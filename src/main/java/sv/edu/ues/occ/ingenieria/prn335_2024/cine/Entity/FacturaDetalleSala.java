package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "factura_detalle_sala")
public class FacturaDetalleSala {
    @Id
    @Column(name = "id_factura_detalle_sala", nullable = false)
    private Long idFacturaDetalleSala;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_factura")
    private Factura idFactura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reserva_detalle")
    private sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.ReservaDetalle idReservaDetalle;

    @Column(name = "monto", precision = 10, scale = 2)
    private BigDecimal montoFacturaDetalleSala;

    public Long getIdFacturaDetalleSala() {
        return idFacturaDetalleSala;
    }

    public void setIdFacturaDetalleSala(Long id) {
        this.idFacturaDetalleSala = id;
    }

    public Factura getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Factura idFactura) {
        this.idFactura = idFactura;
    }

    public sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.ReservaDetalle getIdReservaDetalle() {
        return idReservaDetalle;
    }

    public void setIdReservaDetalle(sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.ReservaDetalle idReservaDetalle) {
        this.idReservaDetalle = idReservaDetalle;
    }

    public BigDecimal getMontoFacturaDetalleSala() {
        return montoFacturaDetalleSala;
    }

    public void setMontoFacturaDetalleSala(BigDecimal monto) {
        this.montoFacturaDetalleSala = monto;
    }

}