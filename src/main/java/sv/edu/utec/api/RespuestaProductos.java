package sv.edu.utec.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespuestaProductos {

    private List<productoApi> products;

    public RespuestaProductos() {
    }

    public List<productoApi> getProducts() {
        return products;
    }

    public void setProducts(List<productoApi> products) {
        this.products = products;
    }
}