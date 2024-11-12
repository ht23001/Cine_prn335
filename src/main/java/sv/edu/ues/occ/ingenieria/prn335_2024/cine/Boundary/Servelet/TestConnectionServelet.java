package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.Servelet;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.pruebaConexion;

import java.io.IOException;

@WebServlet("/testConnection")
public class TestConnectionServelet extends HttpServlet{

    @Inject
    pruebaConexion pruebaConexion;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        pruebaConexion.testConnection();
        resp.getWriter().write("Connection test complete. ESTA GOD YA NO???");
    }
}

