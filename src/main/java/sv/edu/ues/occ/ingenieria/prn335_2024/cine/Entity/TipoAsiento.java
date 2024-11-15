package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "tipo_asiento")
@NamedQueries({
        @NamedQuery(
                name="TipoAsiento.IdMaximo",
                query = "SELECT max (ta.idTipoAsiento) FROM TipoAsiento ta")
})
public class TipoAsiento {
    @Id
    @Column(name = "id_tipo_asiento", nullable = false)

    private Integer idTipoAsiento;


    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "idTipoAsiento")
    public List<AsientoCaracteristica> AsientoCaracteristicaList;

    public List<AsientoCaracteristica> getAsientoCaracteristicaList() {
        return AsientoCaracteristicaList;
    }

    public void setAsientoCaracteristicaList(List<AsientoCaracteristica> asientoCaracteristicaList) {
        AsientoCaracteristicaList = asientoCaracteristicaList;
    }


    @NotBlank
    @Size(max = 155, min = 3)
    @Column(name = "nombre", length = 155)
    private String nombre;

    @Column(name = "activo")
    private Boolean activo;

    @Lob
    @Column(name = "comentarios")
    private String comentarios;

    @Lob
    @Column(name = "expresion_regular")
    private String expresionRegular;


    public TipoAsiento(int idTipoAsiento){
        this.idTipoAsiento = idTipoAsiento;
    }
    public TipoAsiento(){}

    public Integer getIdTipoAsiento() {
        return idTipoAsiento;
    }

    public void setIdTipoAsiento(Integer idTipoAsiento) {
        this.idTipoAsiento = idTipoAsiento;
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

    public String getExpresionRegular() {
        return expresionRegular;
    }

    public void setExpresionRegular(String expresionRegular) {
        this.expresionRegular = expresionRegular;
    }

}