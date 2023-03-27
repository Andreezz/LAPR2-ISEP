package app.ui.console;

import app.controller.RegisterArrivalController;
import app.controller.SNSUserController;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;
import app.ui.console.utils.Utils;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * UserInterface of the UserStory: Register arrival of SNS user
 * @author Pedro Ferreira -> 1210825@isep.ipp.pt
 */

public class RegisterArrivalUI implements Runnable {

    private RegisterArrivalController ctrl;
    private String vaccCenterID;

    public RegisterArrivalUI( String vaccCenterID ) {
        ctrl = new RegisterArrivalController(vaccCenterID);
        this.vaccCenterID = vaccCenterID;
    }

    @Override
    public void run() {
        boolean success = false;
        boolean success2 = false;
        int snsNum;
        String time;
        SNSUser user = null;

        do {
            System.out.println("\n| Verifying vaccine schedule of SNS user... |");
            snsNum = Utils.readIntegerFromConsole("Insert SNS number: ");

            if (ctrl.snsUserExists(Integer.toString(snsNum)) != null) {
                user = ctrl.snsUserExists(Integer.toString(snsNum));
                success2 = true;
            } else {
                System.err.println("\nError 100 : The SNS user with the SNS number " + snsNum + " doesn't exist in the system");
            }
        }while(!success2);

        if( checkVaccineSchedule(user, String.valueOf(LocalDate.now())) ) {
            System.out.println("\n >>The SNS user has a vaccine schedule for that day<<");

            do {
                try {

                    System.out.println("\n| Registration of the SNS user arrival |");
                    time = Utils.readLineFromConsole( "Time of arrival: format(HH:mm)" );

                    if( ctrl.createArrival(user, String.valueOf(LocalDate.now()), time) ) {
                        String confirmation = dataConfirmation(snsNum, vaccCenterID, String.valueOf(LocalDate.now()), time);
                        if(confirmation.equals("yes") ) {
                            if(ctrl.saveArrival() ) {
                                System.out.println( "\n>>The arrival was registered successfully! :)<<" );
                            }
                        }else{
                            System.out.println("\n>>Operation aborted<< X_X");
                        }
                    }else{
                        System.err.println("\nError 666 : No duplicate entries is allowed for the same SNS user on the same day or vaccine period!");
                        System.err.println("Registration of arrival failed...");
                    }
                    success = true;
                }catch(IllegalArgumentException | DateTimeParseException e) {
                    System.err.println( "Error 404 : Arrival Invalid! Enter data again" );
                }

            }while(!success);

        }else{
            System.out.println(">>The SNS user doesn't have a vaccine scheduled for this day and time<<");
        }
    }

    public String dataConfirmation( int snsNum, String centerName, String date, String time ) {
        List<String> options = new ArrayList<String>();
        int option;
        System.out.println("\nArrival Data Confirmation:");
        System.out.println("\nSNS number: " + snsNum);
        System.out.println("Vaccination Center: " + centerName);
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);

        options.add("yes");
        options.add("no");
        option = Utils.showAndSelectIndex(options, "\nSelect an option:");

        return options.get(option);
    }

    public boolean checkVaccineSchedule( SNSUser snsNum, String date ) {
        return ctrl.checkVaccineSchedule(snsNum, date);
    }
}
