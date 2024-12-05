package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.websocket.server;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.websocket.Session;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
@Named
public class ManejadorSesionesWS implements Serializable {


    // GUARDAR SESIONES, ELIMIMNAR SESIONES --

    public static Set<Session> SESIONES = new HashSet<>();

    public void agregarSesion(Session session){
        SESIONES.add(session);

    }

    public void eliminarSesion(Session session){
        SESIONES.remove(session);
    }

    public  Set<Session> getSESIONES() {
        return SESIONES;
    }
}
