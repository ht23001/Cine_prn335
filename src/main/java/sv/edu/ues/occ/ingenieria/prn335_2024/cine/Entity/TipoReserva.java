package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tipo_reserva")
public class TipoReserva {
    @Id
    @Column(name = "id_tipo_reserva", nullable = false)
    private Integer idTipoReserva;

    @NotBlank
    @Size(max = 155 , min=3)
    @Column(name = "nombre", length = 155)
    private String nombreTipoReserva;

    @Column(name = "activo")
    private Boolean activoTipoReserva;

    @Lob
    @Column(name = "comentarios")
    private String comentariosTipoReserva;

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

    public String getNombreTipoReserva() {
        return nombreTipoReserva;
    }

    public void setNombreTipoReserva(String nombre) {
        this.nombreTipoReserva = nombre;
    }

    public Boolean getActivoTipoReserva() {
        return activoTipoReserva;
    }

    public void setActivoTipoReserva(Boolean activo) {
        this.activoTipoReserva = activo;
    }

    public String getComentariosTipoReserva() {
        return comentariosTipoReserva;
    }

    public void setComentariosTipoReserva(String comentarios) {
        this.comentariosTipoReserva = comentarios;
    }

}