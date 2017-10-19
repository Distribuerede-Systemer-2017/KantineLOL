package server.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import com.google.gson.Gson;
import server.ServerImplDB.Kryptering;
import server.models.Product;
import server.providers.FoodProvider;

@Path("/food")
public class FoodEndpoint {

    @GET
    public Response getAllFoods() {

        ArrayList<Product> allFoods = new FoodProvider().getFoods();
        String string = new Gson().toJson(allFoods);

        String encrypted = Kryptering.encryptdecrypt(string);
        encrypted = new Gson().toJson(encrypted);

        return Response.status(200).type("application/json")
                .entity(encrypted)
                .build();
    }
}

