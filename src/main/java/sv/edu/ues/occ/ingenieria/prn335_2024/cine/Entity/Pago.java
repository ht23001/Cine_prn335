package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "pago")
public class Pago {
    @Id
    @Column(name = "id_pago", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_factura")
    private Factura idFactura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_pago")
    private sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoPago idTipoPago;

    @Column(name = "fecha")
    private OffsetDateTime fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Factura getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Factura idFactura) {
        this.idFactura = idFactura;
    }

    public sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoPago getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoPago idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public OffsetDateTime getFecha() {
        return fecha;
    }

    public void setFecha(OffsetDateTime fecha) {
        this.fecha = fecha;
    }

}