package controllers;

import models.User;

import java.text.ParseException;
import java.util.Scanner;

public class HomeController {
    public static User loggedUser = null;
    private UserController userController;


    public HomeController(UserController userController)
    {
        this.userController = userController;
    }

    private void printMenu() {
        System.out.println("WELCOME TO SACP APP!");
        System.out.println("1. Click 1 to Log In");
        System.out.println("2. Click 2 to Register");
        System.out.println("3. Click 3 to Shut Down");
    }

    private void printAdminMenu() {
        System.out.println("Welcome to the SACP app, dear admin!");
        System.out.println("1. User management");

    }

    private void getCommand() {
        Scanner sc = new Scanner(System.in);
        printMenu();

        Integer command = Integer.parseInt(sc.nextLine());
        while(command!=3) {
            switch(command) {
                case 1:
                    userController.login();
                    break;
                case 2:
                    userController.register();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Wrong command!");
            }
            if(HomeController.loggedUser!=null) {
                if(HomeController.loggedUser.getAdmin()) {
                    getAdminCommand();
                }
                else{
                    getUserCommand();
                }
            }
            printMenu();

            command = Integer.parseInt(sc.nextLine());
        }

    }

    private void getAdminCommand() {
        Scanner sc = new Scanner(System.in);
        printAdminMenu();

        Integer command = Integer.parseInt(sc.nextLine());
        while(command!=6) {
            switch(command) {
                case 1:
                    userController.printManagement();
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong command!");
            }

            printAdminMenu();

            command = Integer.parseInt(sc.nextLine());
        }
    }