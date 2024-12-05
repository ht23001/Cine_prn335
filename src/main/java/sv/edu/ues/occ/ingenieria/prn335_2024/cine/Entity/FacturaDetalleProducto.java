package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "factura_detalle_producto")
public class FacturaDetalleProducto {
    @Id
    @Column(name = "id_factura_detalle_producto", nullable = false)
    private Long idFacturaDetalleProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_factura")
    private Factura idFactura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    private sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Producto idProducto;

    @Column(name = "monto", precision = 10, scale = 2)
    private BigDecimal montoFacturaDetalleProducto;

    public Long getIdFacturaDetalleProducto() {
        return idFacturaDetalleProducto;
    }

    public void setIdFacturaDetalleProducto(Long id) {
        this.idFacturaDetalleProducto = id;
    }

    public Factura getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Factura idFactura) {
        this.idFactura = idFactura;
    }

    public sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Producto idProducto) {
        this.idProducto = idProducto;
    }

    public BigDecimal getMontoFacturaDetalleProducto() {
        return montoFacturaDetalleProducto;
    }

    public void setMontoFacturaDetalleProducto(BigDecimal monto) {
        this.montoFacturaDetalleProducto = monto;
    }

}