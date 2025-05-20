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

		@Transactional(readOnly = true)
		public SandwichResponseDTO obtenerPorId(Long id) {
				Sandwich sandwich = sandwichRepository.findById(id)
								.orElseThrow(() -> new RuntimeException("Sandwich no encontrado con ID: " + id));
				return SandwichMapper.toResponseDto(sandwich);
		}

	@Transactional
	public SandwichResponseDTO actualizar(Long id, SandwichRequestDTO dto) {
			Sandwich sandwich = sandwichRepository.findById(id)
							.orElseThrow(() -> new RuntimeException("Sandwich no encontrado con ID: " + id));

			Tipo tipo = tipoRepository.findById(dto.getTipoId())
							.orElseThrow(() -> new RuntimeException("Tipo no encontrado con ID: " + dto.getTipoId()));

			Origen origen = origenRepository.findById(dto.getPaisId())
							.orElseThrow(() -> new RuntimeException("Origen no encontrado con ID: " + dto.getPaisId()));

			List<Ingrediente> ingredientes = ingredienteRepository.findAllById(dto.getIngredientesIds());

			if (ingredientes.size() != dto.getIngredientesIds().size()) {
					throw new RuntimeException("Uno o más ingredientes no fueron encontrados.");
			}

			// Validar nombre duplicado
			if (sandwichRepository.existsByNombre(dto.getNombre())
							&& !sandwichRepository.findByNombre(dto.getNombre()).get().getId().equals(id)) {
					throw new RuntimeException("Ya existe un sándwich con el nombre: " + dto.getNombre());
			}
			
			sandwich = SandwichMapper.fromDto(dto, tipo, origen, ingredientes);
			sandwich.setId(id);
			Sandwich actualizado = sandwichRepository.save(sandwich);
			return SandwichMapper.toResponseDto(actualizado);
	}

	@Transactional
	public void eliminar(Long id) {
			if (!sandwichRepository.existsById(id)) {
					throw new RuntimeException("No existe un sandwich con ID: " + id);
			}
			sandwichRepository.deleteById(id);
	}

}
