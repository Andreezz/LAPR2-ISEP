package app.ui.console;
import app.controller.EmployeeController;
import app.domain.model.PasswordGenerator;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * User Interface of the UserStory: Register an Employee
 * @author Lourenço Mayol -> 1211206@isep.ipp.pt
 */

public class EmployeeUI implements Runnable
{
    private EmployeeController ctrl;



    /**
     * creates an instance of the class EmployeeController
     */
    public EmployeeUI() {ctrl = new EmployeeController();}

    /**
     * keeps the user operating the system, in a loop until he decides to exit the EmployeeUI
     * while he doesn't leave the employee menu the user has 4 options: register an employee, view the employee list, clear the employee list or exit.
     */
    @Override
    public void run() {
        //ctrl.serializeAll();
        boolean end = false;
        //System.out.println(ctrl.getEmployeesList());
        do {
            switch(employeeMenu()){
                case 0:
                    end = true;
                    break;
                case 1:
                    String role;
                    do {
                        role = chooseRole();
                    }while (role == null);
                    try {
                        createEmployee();
                        if (confirmEmployee()) {
                            String pwd = PasswordGenerator.getPassword();
                            if (ctrl.saveEmployee(role, pwd)) {
                                System.out.println("The employee was registered!\nThe employee's password is " + pwd);
                            }
                            else{
                                System.out.println("Employee is invalid!He was not saved!");
                            }
                        }
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                        System.out.println(ctrl.getEmployeesList());
                        break;
                case 3:
                        ctrl.clearEmployeeList();
                        System.out.println("Employees list was cleared!");
                        break;
            }
        }while(!end);
    }


    /**
     * prints a menu so the user can choose an option
     * @return an integer representing the option that the user chose
     */
    public int employeeMenu() {
        List<String> options = new ArrayList<String>();
        options.add("Register an employee");
        options.add("View employees list");
        options.add("Clear employees list");
        int option = 0;
        try {
            option = Utils.showAndSelectIndex(options, "\nSelect an option to continue:\n");
        }
        catch (Exception e){
            System.out.println("Invalid Option!Try Again!");
            return 0;
        }
        return option + 1;
    }

    /**
     * requests that the user types the employee's information
     * directs the employee's information to the controller so this can create an employee
     */
    public void createEmployee() {
         String name;
         String phoneNumber;
         String email;
         String cc;
         String address;
         name = Utils.readLineFromConsole("Type the employee's name: ");
         address = Utils.readLineFromConsole("\nType the employee's address: ");
         phoneNumber = Utils.readLineFromConsole("\nType the employee's phone number, this should include a valid indicative (91,93,96 or 95): ");
         email = Utils.readLineFromConsole("\nType the employee's email, this should be in the following format email@thing.thing: ");
         cc = Utils.readLineFromConsole("\nType the employee's citizen card number in the following format 11111111: ");
         ctrl.createEmployee(address, name, phoneNumber, email, cc);
    }

    /**
     * prints a menu so the user can choose the role that the employee will take
     * @return a String that represents the employee's role
     */
    public String chooseRole(){
        System.out.println("\nChoose a role for the employee you want to register.");
        List<String> options = new ArrayList<String>();
        options.add(Constants.ROLE_NURSE);
        options.add(Constants.ROLE_COORDINATOR);
        options.add(Constants.ROLE_RECEPTIONIST);
        int option = 0;
        try {
            option = Utils.showAndSelectIndex(options, "\n\nSelect an option to continue:");
        }
        catch (Exception e){
            System.out.println("Invalid option!Try Again!");
            return null;
        }
        switch(option + 1) {
            case 1:
                return Constants.ROLE_NURSE;
            case 2:
                return Constants.ROLE_COORDINATOR;
            case 3:
                return Constants.ROLE_RECEPTIONIST;
        }
        return null;
    }

    /**
     * prints the employee's information and asks the user if he wants to save this employee
     * the user decides by typing yes or no
     * @return true if the user wants to save the employee, otherwise, returns false
     */
    public boolean confirmEmployee(){
        System.out.println("Do you wish to save the employee with the following info?");
        System.out.println(ctrl.getEmployeeInfo());
        return Utils.confirm("Answer here with a yes or a no:");
    }
}


