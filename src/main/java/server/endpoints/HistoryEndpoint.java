package server.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

import com.google.gson.Gson;
import server.ServerImplDB.ImplDB;
import server.models.Order;;

@Path("/history")
public class HistoryEndpoint {

    @GET
    public Response getAllProducts() {

        ImplDB serverImplDB = new ImplDB();
        ArrayList<Order> allOrders = new ImplDB().getOrders();

        return Response.status(200).type("application/json").entity(new Gson().toJson(allOrders)).build();


    }
}

