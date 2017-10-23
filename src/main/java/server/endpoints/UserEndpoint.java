package server.endpoints;

import com.google.gson.Gson;
import server.ServerImplDB.ImplDB;
import server.controller.UserController;
import server.models.User;
import server.models.Order;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

import server.providers.UserProvider;
import server.utility.Token;

@Path("/users")
public class UserEndpoint {

    private UserController userController = new UserController();
    private UserProvider userProvider = new UserProvider();


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
        Token token = new Token();
        User user = new Gson().fromJson(data, User.class);
        User userFound = userProvider.authorizeUser(user.getUsername(), user.getPassword());

        if (userFound != null){
            String authToken = token.getToken(user.getUsername(), userFound.getId());

            return Response.status(200).entity(new Gson().toJson(authToken)).build();
        } else {
            return Response.status(400).entity("Error").build();
        }

    }

    @Path("/logout")
    @POST
    public Response logout (String data) throws Exception {
        Token token = new Token();

        User userFound = userProvider.getUserFromToken(data);

        if (userProvider.deleteToken(userFound.getId())) {
            return Response.status(200).entity("Logged out").build();

        } else {
            return Response.status(400).entity("Error").build();
        }
    }


    @Path("/order")
    @POST
    public Response createOrder(String data) throws Exception {
        Order order = new Gson().fromJson(data, Order.class);
        if(userProvider.createOrder(order)) {
            return Response.status(200).type("application/json").entity(new Gson().toJson(order)).build();
        } else {
            return null;
        }

    }

    @Path("/order/{orderId}")
    @POST
    public Response addProductToOrder(@PathParam("orderId") int orderId, String data) throws Exception {
        if(userProvider.addProductToOrder(data, orderId)) {
            return Response.status(200).build();
        } else {
            return Response.status(400).build();
        }
    }

    }

}