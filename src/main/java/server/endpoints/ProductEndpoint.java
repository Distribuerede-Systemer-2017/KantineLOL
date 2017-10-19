package server.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

import com.google.gson.Gson;
import server.ServerImplDB.ImplDB;
import server.models.Product;
import server.providers.DrinkProvider;
import server.ServerImplDB.Kryptering;

@Path("/drink")
public class ProductEndpoint {

    @GET
    public Response getAllProducts() {



        ImplDB serverImplDB = new ImplDB();
        ArrayList<Product> allProducts = new DrinkProvider().getProducts();
        String json = new Gson().toJson(allProducts);
        String krypteret = Kryptering.encryptdecrypt(json);
        krypteret = new Gson().toJson(krypteret);


        return Response.status(200)
                .type("application/json")
                .entity(krypteret)
                .build();
    }

}
