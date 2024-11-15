package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.PeliculaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Pelicula;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Named
@SessionScoped
public class frmPelicula implements Serializable {

  @Inject
    PeliculaBean dataBean;

  LazyDataModel<Pelicula> modelo;

  Pelicula registro;

     @PostConstruct
    public void inicializar(){
        modelo=new  LazyDataModel<Pelicula>() {

            @Override
            public String getRowKey(Pelicula object) {
                if(object!=null && object.getIdPelicula()!=null){
                    return object.getIdPelicula().toString();
                }
                return null;
            }
            @Override
            public Pelicula getRowData(String rowKey) {
                if(rowKey!=null && getWrappedData()!=null){
                    return getWrappedData().stream().filter(r->rowKey.equals(r.getIdPelicula().toString())).findFirst().orElse(null);

                }
                return null;
            }

            @Override
            public int count(Map<String, FilterMeta> map) {

                try {

                    return  (int)dataBean.count();
                }catch (Exception e){
                    e.printStackTrace();
                }
                return 0;
            }

            @Override
            public List<Pelicula> load(int desde, int max, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {

                try {
                    if(desde>=0 && max>0){
                        return dataBean.findRange(desde,max);

                    }
                }catch (Exception e){

                    e.printStackTrace();
                }

                return List.of();
            }
        };

    }


    public PeliculaBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(PeliculaBean dataBean) {
        this.dataBean = dataBean;
    }

    public LazyDataModel<Pelicula> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<Pelicula> modelo) {
        this.modelo = modelo;
    }

    public Pelicula getRegistro() {
        return registro;
    }

    public void setRegistro(Pelicula registro) {
        this.registro = registro;
    }

}
