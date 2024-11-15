package sv.edu.ues.occ.ingenieria.prn335_2024.cine.Boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Control.facturaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.Entity.Factura;


import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Named
@SessionScoped
public class frmFactura implements Serializable {

    @Inject
    facturaBean dataBean;

    LazyDataModel<Factura> modelo;

    Factura registro;

    @PostConstruct
    public void inicializar(){
        modelo=new  LazyDataModel<Factura>() {

            @Override
            public String getRowKey(Factura object) {
                if(object!=null && object.getIdFactura()!=null){
                    return object.getIdFactura().toString();
                }
                return null;
            }

            @Override
            public Factura getRowData(String rowKey) {
                if(rowKey!=null && getWrappedData()!=null){
                    return getWrappedData().stream().filter(r->rowKey.equals(r.getIdFactura().toString())).findFirst().orElse(null);

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
            public List<Factura> load(int desde, int max, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {

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





}
