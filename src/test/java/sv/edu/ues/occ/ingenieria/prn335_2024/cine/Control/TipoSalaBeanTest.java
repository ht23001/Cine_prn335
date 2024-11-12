package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.TipoSala;

import static org.junit.jupiter.api.Assertions.*;

class TipoSalaBeanTest {

   @Test
   void create(){
       System.out.println("TipoSalaBeanTest: create");
       TipoSala nuevo= new TipoSala();
       EntityManager mockEM= Mockito.mock(EntityManager.class);
       TipoSalaBean cut=new TipoSalaBean();

       /*
       assertThrows(IllegalArgumentException.class, ()->{cut.Create(null);});

       assertThrows(IllegalArgumentException.class, ()->{cut.Create(nuevo);});
      */

       cut.em=mockEM;
       cut.Create(nuevo);

   }

    @Test
    void findById() {
        System.out.println("TipoSalaBeanTest: findById");

        final Integer idEsperado = 1;
        TipoSala esperado = new TipoSala(idEsperado);
        TipoSalaBean cut = new TipoSalaBean();


        EntityManager mock = Mockito.mock(EntityManager.class);
        Mockito.when(mock.find(TipoSala.class, idEsperado)).thenReturn(esperado);
        cut.em = mock;

        TipoSala resultado = cut.findById(idEsperado);


        assertNotNull(resultado);
        assertEquals(esperado, resultado);
    }

}