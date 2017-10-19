package server.endpoints;

import com.google.gson.Gson;
import server.ServerImplDB.ImplDB;
import server.models.Product;
import server.models.User;
import server.providers.UserProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/users")
public class UserEndpoint {
    UserProvider userProvider = new UserProvider();

    /*@GET
    public Response getAllUsers() {

        ImplDB serverImplDB = new ImplDB();
        ArrayList<User> allUsers = new ImplDB().getUsers();
        return Response.status(200).type("application/json").entity(new Gson().toJson(allUsers)).build();

    }*/

    @Path("/login")
    @POST
    public Response authorizeUser(String data) throws Exception {
        User user = new Gson().fromJson(data, User.class);
        User userFound = userProvider.logIn(user.getUsername(), user.getPassword());

        if (userFound != null){
            return Response.status(200).entity(new Gson().toJson(userFound)).build();
        } else {
            return Response.status(400).entity("Error").build();
        }

    }

}
