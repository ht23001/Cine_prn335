package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.websocket.server;

import jakarta.inject.Inject;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint("/notificadortiposala")
public class TipoSalaEndpoint implements Serializable {


    @Inject
    ManejadorSesionesWS manejadorSesiones;

    @OnOpen  // se invoca cuando inicia session
    public void abrir(Session session){
        System.out.println("conecto");
        manejadorSesiones.agregarSesion(session);
    }

    @OnMessage // enviar mensaje
    public void PropagarMensaje(Session sesion, String mensaje) throws IOException {

        for(Session session: this.manejadorSesiones.getSESIONES()){

            if(sesion!=null && sesion.isOpen()){

            }else{

                try {

                    sesion.getBasicRemote().sendText("goku");
                }catch (Exception e){
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(),  e);
                }
            }

        }


    }


    // cierra sesion elimando solo si el cliente cierra sesion, no si pierde conexion
    @OnClose
    public void cerrar(Session session){
        manejadorSesiones.eliminarSesion(session);

    }

}
