package server.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

import com.google.gson.Gson;
import server.ServerImplDB.ImplDB;
import server.models.Product;
import server.providers.FoodProvider;;
import server.ServerImplDB.Kryptering;

import javax.ws.rs.Path;

@Path("/food")
public class FoodEndpoint {

    @GET
    public Response getAllFoods() {

        ArrayList<Product> allFoods = new FoodProvider().getFoods();
        String encrypt = new Gson().toJson(allFoods);
        encrypt = Kryptering.encryptdecrypt(encrypt);

        return Response.status(200).type("application/json").entity(encrypt).build();
    }
}

