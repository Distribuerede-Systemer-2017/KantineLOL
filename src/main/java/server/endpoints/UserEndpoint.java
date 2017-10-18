package server.endpoints;

import com.google.gson.Gson;
import server.ServerImplDB.ImplDB;
import server.models.Product;
import server.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/users")
public class UserEndpoint {

    @GET
    public Response getAllUsers() {

        ImplDB serverImplDB = new ImplDB();
        ArrayList<User> allUsers = new ImplDB().getUsers();
        return Response.status(200).type("application/json").entity(new Gson().toJson(allUsers)).build();

    }

}
