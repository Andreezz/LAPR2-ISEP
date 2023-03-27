package app.ui.console;


import app.controller.VaccineTypeController;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class VaccineTypeUI implements Runnable {
    private VaccineTypeController ctrl;

    /**
     * creates an instance of the class VaccineTypeController
     */

    public VaccineTypeUI() {
        this.ctrl = new VaccineTypeController();

    }

    /**
     * Menu for the admin to choose witch tech the vaccine will have.
     *
     * @return String that represents tech
     */
    public String ChooseTech() {
        String[] array = new String[2];
        System.out.println("\nChoose the vaccine tech you want to register.");
        List<String> options = new ArrayList<String>();
        options.add(Constants.TECH_1);
        options.add(Constants.TECH_2);
        options.add(Constants.TECH_3);
        options.add(Constants.TECH_4);
        options.add(Constants.TECH_5);
        options.add(Constants.TECH_6);
        int option = 0;
        option = Utils.showAndSelectIndex(options, "\n\nSelect an option to continue:");
        switch (option + 1) {
            case 1:
                return Constants.TECH_1;
            case 2:
                return Constants.TECH_2;
            case 3:
                return Constants.TECH_3;
            case 4:
                return Constants.TECH_4;
            case 5:
                return Constants.TECH_5;
            case 6:
                return Constants.TECH_6;
        }
        return null;
    }

    public void run() {
        boolean cont = true;
        do {
            try {


                String code = Utils.readLineFromConsole("Please enter the code of the new Vaccine Type:(5 numbers only)");
                String type;
                String tech = ChooseTech();
                if (tech == Constants.TECH_1) {
                    type = Constants.TECH_1_TYPE;
                    ctrl.createVaccineType(code, type, tech);
                } else if (tech == Constants.TECH_2) {
                    type = Constants.TECH_2_TYPE;
                    ctrl.createVaccineType(code, type, tech);
                } else if (tech == Constants.TECH_3) {
                    type = Constants.TECH_3_TYPE;
                    ctrl.createVaccineType(code, type, tech);
                } else if (tech == Constants.TECH_4) {
                    type = Constants.TECH_4_TYPE;
                    ctrl.createVaccineType(code, type, tech);
                } else if (tech == Constants.TECH_5) {
                    type = Constants.TECH_5_TYPE;
                    ctrl.createVaccineType(code, type, tech);
                } else if (tech == Constants.TECH_6) {
                    type = Constants.TECH_6_TYPE;
                    ctrl.createVaccineType(code, type, tech);
                }


                cont = Utils.confirm("The following Vaccine type was created do you want to save? \n----> Yes or No? <----\n" + ctrl.getVt().toString());
            } catch (Exception e) {
                System.out.println("The Vaccine Type was not created because of the following error!! -->" + e.getMessage());
            }
            if (cont) {

                if (ctrl.saveVaccinetype()) {
                    System.out.println("The Vaccine type was saved with success");

                }

            } else {
                System.out.println("Couldn't save the Vaccine type please try again ");

            }


        } while (!cont);


    }


}



