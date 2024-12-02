package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;


public abstract class AbscractDataPersistence <T> {
    Class tipoDatos;

    public abstract EntityManager getEntityManager();

    public AbscractDataPersistence(Class tipodatos){
        this.tipoDatos = tipodatos;
    }

    // METODO GENERAL PARA CREAR UN NUEVO ELEMENTO ej tipoSala

    public void Create(T entity){
       EntityManager em=null;
       if(entity==null){
           throw new IllegalArgumentException("La entidad a persistir no puede ser nulo");
       }
       try{
           em=getEntityManager();
           if(em==null){
               throw new IllegalStateException("Error al acceder al repositorio");
           }
           em.persist(entity);
       }catch (Exception e){
           throw new IllegalStateException("Error al acceder al repositorio ", e);
       }
    }

    // METODO GENERAL PARA ACTUALIZAR UN ELEMENTO ej tipoSala

    public T Update(T entity){
          if(entity==null){
              throw new IllegalArgumentException( "La entidad a actualizar no puede ser nula");

          }
        EntityManager em=getEntityManager();
          if(em==null){
              throw new IllegalStateException("Error al acceder al repositorio");
        }
          return em.merge(entity);
    }


    // METODO GENERAL PARA ELIMINAR UN ELEMENTO EXISTENTE

    public void Delete(final Object id){
        if(id==null){
            throw new IllegalArgumentException("El id es nulo");
        }
        EntityManager em=getEntityManager();
        if(em==null){
            throw new IllegalArgumentException("Error al acceder al repositorio");
        }
        T entity = (T) em.find(tipoDatos, id);

        if(entity!=null){
            em.remove(entity);
        }else{
            throw new IllegalArgumentException("Entidad no encontrada");
        }
    }

    public T findById(final Object id) {
        EntityManager em=null;

        if(id==null){
         throw new IllegalArgumentException("Parametro no valido: idTipoSala");
        }
        try{
            em=getEntityManager();

            if(em==null){
                throw new IllegalStateException("Error al acceder al repositorio");
            }
        }catch(Exception ex) {

            throw new IllegalStateException("Error al acceder al repositorio"+ex);
        }
        return (T)em.find(tipoDatos, id);

    }

    public List<T> findRange(int first, int max) throws IllegalStateException, IllegalArgumentException{
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery q = cb.createQuery(tipoDatos);
        Root<T> raiz = q.from(tipoDatos);
        CriteriaQuery cq= q.select(raiz);
        getEntityManager().createQuery(cq);
        TypedQuery query= getEntityManager().createQuery(cq);
        query.setFirstResult(first);
        query.setMaxResults(max);
        return query.getResultList();
    }

    public long Count() {
        EntityManager em = getEntityManager();
        if (em == null) {
            throw new IllegalStateException("Error al acceder al repositorio");
        }

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<T> root = cq.from(tipoDatos);
        cq.select(cb.count(root));

        TypedQuery<Long> query = em.createQuery(cq);
        return query.getSingleResult();
    }


}
