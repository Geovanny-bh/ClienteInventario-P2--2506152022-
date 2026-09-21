package sv.edu.utec.api;
import com.fasterxml.jackson.databind.ObjectMapper;
import sv.edu.utec.modelo.Producto;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class proveedorAPI {
    private static final String URL_BASE =
            "https://dummyjson.com/products";

    public List<Producto> obtenerProductos(int limite)
            throws IOException, InterruptedException {

        String url = URL_BASE
                + "?limit=" + limite
                + "&select=title,stock";

        HttpClient cliente = HttpClient.newHttpClient();

        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> respuesta =
                cliente.send(
                        solicitud,
                        HttpResponse.BodyHandlers.ofString()
                );

        int codigo = respuesta.statusCode();

        if (codigo != 200) {
            throw new IOException(
                    "Error al consultar la API. Código HTTP: " + codigo
            );
        }

        ObjectMapper mapper = new ObjectMapper();

        RespuestaProductos respuestaProductos =
                mapper.readValue(
                        respuesta.body(),
                        RespuestaProductos.class
                );

        List<Producto> productos = new ArrayList<>();

        for (productoApi productoApi :
                respuestaProductos.getProducts()) {

            productos.add(productoApi.aProducto());
        }

        return productos;
    }
}


