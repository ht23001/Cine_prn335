package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "asiento")
public class Asiento {
    @Id
    @Column(name = "id_asiento", nullable = false)
    private Long idAsiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sala")
    private sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sala idSala;

    @Size(max = 155)
    @Column(name = "nombre", length = 155)
    private String nombreAsiento;

    @Column(name = "activo")
    private Boolean activoAsiento;

    public Long getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(Long id) {
        this.idAsiento = id;
    }

    public sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sala getIdSala() {
        return idSala;
    }

    public void setIdSala(sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sala idSala) {
        this.idSala = idSala;
    }

    public String getNombreAsiento() {
        return nombreAsiento;
    }

    public void setNombreAsiento(String nombre) {
        this.nombreAsiento = nombre;
    }

    public Boolean getActivoAsiento() {
        return activoAsiento;
    }

    public void setActivoAsiento(Boolean activo) {
        this.activoAsiento = activo;
    }

}