package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import com.sun.source.tree.ModuleTree;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sala;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SalaBeanTest {


    @Test
    void findByTipoSala() {

        System.out.println("SalaBeanTest. findById");
        List<Sala> buscados= Arrays.asList(new Sala[]{new Sala(1),new Sala(2),new Sala(3),new Sala(4)});
        Query mockQuery= Mockito.mock(Query.class);
        Mockito.when(mockQuery.getResultList()).thenReturn(buscados);

        EntityManager mockEM = Mockito.mock(EntityManager.class);

        Mockito.when(mockEM.createQuery("Sala.findByIdTipoSala")).thenReturn(mockQuery);

        SalaBean cut = new SalaBean();
        cut.em=mockEM;

        List<Sala> encontrados=cut.findByTipoSala(1, 1, 1);

        assertEquals(buscados.size(), encontrados.size());


    }
}