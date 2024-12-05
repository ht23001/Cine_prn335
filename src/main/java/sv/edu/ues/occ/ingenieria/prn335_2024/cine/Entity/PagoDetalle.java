package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "pago_detalle")
public class PagoDetalle {
    @Id
    @Column(name = "id_pago_detalle", nullable = false)
    private Long idPagoDetalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pago")
    private Pago idPago;

    @Column(name = "monto", precision = 10, scale = 2)
    private BigDecimal montoPagoDetalle;

    @Lob
    @Column(name = "concepto")
    private String conceptoPagoDetalle;

    public Long getIdPagoDetalle() {
        return idPagoDetalle;
    }

    public void setIdPagoDetalle(Long id) {
        this.idPagoDetalle = id;
    }

    public Pago getIdPago() {
        return idPago;
    }

    public void setIdPago(Pago idPago) {
        this.idPago = idPago;
    }

    public BigDecimal getMontoPagoDetalle() {
        return montoPagoDetalle;
    }

    public void setMontoPagoDetalle(BigDecimal monto) {
        this.montoPagoDetalle = monto;
    }

    public String getConceptoPagoDetalle() {
        return conceptoPagoDetalle;
    }

    public void setConceptoPagoDetalle(String concepto) {
        this.conceptoPagoDetalle = concepto;
    }

}