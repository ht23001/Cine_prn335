package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.Null;
import jakarta.ws.rs.ext.ParamConverter;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.SalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.SucursalBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sala;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sucursal;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class frmSala implements Serializable {
    int idSala;
    @Inject
    SalaBean sBean;

    @Inject
    SucursalBean suB;

    @Inject
    FacesContext facesContext;

    ESTADO_CRUD estado;

    List <Sala> registros;

    LazyDataModel<Sala> modelo;

    @PostConstruct
    public void init() {
        estado = ESTADO_CRUD.NINGUNO;
        registros = sBean.findRange(0, 1000000);
        modelo = new LazyDataModel<Sala>() {

            @Override
            public String getRowKey(Sala object) {
                if (object != null && object.getIdSala() != null) {
                    return object.getIdSala().toString();
                }
                return null;
            }

            @Override
            public Sala getRowData(String rowKey) {
                if (rowKey != null && getWrappedData() != null) {
                    return getWrappedData().stream().filter(r -> rowKey.equals(r.getIdSala().toString())).findFirst().orElse(null);
                }
                return null;
            }

            @Override
            public List<Sala> getWrappedData() {
                return (List<Sala>) super.getWrappedData();
            }


            @Override
            public int count(Map<String, FilterMeta> map) {
                try {

                    return (int) sBean.count();
                } catch (Exception e) {
                    e.printStackTrace();
                    ///TODO: Enviar mensaje de error de acceso
                }
                return 0;
            }

            @Override
            public List<Sala> load(int desde, int max, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {


                try {
                    if (desde >= 0 && max > 0) {
                        return sBean.findRange(desde, max);

                    }

                } catch (Exception e) {

                    e.printStackTrace();
                    ///TODO: enviar mensaje de error en el repositorio
                }

                return List.of();


            }
        };
    }

    Sala registro;

    public Sala getRegistro() {
        return registro;
    }

    public void setRegistro(Sala registro) {
        this.registro = registro;
    }

    public FacesContext getFacesContext() {
        return facesContext;
    }

    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }

    public ESTADO_CRUD getEstado() {
        return estado;
    }

    public void setEstado(ESTADO_CRUD estado) {
        this.estado = estado;
    }



    public LazyDataModel<Sala> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<Sala> modelo) {
        this.modelo = modelo;
    }

    public void btnNuevoHandler(ActionEvent actionEvent){
        this.registro = new Sala();
        this.registro.setIdSala(sBean.obtenerMaxIdSala(registro)+1);
        this.registro.setActivo(true);
        this.estado=ESTADO_CRUD.CREAR;

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
        sBean.delete(registro.getIdSala());
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
        Sala actualizado= sBean.update(registro);
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

