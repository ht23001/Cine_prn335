package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.AsientoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.PeliculaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Asiento;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Pelicula;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Named
@SessionScoped
public class frmAsiento implements Serializable {


    @Inject
    AsientoBean dataBean;

    LazyDataModel<Asiento> modelo;

    Pelicula registro;

    @PostConstruct
    public void inicializar(){
        modelo=new  LazyDataModel<Asiento>() {

            @Override
            public String getRowKey(Asiento object) {
                if(object!=null && object.getIdAsiento()!=null){
                    return object.getIdAsiento().toString();
                }
                return null;
            }
            @Override
            public Asiento getRowData(String rowKey) {
                if(rowKey!=null && getWrappedData()!=null){
                    return getWrappedData().stream().filter(r->rowKey.equals(r.getIdAsiento().toString())).findFirst().orElse(null);

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
            public List<Asiento> load(int desde, int max, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {

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

    public AsientoBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(AsientoBean dataBean) {
        this.dataBean = dataBean;
    }

    public LazyDataModel<Asiento> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<Asiento> modelo) {
        this.modelo = modelo;
    }

    public Pelicula getRegistro() {
        return registro;
    }

    public void setRegistro(Pelicula registro) {
        this.registro = registro;
    }
}
