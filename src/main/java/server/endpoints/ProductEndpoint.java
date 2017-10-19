package server.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

import com.google.gson.Gson;
import server.ServerImplDB.ImplDB;
import server.models.Product;
import server.providers.DrinkProvider;;
import server.ServerImplDB.Kryptering;

@Path("/drink")
public class ProductEndpoint {

    Kryptering kryptering = new Kryptering();


    @GET
    public Response getAllProducts() {

        String json = new Gson().toJson(getAllProducts());
        String krypteret = Kryptering.encryptdecrypt(json);

        ImplDB serverImplDB = new ImplDB();
        ArrayList<Product> allProducts = new DrinkProvider().getProducts();
        return Response.status(200)
                .type("application/json")
                .entity(krypteret)
                .build();



    }

}
