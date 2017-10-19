package server.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

import com.google.gson.Gson;
import server.ServerImplDB.ImplDB;
import server.models.Product;;

@Path("/product")
public class ProductEndpoint {

    @GET
    public Response getAllProducts() {

        ImplDB serverImplDB = new ImplDB();
        ArrayList<Product> allProducts = new ImplDB().getProducts();
        return Response.status(200).type("application/json").entity(new Gson().toJson(allProducts)).build();



    }

}