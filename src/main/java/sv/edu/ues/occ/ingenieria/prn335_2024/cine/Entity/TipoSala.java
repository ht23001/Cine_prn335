package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Table(name = "tipo_sala")
public class TipoSala {

    @Id
    @Column(name = "id_tipo_sala", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoSala;

    // @Pattern(regexp = "\\d\\d\\d\\d\\d\\d\\d\\d-\\d", message = "agrega dui puto")
    @NotBlank(message = "Debe ingresar nombre valido")
    @Size(max = 155, min = 3, message = "Debe tener entre 155 y 3 caracteres")
    @Column(name = "nombre", length = 155)
    private String nombreTipoSala;

    @Column(name = "activo")
    private Boolean activoTipoSala;

    @Lob
    @Column(name = "comentarios")
    private String comentariosTipoSala;

    @Lob
    @Column(name = "expresion_regular")
    private String expresionRegular;

    // constructores para prueba mockito
    public TipoSala(int idTipoSala){
         this.idTipoSala = idTipoSala;
    }
    public TipoSala(){}

    public Integer getIdTipoSala() {
        return idTipoSala;
    }

    public void setIdTipoSala(Integer id) {
        this.idTipoSala = id;
    }

    public String getNombreTipoSala() {
        return nombreTipoSala;
    }

    public void setNombreTipoSala(String nombre) {
        this.nombreTipoSala = nombre;
    }

    public Boolean getActivoTipoSala() {
        return activoTipoSala;
    }

    public void setActivoTipoSala(Boolean activo) {
        this.activoTipoSala = activo;
    }

    public String getComentariosTipoSala() {
        return comentariosTipoSala;
    }

    public void setComentariosTipoSala(String comentarios) {
        this.comentariosTipoSala = comentarios;
    }

    public String getExpresionRegular() {
        return expresionRegular;
    }

    public void setExpresionRegular(String expresionRegular) {
        this.expresionRegular = expresionRegular;
    }

}