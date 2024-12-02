package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tipo_reserva")
@NamedQueries({
        @NamedQuery(
                name="TipoReserva.IdMaximo",
                query = "SELECT max (tr.idTipoReserva) FROM TipoReserva tr")
})
public class TipoReserva {
    @Id
    @Column(name = "id_tipo_reserva", nullable = false)
    private Integer idTipoReserva;


    /*COMENTO ESTO  GENERA ERROR EN LOS REST


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "idTipoReserva")
    @JsonIgnore
    public List<Reserva> ReservaList;

    public List<Reserva> getReservaList() {
        return ReservaList;
    }
    public void setReservaList(List<Reserva> reservaList) {
        ReservaList = reservaList;
    }

    en teoria con @JSONignore se arrega */



    @NotBlank
    @Size(max = 155, min = 3)
    @Column(name = "nombre", length = 155)
    private String nombre;

    private Boolean activo;

    private String comentarios;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_reserva_id_gen")
    @SequenceGenerator(name = "tipo_reserva_id_gen", sequenceName = "tipo_reserva_id_tipo_reserva_seq", allocationSize = 1)
    @Column(name = "id_tipo_reserva", nullable = false)
    public Integer getIdTipoReserva() {
        return idTipoReserva;
    }

    public void setIdTipoReserva(Integer id) {
        this.idTipoReserva = id;
    }

    @Size(max = 155)
    @Column(name = "nombre", length = 155)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "activo")
    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Lob
    @Column(name = "comentarios")
    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

}
