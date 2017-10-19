package server.endpoints;

import com.google.gson.Gson;
import server.UserTable;
import server.models.User;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/create")
public class CreateUserEndpoint {





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
