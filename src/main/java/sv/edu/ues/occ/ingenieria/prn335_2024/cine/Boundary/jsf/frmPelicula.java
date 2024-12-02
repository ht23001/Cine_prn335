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
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.PeliculaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Pelicula;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoAsiento;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoPago;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoReserva;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class frmPelicula implements Serializable {


    @Inject
    FacesContext facesContext;

    @Inject
    PeliculaBean pBean;

    ESTADO_CRUD estado;


    LazyDataModel<Pelicula> modelo;


    @PostConstruct
    public void init(){
        estado=ESTADO_CRUD.MODIFICAR;
        modelo=new LazyDataModel<Pelicula>() {

            @Override
            public String getRowKey(Pelicula object) {
                if (object != null && object.getIdPelicula() != null) {
                    return object.getIdPelicula().toString();
                }
                return null;
            }

            @Override
            public Pelicula getRowData(String rowKey) {
                if (rowKey != null && getWrappedData() != null) {
                    return getWrappedData().stream().filter(r -> rowKey.equals(r.getIdPelicula().toString())).findFirst().orElse(null);
                }
                return null;
            }


            @Override
            public List<Pelicula> getWrappedData() {
                return (List<Pelicula>) super.getWrappedData();
            }


            @Override
            public int count(Map<String, FilterMeta> map) {
                try {

                    return  (int) pBean.count();
                }catch (Exception e){
                    e.printStackTrace();
                    ///TODO: Enviar mensaje de error de acceso
                }
                return 0;
            }

            @Override
            public List<Pelicula> load(int desde, int max, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {


                try {
                    if(desde>=0 && max>0){
                        return pBean.findRange(desde,max);

                    }

                }catch (Exception e){

                    e.printStackTrace();
                    ///TODO: enviar mensaje de error en el repositorio
                }

                return List.of();
            }
        };


    }

    Pelicula registro;


    public LazyDataModel<Pelicula> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<Pelicula> modelo) {
        this.modelo = modelo;
    }

    public Pelicula getRegistro() {
        return registro;
    }

    public void setRegistro(Pelicula registro) {
        this.registro = registro;
    }

    public void onRowSelect(SelectEvent<Pelicula> event) {
        registro = event.getObject();
        estado = ESTADO_CRUD.MODIFICAR;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fila Seleccionada", "ID: " + registro.getIdPelicula())); }

    public void btnNuevoHandler(ActionEvent actionEvent){
        this.registro = new Pelicula();
        this.registro.setIdPelicula((long) (pBean.obtenerMaxIdPelicula(registro)+1));
        this.estado=ESTADO_CRUD.CREAR;

    }

    public void btnGuardarHandler(ActionEvent actionEvent) {
        try {
            this.pBean.create(registro);
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
        pBean.delete(registro.getIdPelicula());
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
        Pelicula actualizado= pBean.update(registro);
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

    public ESTADO_CRUD getEstado() {
        return estado;
    }

    public void setEstado(ESTADO_CRUD estado) {
        this.estado = estado;
    }


}
