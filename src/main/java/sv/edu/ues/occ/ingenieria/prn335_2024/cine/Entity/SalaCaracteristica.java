package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sala_caracteristica")
public class SalaCaracteristica {
    @Id
    @Column(name = "id_sala_caracteristica", nullable = false)
    private Long idSalaCaracteristica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_sala")
    private sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoSala idTipoSala;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sala")
    private Sala idSala;

    @Lob
    @Column(name = "valor")
    private String valorSalaCaracteristica;

    public Long getIdSalaCaracteristica() {
        return idSalaCaracteristica;
    }

    public void setIdSalaCaracteristica(Long id) {
        this.idSalaCaracteristica = id;
    }

    public sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoSala getIdTipoSala() {
        return idTipoSala;
    }

    public void setIdTipoSala(sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoSala idTipoSala) {
        this.idTipoSala = idTipoSala;
    }

    public Sala getIdSala() {
        return idSala;
    }

    public void setIdSala(Sala idSala) {
        this.idSala = idSala;
    }

    public String getValorSalaCaracteristica() {
        return valorSalaCaracteristica;
    }

    public void setValorSalaCaracteristica(String valor) {
        this.valorSalaCaracteristica = valor;
    }

}