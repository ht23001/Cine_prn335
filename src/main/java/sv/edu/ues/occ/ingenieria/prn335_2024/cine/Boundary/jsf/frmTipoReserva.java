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
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.TipoReservaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoReserva;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class frmTipoReserva implements Serializable {

    @Inject
    FacesContext facesContext;

    @Inject
    TipoReservaBean dataBean;

    LazyDataModel<TipoReserva> modelo;

    TipoReserva registro;

     @PostConstruct
    public void inicializar(){
         modelo=new  LazyDataModel<TipoReserva>() {

             @Override
             public String getRowKey(TipoReserva object) {
                 if(object!=null && object.getIdTipoReserva()!=null){
                     return object.getIdTipoReserva().toString();
                 }
                 return null;
             }

             @Override
             public TipoReserva getRowData(String rowKey) {
                 if(rowKey!=null && getWrappedData()!=null){
                     return getWrappedData().stream().filter(r->rowKey.equals(r.getIdTipoReserva().toString())).findFirst().orElse(null);

                 }
                 return null;
             }

             @Override
             public int count(Map<String, FilterMeta> map) {

                 try {

                     return  (int)dataBean.count();
                 }catch (Exception e){
                     e.printStackTrace();
                 }
                 return 0;
             }

             @Override
             public List<TipoReserva> load(int desde, int max, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {

                 try {
                     if(desde>=0 && max>0){
                         return dataBean.findRange(desde,max);

                     }

                 }catch (Exception e){

                     e.printStackTrace();

                 }

                 return List.of();
             }
         };

    }


    public TipoReservaBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(TipoReservaBean dataBean) {
        this.dataBean = dataBean;
    }

    public LazyDataModel<TipoReserva> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<TipoReserva> modelo) {
        this.modelo = modelo;
    }

    public TipoReserva getRegistro() {
        return registro;
    }

    public void setRegistro(TipoReserva registro) {
        this.registro = registro;
    }

    public void btnNuevoHandler(ActionEvent event) {
         this.registro= new TipoReserva();
         this.registro.setActivoTipoReserva(true);

    }

    public void btnCrearHandler(ActionEvent event){
         dataBean.create(registro);
        FacesMessage mensaje= new FacesMessage();
        mensaje.setSummary("Creado con exito");
        mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
        facesContext.addMessage(null, mensaje);
    }
}
