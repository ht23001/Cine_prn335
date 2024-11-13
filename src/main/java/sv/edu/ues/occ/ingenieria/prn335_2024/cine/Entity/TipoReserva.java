package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tipo_reserva")
public class TipoReserva {
    @Id
    @Column(name = "id_tipo_reserva", nullable = false)
    private Integer idTipoReserva;

    @Size(max = 155)
    @Column(name = "nombre", length = 155)
    private String nombre;

    @Column(name = "activo")
    private Boolean activo;

    @Lob
    @Column(name = "comentarios")
    private String comentarios;

    // constructores para mockito
    public TipoReserva(int idTipoReserva){
        this.idTipoReserva = idTipoReserva;
    }

    public TipoReserva(){}

    public Integer getIdTipoReserva() {
        return idTipoReserva;
    }

    public void setIdTipoReserva(Integer id) {
        this.idTipoReserva = id;
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

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

}