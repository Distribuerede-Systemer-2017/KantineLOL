package logic.controller;

import server.models.User;
import server.providers.UserProvider;

import java.util.ArrayList;

public class UserController {
    public UserController

    public User validateUserCreation(String password, String username) throws IllegalArgumentException {

        User validatedUser = new User(password, username);



        String[] emailSplit;
        emailSplit = username.split("@");
        String username = emailSplit[0];
        String emailDomain = emailSplit[1];

        if (!emailDomain.contains("cbs.dk") && !emailDomain.contains("student.cbs.dk")
                ) {
            System.out.print("e-mail er ikke korrekt\n");
            System.out.print(emailDomain);
            throw new IllegalArgumentException("domænet er forkert");
        }


        return validatedUser;


        //NOTE: Jeg havde glemt at man ikke skal tage højde for e-mail fordi vi bruger USERNAME og ikke Email

    }
}