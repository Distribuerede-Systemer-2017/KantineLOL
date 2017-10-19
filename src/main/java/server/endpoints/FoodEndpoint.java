package server.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

import com.google.gson.Gson;
import server.ServerImplDB.ImplDB;
import server.models.Product;;

import javax.ws.rs.Path;

@Path("/Food")
public class FoodEndpoint {

    @GET
    public Response getAllFoods() {

        ImplDB serverImplDB = new ImplDB();
        ArrayList<Product> allFoods = new ImplDB().getFoods();
        return Response.status(200).type("application/json").entity(new Gson().toJson(allFoods)).build();


}
}

