package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pelicula")
@NamedQueries({
        @NamedQuery(
                name="Pelicula.IdMaximo",
                query = "SELECT max(p.idPelicula) FROM Pelicula p")
})
public class Pelicula {
    @Id
    @Column(name = "id_pelicula", nullable = false)
    private Long idPelicula;

    private String nombre;

    private String sinopsis;

    public Long getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Long idPelicula) {
        this.idPelicula = idPelicula;
    }
    


    @Size(max = 255)
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Lob
    @Column(name = "sinopsis")
    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

}