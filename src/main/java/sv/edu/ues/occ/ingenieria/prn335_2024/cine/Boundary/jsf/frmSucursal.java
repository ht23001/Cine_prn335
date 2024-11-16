package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.SucursalBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.TipoProductoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sucursal;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoProducto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class frmSucursal implements Serializable {

    @Inject
    FacesContext facesContext;

    @Inject
    SucursalBean sBean;

    ESTADO_CRUD estado;


    LazyDataModel<Sucursal> modelo;


    @PostConstruct
    public void init(){
        estado=ESTADO_CRUD.MODIFICAR;
        modelo=new LazyDataModel<Sucursal>() {

            @Override
            public String getRowKey(Sucursal object) {
                if (object != null && object.getIdSucursal() != null) {
                    return object.getIdSucursal().toString(); }
                return null;
            }

            @Override
            public Sucursal getRowData(String rowKey) {
                if (rowKey != null && getWrappedData() != null) {
                    return getWrappedData().stream().filter(r -> rowKey.equals(r.getIdSucursal().toString())).findFirst().orElse(null); }
                return null;
            }

            @Override
            public List<Sucursal> getWrappedData() {
                return (List<Sucursal>) super.getWrappedData();
            }


            @Override
            public int count(Map<String, FilterMeta> map) {
                try {

                    return  (int)sBean.count();
                }catch (Exception e){
                    e.printStackTrace();
                    ///TODO: Enviar mensaje de error de acceso
                }
                return 0;
            }

            @Override
            public List<Sucursal> load(int desde, int max, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {


                try {
                    if(desde>=0 && max>0){
                        return sBean.findRange(desde,max);

                    }

                }catch (Exception e){

                    e.printStackTrace();
                    ///TODO: enviar mensaje de error en el repositorio
                }

                return List.of();
            }
        };


    }

    Sucursal registro;


    public LazyDataModel<Sucursal> getModelo() {
        return modelo;

    }

    public void setModelo(LazyDataModel<Sucursal> modelo) {
        this.modelo = modelo;
    }

    public ESTADO_CRUD getEstado() {
        return estado;
    }

    public void setEstado(ESTADO_CRUD estado) {
        this.estado = estado;
    }

    public FacesContext getFacesContext() {
        return facesContext;
    }

    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }

    public Sucursal getRegistro() {
        return registro;
    }

    public void setRegistro(Sucursal registro) {
        this.registro = registro;
    }

    public void onRowSelect(SelectEvent<Sucursal> event) {
        registro = event.getObject();
        estado = ESTADO_CRUD.MODIFICAR;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fila Seleccionada", "ID: " + registro.getIdSucursal())); }

    public void btnNuevoHandler(ActionEvent actionEvent){
        this.registro = new Sucursal();
        if(sBean.obtenerMaxIdSucursal(registro)==null){
            this.registro.setIdSucursal(1);
            this.registro.setActivo(true);
            this.estado=ESTADO_CRUD.CREAR;
        }else{
            this.registro.setIdSucursal(sBean.obtenerMaxIdSucursal(registro)+1);
            this.registro.setActivo(true);
            this.estado=ESTADO_CRUD.CREAR;
        }


    }

    public void btnGuardarHandler(ActionEvent actionEvent) {
        try {
            this.sBean.create(registro);
            this.registro =null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro guardado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar el registro"));
            e.printStackTrace();
        }
        this.registro = null;
        this.estado = ESTADO_CRUD.MODIFICAR;
    }

    public void btnEliminarHandler(ActionEvent actionEvent){
        FacesMessage mensaje = new FacesMessage();
        sBean.delete(registro.getIdSucursal());
        mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
        mensaje.setSummary("Registro eliminado con exito");
        facesContext.addMessage(null, mensaje);
        this.registro =null;
        this.estado=ESTADO_CRUD.MODIFICAR;
    }

    public void btnCancelarHandler(ActionEvent actionEvent){

        this.registro = null;
        this.estado=ESTADO_CRUD.MODIFICAR;
    }

    public void btnModificarHandler(ActionEvent actionEvent) {
        Sucursal actualizado= sBean.update(registro);
        FacesMessage mensaje= new FacesMessage();
        if(actualizado!=null){
            this.registro =null;
            this.estado=ESTADO_CRUD.MODIFICAR;
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            mensaje.setSummary("Registro modificado con exito");

        }else {
            mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
            mensaje.setSummary("NO se pudo guardar el registro, revise datos");

        }
        facesContext.addMessage(null, mensaje);
    }

}
