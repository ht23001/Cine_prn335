package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.TipoSalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoSala;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class frmTipoSala implements Serializable {

    @Inject
    TipoSalaBean tsBean;

    @Inject
    FacesContext facesContext;

    ESTADO_CRUD estado;
    List<TipoSala> registros;
     String expresionRegular; // Agregada propiedad faltante

     @PostConstruct
    public void init(){
         estado=ESTADO_CRUD.NINGUNO;
        registros = tsBean.findRange(0,1000000);
    }

    TipoSala registro;

    public void btnSelecionarRegistroHandler(final Integer idTipoSala){
        if(idTipoSala !=null){
            this.registro=this.registros.stream().filter(r->idTipoSala.equals(r.getIdTipoSala())).findFirst().orElse(null);
            this.estado=ESTADO_CRUD.MODIFICAR;
            return;
        }
        this.registro=null;

    }

    public void btnCancelarHandler(ActionEvent actionEvent){

        this.registro = null;
        this.estado=ESTADO_CRUD.NINGUNO;
    }

    public void btnNuevoHandler(ActionEvent actionEvent){
        this.registro = new TipoSala();
        this.registro.setActivoTipoSala(true);
        this.registro.setExpresionRegular(".");
        this.estado=ESTADO_CRUD.CREAR;
    }


    // Getters y Setters
    public List<TipoSala> getRegistros() {
        return registros;
    }

    public void setRegistros(List<TipoSala> registros) {
        this.registros = registros;
    }

    public Integer getSelecionado() {
        if (registro == null) {
            return null;
        }
        return registro.getIdTipoSala();
    }

    public void setSelecionado(Integer selecionado){
        this.registro = this.registros.stream()
                .filter(r -> r.getIdTipoSala() == selecionado).collect(Collectors.toList()).getFirst();
             if(this.registro!=null){
                 this.estado=ESTADO_CRUD.MODIFICAR;
             }
    }

    public TipoSala getRegistro() {
        return registro;
    }

    public void setRegistro(TipoSala registro) {
        this.registro = registro;
    }

    public String getExpresionRegular() { // Getter para expresionRegular
        return expresionRegular;
    }

    public void setExpresionRegular(String expresionRegular) { // Setter para expresionRegular
        this.expresionRegular = expresionRegular;
    }


    public void btnGuardarHandler(ActionEvent actionEvent) {
       this.tsBean.create(registro);
        this.registro=null;

        this.registros=tsBean.findRange(0,1000000);
        this.estado=ESTADO_CRUD.NINGUNO;
    }

    public ESTADO_CRUD getEstado() {
        return estado;
    }

    public void btnModificarHandler(ActionEvent actionEvent) {
         TipoSala actualizado= tsBean.update(registro);
        FacesMessage mensaje= new FacesMessage();
         if(actualizado!=null){
             this.registro=null;
             this.estado=ESTADO_CRUD.NINGUNO;
             mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
             mensaje.setSummary("Registro modificado con exito");

         }else {
             mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
             mensaje.setSummary("NO se pudo guardar el registro, revise datos");

         }
        facesContext.addMessage(null, mensaje);
    }




}
