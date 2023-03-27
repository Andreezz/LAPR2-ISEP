package app.ui.console;

import app.controller.VaccinationCenterController;

import app.domain.model.Schedule;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class VaccinationCentreUI implements Runnable {

    private VaccinationCenterController ctrl;


    /**
     * creates an instance of the class VaccinationCenterController
     */


    public VaccinationCentreUI() {
        this.ctrl = new VaccinationCenterController();

    }
    /**
     * Menu for the admin to choose witch tech the vaccine will have.
     *
     * @return String that represents tech
     */
    public String ChooseVcType() {
        System.out.println("\nChoose the Vaccination Center Type you want to register.");
        List<String> options = new ArrayList<String>();
        options.add(Constants.VC_TYPE_1);
        options.add(Constants.VC_TYPE_2);
        int option = 0;
        option = Utils.showAndSelectIndex(options, "\n\nSelect an option to continue:");
        switch (option + 1) {
            case 1:
                return Constants.VC_TYPE_1;
            case 2:
                return Constants.VC_TYPE_2;
        }
        return null;
    }


    public void run() {
        boolean cont = true;
        do {

            try {
                String vctype = ChooseVcType();
                String name = Utils.readLineFromConsole("Please enter the name of the new Vaccination Center:");
                String email = Utils.readLineFromConsole("Please enter the email of the new Vaccination Center:");
                String phoneNumber = Utils.readLineFromConsole("Please enter the Phone Number of the new Vaccination Center: ");
                int faxNumber = Utils.readIntegerFromConsole("Please enter the Fax Number of the new Vaccination Center:");
                String address = Utils.readLineFromConsole("Please enter the address of the new Vaccination Center:");
                String webAddress = Utils.readLineFromConsole("Please enter the web address of the new Vaccination Center:");
                String openHour = Utils.readLineFromConsole("Please enter the Opening time of the new Vaccination Center: (format: hh:mm)");
                String closeHour = Utils.readLineFromConsole("Please enter the Closing time of the new Vaccination Center: (format: hh:mm)");
                Double slotDuration = Utils.readDoubleFromConsole("Please enter the slot duration of the new Vaccination Center:");
                int maxVaccines = Utils.readIntegerFromConsole("Please enter the max number of vaccines per slot of the new Vaccination Center:");

                ctrl.createVaccinationCenter(name,email,phoneNumber,address,webAddress,faxNumber,vctype,slotDuration,maxVaccines, new Schedule(openHour,closeHour));

                cont = Utils.confirm("The following Vaccination Center was created do you want to save? \n Yes or No?\n" + ctrl.getVc().toString());
            } catch (Exception e) {
                System.out.println("Vaccination Center was not created because of the following error!!  --> " + e.getMessage());
            }




            if (cont) {

                if (ctrl.saveVaccinationCenter()) {
                    System.out.println("The Vaccination Center was saved with success");

                }

            } else {
                System.out.println("Couldn't save the Vaccination Center please try again ");

            }


        } while (!cont);


        Utils.confirm("Do you want to list the Vaccination Center's already saved? \n Yes or No?\n");
        System.out.println(ctrl.getToString());
    }
}





