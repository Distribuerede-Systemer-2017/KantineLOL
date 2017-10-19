package server.endpoints;

import server.ServerImplDB.ImplDB;
import server.providers.UserProvider;
import com.google.gson.Gson;
import server.UserTable;
import server.models.Item;
import server.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

private static Conection conection;

@Path("/user")
public class UserEndpoint {

    private ImplDB implDB = new ImplDB();
   // private Digester dig = new Digester();
    private ArrayList<Item> items;
    private UserController ucontroller = new UserController();

@POST
@Path("/create")
    public Response createUser(String jsonUser) {
        int status = 0;
        try {
            User userCreated = new Gson().fromJson(jsonUser, User.class);
            boolean result = ucontroller.addUser(userCreated);
            status = 200;
        } catch (Exception e) {
            if (e.getClass() == BadRequestException.class) {
                status = 400;
            } else if (e.getClass() == InternalServerErrorException.class) {
                status = 500;
            }
        }
        return Response
                .status(status)
                .type("application/json")
                .entity("{\"userCreated\":\"true\"}")
                .build();
    }


        @GET
        @Path("{id}")
        public Response getUserById(@PathParam("id") int id) {

            //Lidt hjælp
            //
            UserTable userTable = UserTable.getInstance();
            User foundUser = userTable.findById(id);

            return Response
                    .status(200)
                    .type("application/json")
                    .entity(new Gson().toJson(foundUser))
                    .build();
        }

        @POST
        public Response createUser(String jsonUser) {

            UserTable userTable = UserTable.getInstance();
            User newUser = new Gson().fromJson(jsonUser, User.class);
            userTable.addUser(newUser);

            return Response
                    .status(200)
                    .type("application/json")
                    .entity("{\"userCreated\":\"true\"}")
                    .build();
        }

    }
}
