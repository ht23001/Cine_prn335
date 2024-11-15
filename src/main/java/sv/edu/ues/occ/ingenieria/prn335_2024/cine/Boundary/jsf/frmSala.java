package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.SalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sala;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class frmSala implements Serializable {


    @Inject
    SalaBean dataBean;

    LazyDataModel<Sala> modelo;

      Sala registro;

    @PostConstruct
    public void inicializar(){
        modelo=new  LazyDataModel<Sala>() {

            @Override
            public String getRowKey(Sala object) {
                if(object!=null && object.getIdSala()!=null){
                    return object.getIdSala().toString();
                }
                return null;
            }

            @Override
            public Sala getRowData(String rowKey) {
                if(rowKey!=null && getWrappedData()!=null){
                    return getWrappedData().stream().filter(r->rowKey.equals(r.getIdSala().toString())).findFirst().orElse(null);

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
            public List<Sala> load(int desde, int max, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {

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

    public SalaBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(SalaBean dataBean) {
        this.dataBean = dataBean;
    }

    public LazyDataModel<Sala> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<Sala> modelo) {
        this.modelo = modelo;
    }

    public Sala getRegistro() {
        return registro;
    }

    public void setRegistro(Sala registro) {
        this.registro = registro;
    }
}
