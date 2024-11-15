package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pelicula")
public class Pelicula {

    @Id
    @Column(name = "id_pelicula", nullable = false)
    private Long idPelicula;

    @Size(max = 255)
    @Column(name = "nombre")
    private String nombrePelicula;

    @Lob
    @Column(name = "sinopsis")
    private String sinopsisPelicula;

    public Long getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Long id) {
        this.idPelicula = id;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public void setNombrePelicula(String nombre) {
        this.nombrePelicula = nombre;
    }

    public String getSinopsisPelicula() {
        return sinopsisPelicula;
    }

    public void setSinopsisPelicula(String sinopsis) {
        this.sinopsisPelicula = sinopsis;
    }

}