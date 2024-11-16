package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.jsf;

import jakarta.annotation.PostConstruct;
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
        DefaultMenuItem item1=DefaultMenuItem.builder()
                .value("Asiento")
                .ajax(true)
                .command("#{frmMenu.navegar('TipoAsiento.jsf')}")
                .build();

        tipos.getElements().add(item1);
        DefaultMenuItem item2=DefaultMenuItem.builder()
                .value("Pago")
                .ajax(true)
                .command("#{frmMenu.navegar('TipoPago.jsf')}")
                .build();

        tipos.getElements().add(item2);
        DefaultMenuItem item3=DefaultMenuItem.builder()
                .value("Pelicula")
                .ajax(true)
                .command("#{frmMenu.navegar('TipoPelicula.jsf')}")
                .build();

        tipos.getElements().add(item3);
        DefaultMenuItem item4=DefaultMenuItem.builder()
                .value("Producto")
                .ajax(true)
                .command("#{frmMenu.navegar('TipoProducto.jsf')}")
                .build();

        tipos.getElements().add(item4);
        DefaultMenuItem item5=DefaultMenuItem.builder()
                .value("Reserva")
                .ajax(true)
                .command("#{frmMenu.navegar('TipoReserva.jsf')}")
                .build();

        tipos.getElements().add(item5);






        DefaultSubMenu cine  = DefaultSubMenu.builder()
                .label("Cine")
                . expanded( true)
                .build();
        DefaultMenuItem item6=DefaultMenuItem.builder()
                .value("Pelicula")
                .ajax(true)
                .command("#{frmMenu.navegar('Pelicula.jsf')}")
                .build();

        cine.getElements().add(item6);
        DefaultMenuItem item7=DefaultMenuItem.builder()
                .value("Sucursal")
                .ajax(true)
                .command("#{frmMenu.navegar('Sucursal.jsf')}")
                .build();

        cine.getElements().add(item7);
        DefaultMenuItem item8=DefaultMenuItem.builder()
                .value("Sala")
                .ajax(true)
                .command("#{frmMenu.navegar('Sala.jsf')}")
                .build();

        cine.getElements().add(item8);
        DefaultMenuItem item9=DefaultMenuItem.builder()
                .value("Reserva")
                .ajax(true)
                .command("#{frmMenu.navegar('Reserva.jsf')}")
                .build();

        cine.getElements().add(item9);

        model.getElements().add(tipos );
        model.getElements().add(cine );
    }

    public void navegar(String formulario ) throws IOException {
        facesContext.getExternalContext().redirect(formulario );
    }

    public DefaultMenuModel getModel() {
        return model;
    }
}
