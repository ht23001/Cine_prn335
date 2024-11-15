package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.SucursalBean;

import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Sucursal;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Named
@SessionScoped
public class frmSucursal implements Serializable {

    @Inject
    SucursalBean dataBean;

    LazyDataModel<Sucursal> modelo;

    Sucursal registro;

    @PostConstruct
    public void inicializar(){
        modelo=new  LazyDataModel<Sucursal>() {

            @Override
            public String getRowKey(Sucursal object) {
                if(object!=null && object.getIdSucursal()!=null){
                    return object.getIdSucursal().toString();
                }
                return null;
            }
            @Override
            public Sucursal getRowData(String rowKey) {
                if(rowKey!=null && getWrappedData()!=null){
                    return getWrappedData().stream().filter(r->rowKey.equals(r.getIdSucursal().toString())).findFirst().orElse(null);

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
            public List<Sucursal> load(int desde, int max, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {

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


    public SucursalBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(SucursalBean dataBean) {
        this.dataBean = dataBean;
    }

    public LazyDataModel<Sucursal> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<Sucursal> modelo) {
        this.modelo = modelo;
    }

    public Sucursal getRegistro() {
        return registro;
    }

    public void setRegistro(Sucursal registro) {
        this.registro = registro;
    }
}
