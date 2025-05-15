package cl.josbla.sandwichplanet.client.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.josbla.sandwichplanet.client.dto.SandwichRequestDTO;
import cl.josbla.sandwichplanet.client.dto.SandwichResponseDTO;
import cl.josbla.sandwichplanet.client.dto.SandwichResumenDTO;
import cl.josbla.sandwichplanet.client.dto.SandwichesMetadataDTO;
import cl.josbla.sandwichplanet.client.mapper.SandwichMapper;
import cl.josbla.sandwichplanet.client.models.Ingrediente;
import cl.josbla.sandwichplanet.client.models.Origen;
import cl.josbla.sandwichplanet.client.models.Sandwich;
import cl.josbla.sandwichplanet.client.models.Tipo;
import cl.josbla.sandwichplanet.client.repository.IngredienteRepository;
import cl.josbla.sandwichplanet.client.repository.OrigenRepository;
import cl.josbla.sandwichplanet.client.repository.SandwichRepository;
import cl.josbla.sandwichplanet.client.repository.TipoRepository;

@Service
public class SandwichService {
    private final SandwichRepository sandwichRepository;
    private final IngredienteRepository ingredienteRepository;
    private final OrigenRepository origenRepository;
    private final TipoRepository tipoRepository;

    public SandwichService(SandwichRepository sandwichRepository,
                           IngredienteRepository ingredienteRepository,
                           OrigenRepository paisRepository,
                           TipoRepository tipoSandwichRepository) {
        this.sandwichRepository = sandwichRepository;
        this.ingredienteRepository = ingredienteRepository;
        this.origenRepository = paisRepository;
        this.tipoRepository = tipoSandwichRepository;
    }

    @Transactional
    public SandwichResponseDTO crearSandwich(SandwichRequestDTO dto) {
        // Obtener entidades por ID
        Tipo tipo = tipoRepository.findById(dto.getTipoId())
                .orElseThrow(() -> new RuntimeException("Tipo no encontrado con ID: " + dto.getTipoId()));

        Origen origen = origenRepository.findById(dto.getPaisId())
                .orElseThrow(() -> new RuntimeException("Origen no encontrado con ID: " + dto.getPaisId()));

        List<Ingrediente> ingredientes = ingredienteRepository.findAllById(dto.getIngredientesIds());

        if (ingredientes.size() != dto.getIngredientesIds().size()) {
            throw new RuntimeException("Uno o más ingredientes no fueron encontrados.");
        }

        // Mapear a entidad
        Sandwich sandwich = SandwichMapper.fromDto(dto, tipo, origen, ingredientes);

        // Guardar
        Sandwich sandwichGuardado = sandwichRepository.save(sandwich);

        // Mapear a respuesta
        return SandwichMapper.toResponseDto(sandwichGuardado);
    }

    @Transactional(readOnly = true)
    public SandwichesMetadataDTO obtenerTodosLosSandwiches() {
        List<Sandwich> sandwiches = sandwichRepository.findAll();

        List<SandwichResponseDTO> sandwichDtos = SandwichMapper.toDtoList(sandwiches);

        // Extraer países y tipos únicos para la metadata
        List<String> paises = sandwiches.stream()
                .map(s -> s.getOrigen().getNombre())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        List<String> tipos = sandwiches.stream()
                .map(s -> s.getTipo().getNombre())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        SandwichesMetadataDTO.Metadata metadata = SandwichesMetadataDTO.Metadata.builder()
                .totalSandwiches(sandwichDtos.size())
                .paisesRepresentados(paises)
                .tipos(tipos)
                .build();

        return SandwichesMetadataDTO.builder()
                .sandwiches(sandwichDtos)
                .metadata(metadata)
                .build();
    }

    @Transactional(readOnly = true)
    public List<SandwichResumenDTO> listarResumen() {
        List<Sandwich> sandwiches = sandwichRepository.findAll();
        return sandwiches.stream()
                .map(SandwichMapper::toResumenDto)
                .collect(Collectors.toList());
        }

}
