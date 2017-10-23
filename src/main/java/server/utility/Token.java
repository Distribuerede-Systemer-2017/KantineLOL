package server.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static server.ServerImplDB.ImplDB.getConnection;

public class Token {

    private Connection connection;

    public String getToken(String username, int userid) {
        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(username+"secret");
            token = JWT.create()
                    .withIssuer("auth0")
                    .sign(algorithm);
        } catch (UnsupportedEncodingException exception) {

        } catch (JWTCreationException exception) {

        }

        //Save token in DB
        PreparedStatement sql = null;
        try {
            sql = getConnection().prepareStatement("UPDATE lol.users SET token = ? WHERE id = ?");
            sql.setString(1, token);
            sql.setInt(2, userid);
            sql.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return token;
    }
}
