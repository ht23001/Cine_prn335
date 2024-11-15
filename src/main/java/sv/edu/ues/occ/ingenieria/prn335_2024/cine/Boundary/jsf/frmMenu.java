package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.jsf;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;

import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class frmMenu implements Serializable {

    @Inject
    FacesContext facesContext;

    DefaultMenuModel  model;

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();
        DefaultSubMenu tipos  = DefaultSubMenu.builder()
                .label("Tipos")
                . expanded( true)
                .build();
        DefaultMenuItem item=DefaultMenuItem.builder()
                .value("Sala")
                .ajax(true)
                .command("#{frmMenu.navegar('TipoSala.jsf')}")
                .build();
        tipos.getElements().add(item);

        model.getElements().add(tipos );
    }

    public void navegar(String formulario ) throws IOException {
        facesContext.getExternalContext().redirect(formulario );
    }

    public DefaultMenuModel getModel() {
        return model;
    }
}
