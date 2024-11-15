package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "sala", schema = "public")
@NamedQueries({
        @NamedQuery(
                name="Sala.findByIdTipoSala",
                query = "SELECT s FROM SalaCaracteristica sc JOIN sc.idSala s WHERE  sc.idTipoSala.idTipoSala = :idTipoSala GROUP BY s.idSala ORDER BY s.nombre ASC ")
})

public class Sala {
    @Id
    @Column(name = "id_sala", nullable = false)
    private Integer idSala;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sucursal")
    private sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sucursal id;


    @Size(max = 155)
    @Column(name = "nombre", length = 155)
    private String nombre;

    @Column(name = "activo")
    private Boolean activo;

    @Lob
    @Column(name = "observaciones")
    private String observaciones;

    public Sala(){};

    public Sala(int idSala){
        this.idSala =idSala;
    }

    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(Integer id) {
        this.idSala = id;
    }

    public sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sucursal getId() {
        return id;
    }

    public void setId(sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sucursal idSucursal) {
        this.id = idSucursal;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}