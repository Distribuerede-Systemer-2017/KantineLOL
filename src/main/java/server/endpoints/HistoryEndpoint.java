package server.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

import com.google.gson.Gson;
import server.ServerImplDB.ImplDB;
import server.models.Order;
import server.models.User;
import server.providers.HistoryProvider;
import server.providers.UserProvider;;

@Path("/history")
public class HistoryEndpoint {
    UserProvider userProvider = new UserProvider();

    @GET
    public Response getAllOrder(@HeaderParam("token") String token) throws Exception {
        User u = userProvider.getUserFromToken(token);
        if(u!=null && token!=null) {
            ImplDB serverImplDB = new ImplDB();
            ArrayList<Order> allOrders = new HistoryProvider().getOrders();

            return Response.status(200).type("application/json").entity(new Gson().toJson(allOrders)).build();
        } else {
            return Response.status(400).entity(new Gson().toJson("error")).build();
        }

    }




}

