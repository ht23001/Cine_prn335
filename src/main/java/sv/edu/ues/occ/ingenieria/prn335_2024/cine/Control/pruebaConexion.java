package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@Stateless
public class pruebaConexion {

        @Resource(lookup = "jdbc/cine")
        DataSource dataSource;

        public void testConnection() {
            try (Connection conn = dataSource.getConnection()) {
                System.out.println("Connection successful!");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
}
