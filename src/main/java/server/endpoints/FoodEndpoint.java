package server.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


import com.google.gson.Gson;
import server.ServerImplDB.ImplDB;
import server.ServerImplDB.Kryptering;
import server.models.Product;
import server.providers.FoodProvider;
import server.utility.Globals;;

import javax.ws.rs.Path;

@Path("/Food")
public class FoodEndpoint {

    @GET
    public Response getAllFoods() {

        //Globals.log.writeLog(getClass().getName(), this, "All foods shown", 1);

        ArrayList<Product> allProducts = new FoodProvider().getFoods();
        String json = new Gson().toJson(allProducts);
        String krypteret = Kryptering.encryptdecrypt(json);
        krypteret = new Gson().toJson(krypteret);

        return Response.status(200).type("application/json")
                .entity(krypteret)
                .build();


    }
}

