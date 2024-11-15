package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

@Entity
@Table(name = "factura")
public class Factura {
    @Id
    @Column(name = "id_factura", nullable = false)
    private Long idFactura;

    @Size(max = 255)
    @Column(name = "cliente")
    private String clienteFactura;

    @Size(max = 155)
    @Column(name = "dui", length = 155)
    private String duiFactura;

    @Column(name = "fecha")
    private OffsetDateTime fechaFactura;

    @Lob
    @Column(name = "comentarios")
    private String comentariosFactura;

    public Long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Long id) {
        this.idFactura = id;
    }

    public String getClienteFactura() {
        return clienteFactura;
    }

    public void setClienteFactura(String cliente) {
        this.clienteFactura = cliente;
    }

    public String getDuiFactura() {
        return duiFactura;
    }

    public void setDuiFactura(String dui) {
        this.duiFactura = dui;
    }

    public OffsetDateTime getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(OffsetDateTime fecha) {
        this.fechaFactura = fecha;
    }

    public String getComentariosFactura() {
        return comentariosFactura;
    }

    public void setComentariosFactura(String comentarios) {
        this.comentariosFactura = comentarios;
    }

}