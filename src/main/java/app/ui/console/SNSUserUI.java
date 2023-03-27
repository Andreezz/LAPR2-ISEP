package app.ui.console;

import app.controller.SNSUserController;
import app.domain.model.PasswordGenerator;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 * User Interface of the UserStory: As a Receptionist,I want to register an SNS User
 * @author Lourenço Mayol -> 1211206@isep.ipp.pt
 */
public class SNSUserUI implements Runnable{

    private SNSUserController ctrl;

    /**
     * creates an instance of the class SNSUserController
     */
    public SNSUserUI(){ctrl = new SNSUserController();}

    /** Keeps the administrator on a loop while he wants to register an SNS user
     */
    @Override
    public void run() {
        boolean end;
        do{
            try {
                createSNSUser();
                String pwd = PasswordGenerator.getPassword();
                if(confirmUser() && ctrl.saveSNSUserWithAuth(pwd)){
                    System.out.println("The SNS user was registered!\nThe user's password is " + pwd);
                }
                else{
                    System.out.println("SNS user is invalid!He was not saved!");
                }
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            end = exitMenu();
        }while(!end);
    }

    /**
     * requests that the administrator types the SNS user's information
     * directs the user's information to the controller so this can create a user
     */
    public void createSNSUser(){
        String name;
        String address;
        String gender;
        String phoneNumber;
        String email;
        String birthdate;
        String SNSNumber;
        String cc;
        name = Utils.readLineFromConsole("Type the SNS user's name: ");
        address = Utils.readLineFromConsole("\nType the SNS user's address: ");
        phoneNumber = Utils.readLineFromConsole("\nType the SNS user's phone number, this should include a valid indicative (91,93 or 96 or 95): ");
        email = Utils.readLineFromConsole("\nType the SNS user's email, this should be in the following format email@thing.thing: ");
        cc = Utils.readLineFromConsole("\nType the SNS user's citizen card number in the following format 11111111: ");
        do {
            gender = chooseGender();
        }while(gender == null);
        birthdate = Utils.readLineFromConsole("\nType the SNS user's birthdate, this should be in the following format DD/MM/YYYY: ");
        SNSNumber = Utils.readLineFromConsole("\nType the SNS user's SNS number, this should be a 9 digit number: ");
        ctrl.create(name, gender, birthdate, address, phoneNumber, email, SNSNumber, cc);
    }

    /** Prints a menu so the administrator can choose a gender for the SNS user that he is trying to create
     * @return a integer that corresponds to the gender option that the administrator chose, if the admin wrote an option that doesn't exist, the method returns null
     */
    public String chooseGender(){
        System.out.println("\nChoose a gender for the SNS user that you want to register.");
        List<String> options = new ArrayList<String>();
        options.add(Constants.SNS_USER_GENDER_FEMALE);
        options.add(Constants.SNS_USER_GENDER_MALE);
        options.add(Constants.SNS_USER_GENDER_OTHER);
        int option = 0;
        try {
            option = Utils.showAndSelectIndex(options, "\n\nSelect an option to continue:");
        }
        catch (Exception e){
            System.out.println("Invalid option!Try again!");
            return null;
        }
        switch(option + 1) {
            case 1:
                return Constants.SNS_USER_GENDER_FEMALE;
            case 2:
                return Constants.SNS_USER_GENDER_MALE;
            case 3:
                return Constants.SNS_USER_GENDER_OTHER;
        }
        return null;
    }

    /**
     * prints the SNS user's information and asks the administrator if he wants to save this user
     * the Administrator decides by typing yes or no
     * @return true if the administrator wants to save the user, otherwise, returns false
     */
    public boolean confirmUser(){
        System.out.println("Do you wish to save the SNS user with the following info?");
        System.out.println(ctrl.getSNSUserInfo());
        return Utils.confirm("Answer here with a yes or a no:");
    }

    public boolean exitMenu(){
        System.out.println("\nChoose to exit the SNS user menu or to register another SNS user.");
        List<String> options = new ArrayList<String>();
        options.add("Register another SNS user");
        options.add("Exit");
        int option = 0;
        try{
            option = Utils.showAndSelectIndex(options, "\nSelect an option to continue:");
        }
        catch (Exception e){
            System.out.println("Invalid option!Try again!");
            return false;
        }
        return option + 1 == 2;
    }


}
