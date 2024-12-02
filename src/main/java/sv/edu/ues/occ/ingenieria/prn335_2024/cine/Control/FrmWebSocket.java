package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;


import jakarta.faces.push.Push;
import jakarta.faces.push.PushContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped

public class FrmWebSocket implements Serializable {

    @Inject
    @Push(channel="chepe")

    PushContext pushContext;

    public void enviarMensaje(){
        pushContext.send("Enviar hola "+System.currentTimeMillis());
    }

}
