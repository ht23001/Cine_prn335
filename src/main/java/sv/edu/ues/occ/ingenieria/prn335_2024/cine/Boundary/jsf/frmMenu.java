package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;

import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class frmMenu implements Serializable {

    FacesContext facesContext;

    DefaultMenuModel modelo;

    @PostConstruct
    public void init() {
        modelo = new DefaultMenuModel();
        DefaultSubMenu tipos = DefaultSubMenu.builder()
                .label("Tipos")
                .expanded(true)
                .build();
        DefaultMenuItem item=DefaultMenuItem.builder()
                .value("Sala")
                .ajax(true)
                .command("#{frmMenu.navegar('TipoSala.jsf')}")
                .build();
        tipos.getElements().add(item);
        modelo.getElements().add(tipos);
    }

    public void navegar(String formulario) throws IOException {
        facesContext.getExternalContext().redirect(formulario);
    }

    public DefaultMenuModel getModelo() {
        return modelo;
    }
}
