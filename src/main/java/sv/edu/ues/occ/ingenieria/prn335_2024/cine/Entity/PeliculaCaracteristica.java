package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pelicula_caracteristica")
public class PeliculaCaracteristica {
    @Id
    @Column(name = "id_pelicula_caracteristica", nullable = false)
    private Long idPeliculaCaracteristica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_pelicula")
    private sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoPelicula idTipoPelicula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pelicula")
    private Pelicula idPelicula;

    @Lob
    @Column(name = "valor")
    private String valorPeliculaCaracteristica;

    public Long getIdPeliculaCaracteristica() {
        return idPeliculaCaracteristica;
    }

    public void setIdPeliculaCaracteristica(Long id) {
        this.idPeliculaCaracteristica = id;
    }

    public sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoPelicula getIdTipoPelicula() {
        return idTipoPelicula;
    }

    public void setIdTipoPelicula(sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoPelicula idTipoPelicula) {
        this.idTipoPelicula = idTipoPelicula;
    }

    public Pelicula getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Pelicula idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getValorPeliculaCaracteristica() {
        return valorPeliculaCaracteristica;
    }

    public void setValorPeliculaCaracteristica(String valor) {
        this.valorPeliculaCaracteristica = valor;
    }

}