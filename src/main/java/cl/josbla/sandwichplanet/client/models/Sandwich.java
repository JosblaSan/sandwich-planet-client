package cl.josbla.sandwichplanet.client.models;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "sandwiches", uniqueConstraints = {
    @UniqueConstraint(columnNames = "nombre", name = "UK_sandwiches_nombre")
})
public class Sandwich {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipoId", nullable = false)
    private Tipo tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origenId", nullable = false)
    private Origen origen;

    @Column(nullable = false)
    private boolean vegetariano;

    @Column(name = "caloriasAproximadas", nullable = false)
    private int caloriasAproximadas;

    @ManyToMany
    @JoinTable(
        name = "sandwichIngredientes",
        joinColumns = @JoinColumn(name = "sandwichId"),
        inverseJoinColumns = @JoinColumn(name = "ingredienteId")
    )
    private Set<Ingrediente> ingredientes;

    @Column(name = "precio", nullable = false)
    private double precio;

    @Column(name = "disponible", nullable = false)
    private boolean disponible;

    @Column(name = "imagenUrl", length = 255)
    private String imagenUrl;

    @Column(name = "fechaCreacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaActualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }
}
