package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tipo_producto")
@NamedQueries({
        @NamedQuery(
                name="TipoProducto.IdMaximo",
                query = "SELECT max(tp.idTipoProducto) FROM TipoProducto tp")
})
public class TipoProducto {
    @Id
    @Column(name = "id_tipo_producto", nullable = false)
    private Integer idTipoProducto;

    @NotBlank
    @Size(max = 155, min = 3)
    @Column(name = "nombre", length = 155)
    private String nombre;

    @Column(name = "activo")
    private Boolean activo;

    @Lob
    @Column(name = "comentarios")
    private String comentarios;

    public Integer getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(Integer idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public @NotBlank @Size(max = 155, min = 3) String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank @Size(max = 155, min = 3) String nombre) {
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