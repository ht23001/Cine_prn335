package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "sucursal")
public class Sucursal {
    @Id
    @Column(name = "id_sucursal", nullable = false)
    private Integer idSucursal;

    @Size(max = 155)
    @Column(name = "nombre", length = 155)
    private String nombreSucursal;

    @Column(name = "longitud")
    private Double longitudSucursal;

    @Column(name = "latitud")
    private Double latitudSucursal;

    @Lob
    @Column(name = "comentarios")
    private String comentariosSucursal;

    @Column(name = "activo")
    private Boolean activoSucursal;

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer id) {
        this.idSucursal = id;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombre) {
        this.nombreSucursal = nombre;
    }

    public Double getLongitudSucursal() {
        return longitudSucursal;
    }

    public void setLongitudSucursal(Double longitud) {
        this.longitudSucursal = longitud;
    }

    public Double getLatitudSucursal() {
        return latitudSucursal;
    }

    public void setLatitudSucursal(Double latitud) {
        this.latitudSucursal = latitud;
    }

    public String getComentariosSucursal() {
        return comentariosSucursal;
    }

    public void setComentariosSucursal(String comentarios) {
        this.comentariosSucursal = comentarios;
    }

    public Boolean getActivoSucursal() {
        return activoSucursal;
    }

    public void setActivoSucursal(Boolean activo) {
        this.activoSucursal = activo;
    }

}