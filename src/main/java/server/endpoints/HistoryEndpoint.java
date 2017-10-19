package server.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

import com.google.gson.Gson;
import server.ServerImplDB.ImplDB;
import server.models.Order;
import server.providers.HistoryProvider;;

@Path("/order")
public class HistoryEndpoint {

    @GET
    public Response getAllOrder() {

        ImplDB serverImplDB = new ImplDB();
        ArrayList<Order> allOrders = new HistoryProvider().getOrders();

        return Response.status(200).type("application/json").entity(new Gson().toJson(allOrders)).build();


    }




}

