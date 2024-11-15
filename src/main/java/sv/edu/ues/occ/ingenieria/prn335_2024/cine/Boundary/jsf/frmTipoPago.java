package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.jsf;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.TipoPagoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoPago;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoReserva;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class frmTipoPago implements Serializable {

    @Inject
    FacesContext facesContext;

    @Inject
    TipoPagoBean tpBean;

    ESTADO_CRUD estado;


    LazyDataModel<TipoPago> modelo;


    @PostConstruct
    public void init(){
        estado=ESTADO_CRUD.MODIFICAR;
        modelo=new LazyDataModel<TipoPago>() {

            @Override
            public String getRowKey(TipoPago object) {
                if (object != null && object.getIdTipoPago() != null) {
                    return object.getIdTipoPago().toString(); }
                return null;
            }

            @Override
            public List<TipoPago> getWrappedData() {
                return (List<TipoPago>) super.getWrappedData();
            }

            @Override
            public TipoPago getRowData(String rowKey) {
                if (rowKey != null && getWrappedData() != null) {
                    return getWrappedData().stream().filter(r -> rowKey.equals(r.getIdTipoPago().toString())).findFirst().orElse(null); }
                return null;
            }



            @Override
            public int count(Map<String, FilterMeta> map) {
                try {

                    return  (int) tpBean.count();
                }catch (Exception e){
                    e.printStackTrace();
                    ///TODO: Enviar mensaje de error de acceso
                }
                return 0;
            }

            @Override
            public List<TipoPago> load(int desde, int max, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {


                try {
                    if(desde>=0 && max>0){
                        return tpBean.findRange(desde,max);

                    }

                }catch (Exception e){

                    e.printStackTrace();
                    ///TODO: enviar mensaje de error en el repositorio
                }

                return List.of();
            }
        };


    }

    TipoPago registro;


    public TipoPagoBean getTpBean() {
        return tpBean;
    }

    public void setTpBean(TipoPagoBean tpBean) {
        this.tpBean = tpBean;
    }

    public LazyDataModel<TipoPago> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<TipoPago> modelo) {
        this.modelo = modelo;
    }

    public TipoPago getRegistro() {
        return registro;
    }

    public void setRegistro(TipoPago registro) {
        this.registro = registro;
    }

    public void btnNuevoHandler(ActionEvent actionEvent){
        this.registro = new TipoPago();
        if(tpBean.obtenerMaxIdTipoPago(registro)==null){
            this.registro.setIdTipoPago(1);
            this.registro.setActivo(true);
            this.estado=ESTADO_CRUD.CREAR;
        }else{
            this.registro.setIdTipoPago(tpBean.obtenerMaxIdTipoPago(registro)+1);
            this.registro.setActivo(true);
            this.estado=ESTADO_CRUD.CREAR;
        }

    }

    public void btnGuardarHandler(ActionEvent actionEvent) {
        try {
            this.tpBean.create(registro);
            this.registro =null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro guardado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar el registro"));
            e.printStackTrace();
        }
        this.registro = null;
        this.estado = ESTADO_CRUD.NINGUNO;
    }

    public void btnEliminarHandler(ActionEvent actionEvent){
        FacesMessage mensaje = new FacesMessage();
        tpBean.delete(registro.getIdTipoPago());
        mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
        mensaje.setSummary("Registro eliminado con exito");
        facesContext.addMessage(null, mensaje);
        this.registro =null;
        this.estado=ESTADO_CRUD.NINGUNO;
    }

    public void btnCancelarHandler(ActionEvent actionEvent){

        this.registro = null;
        this.estado=ESTADO_CRUD.NINGUNO;
    }

    public void btnModificarHandler(ActionEvent actionEvent) {
        TipoPago actualizado= tpBean.update(registro);
        FacesMessage mensaje= new FacesMessage();
        if(actualizado!=null){
            this.registro =null;
            this.estado=ESTADO_CRUD.NINGUNO;
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            mensaje.setSummary("Registro modificado con exito");

        }else {
            mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
            mensaje.setSummary("NO se pudo guardar el registro, revise datos");

        }
        facesContext.addMessage(null, mensaje);
    }

    public ESTADO_CRUD getEstado() {
        return estado;
    }

    public void setEstado(ESTADO_CRUD estado) {
        this.estado = estado;
    }
}
