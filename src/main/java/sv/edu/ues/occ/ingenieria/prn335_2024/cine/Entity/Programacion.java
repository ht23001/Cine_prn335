package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "programacion")
public class Programacion {
    @Id
    @Column(name = "id_programacion", nullable = false)
    private Long idProgramacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sala")
    private sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sala idSala;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pelicula")
    private Pelicula idPelicula;

    @Column(name = "desde")
    private OffsetDateTime desdeProgramacion;

    @Column(name = "hasta")
    private OffsetDateTime hastaProgramacion;

    @Lob
    @Column(name = "comentarios")
    private String comentariosProgramacion;

    public Long getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(Long id) {
        this.idProgramacion = id;
    }

    public sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sala getIdSala() {
        return idSala;
    }

    public void setIdSala(sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sala idSala) {
        this.idSala = idSala;
    }

    public Pelicula getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Pelicula idPelicula) {
        this.idPelicula = idPelicula;
    }

    public OffsetDateTime getDesdeProgramacion() {
        return desdeProgramacion;
    }

    public void setDesdeProgramacion(OffsetDateTime desde) {
        this.desdeProgramacion = desde;
    }

    public OffsetDateTime getHastaProgramacion() {
        return hastaProgramacion;
    }

    public void setHastaProgramacion(OffsetDateTime hasta) {
        this.hastaProgramacion = hasta;
    }

    public String getComentariosProgramacion() {
        return comentariosProgramacion;
    }

    public void setComentariosProgramacion(String comentarios) {
        this.comentariosProgramacion = comentarios;
    }

}