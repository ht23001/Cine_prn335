package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @Column(name = "id_producto", nullable = false)
    private Long idProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_producto")
    private sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoProducto idTipoProducto;

    @Size(max = 155)
    @Column(name = "nombre", length = 155)
    private String nombreProducto;

    @Column(name = "activo")
    private Boolean activoProducto;

    @Lob
    @Column(name = "descripcion")
    private String descripcionProducto;

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long id) {
        this.idProducto = id;
    }

    public sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoProducto getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoProducto idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombre) {
        this.nombreProducto = nombre;
    }

    public Boolean getActivoProducto() {
        return activoProducto;
    }

    public void setActivoProducto(Boolean activo) {
        this.activoProducto = activo;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcion) {
        this.descripcionProducto = descripcion;
    }

}