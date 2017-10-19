package server.endpoints;

import com.google.gson.Gson;
import server.ServerImplDB.ImplDB;
import server.controller.UserController;
import server.models.User;
import server.utility.Digester;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

import server.providers.UserProvider;

@Path("/users")
public class UserEndpoint {

    private UserController userController = new UserController();
    private UserProvider userProvider = new UserProvider();

    /*@GET
    public Response getAllUsers() {
        ImplDB serverImplDB = new ImplDB();
        ArrayList<User> allUsers = new ImplDB().getUsers();
        return Response.status(200).type("application/json").entity(new Gson().toJson(allUsers)).build();
*/


    @POST
    @Path("/create")
    public Response createUser(String jsonUser) {


        int status = 0;
        try {
            User userCreated = new Gson().fromJson(jsonUser, User.class);
            boolean result = userController.addUser(userCreated);
            //boolean result = userController.addUser(userCreated);
            status = 200;
        } catch (Exception e)
        {
            if (e.getClass() == BadRequestException.class) {
                status = 400;
            } else if (e.getClass() == InternalServerErrorException.class){
                status = 500;
            }

        }
        return Response
                .status(status)
                .type("application/json")
                .entity("{\"userCreated\":\"true\"}")
                .build();

    }

    @Path("/login")
    @POST
    public Response authorizeUser(String data) throws Exception {
        User user = new Gson().fromJson(data, User.class);
        User userFound = userProvider.logIn(user.getUsername(), user.getPassword());

        if (userFound != null){
            return Response.status(200).entity(new Gson().toJson(userFound)).build();
        } else {
            return Response.status(400).entity("{\"user logged in\":\"false\"}").build();
        }

    }
}

