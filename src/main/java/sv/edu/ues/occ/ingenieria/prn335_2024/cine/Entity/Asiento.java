package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "asiento")
public class Asiento {
    @Id
    @Column(name = "id_asiento", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sala")
    private sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sala idSala;

    @Size(max = 155)
    @Column(name = "nombre", length = 155)
    private String nombre;

    @Column(name = "activo")
    private Boolean activo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sala getIdSala() {
        return idSala;
    }

    public void setIdSala(sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sala idSala) {
        this.idSala = idSala;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

}