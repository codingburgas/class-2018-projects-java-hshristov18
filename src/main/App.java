package main;

import controllers.*;
import models.Category;
import repositories.*;
import services.*;

import java.util.concurrent.CancellationException;

public class App {
    public static void main( String... args ) {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);

        UserController userController = new UserController(userService);

        HomeController homeController = new HomeController(userController);
        homeController.run();
    }
}