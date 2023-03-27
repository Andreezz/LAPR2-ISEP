package app.ui.console;


/**
 * imports that are necessary to the correct functionality of the controller class
 */

import app.domain.model.VaccineType;
import app.ui.console.utils.Utils;
import app.controller.RegisterAdReaController;
import app.domain.model.AdverseReac;
import app.domain.stores.AdverseReacStore;
import app.domain.shared.Constants;



import java.util.ArrayList;
import java.util.List;

/**
 * UI of the UserStory: Register a SNS User's adverse reactions
 *
 * @author Miguel Moreira (1211240) - 1DN
 */

public class AdverseReacUI implements Runnable {


    private RegisterAdReaController ctrl;

    public AdverseReacUI(String vc) {


        ctrl = new RegisterAdReaController();

    }

    public AdverseReacUI() {

    }


    /**
     * adding of options(functions) by what the User can select one
     * @return a arraylist with the option by what the User will be guven the choice to choose one
     */

    public int AdreaMenu() {
        List<String> options = new ArrayList<String>();
        options.add("Register adverse reactions");
        options.add("Consult the currently registered SNS user's adverse reactions");
        options.add("Delete the SNS User's adverse reactions list");
        options.add("Delete a specific SNS user´s adverse reaction");
        int option = 0;
        option = Utils.showAndSelectIndex(options, "\nSelect an option to continue:\n");
        return option+1;
    }



    /**
     * The user will be kept in a loop until he/she decides to exit NewVaccinesUI
     * while he doesn't leave the vaccine menu the user has 5 options: register a new vaccine, get the vaccine list, clear the vaccine list, clear a specific vaccine or exit
     */
    @Override
    public void run() {
        boolean end = false;
        do {
            switch (AdreaMenu()) {
                case 0:
                    end = true;
                    break;
                case 1:
                    boolean cont = true;

                    String snsnumber;

                    String adversereactions;

                     snsnumber= Utils.readLineFromConsole("Type the SNS User's number: ");
                    adversereactions = Utils.readLineFromConsole("Type the adverse reactions: ");

                    /**
                     * creates the SNS User's adverse reactions registration
                     */

                    ctrl.createAdRea(snsnumber,adversereactions);


                        if (ctrl.validateAdRea(ctrl.getAdversereac())) {
                            System.out.println("The SNS User´s adverse reactions were saved with success");
                            ctrl.saveAdRea(snsnumber, adversereactions);
                        } else {
                            System.out.println("Couldn't save the SNS User´s adverse reactions please try again ");
                        }
                    /**
                     * case 2 will give the User the choice to get the Adverse Reactions full list
                     */
                case 2:
                    System.out.println(ctrl.getAdReaList());
                    break;
                /**
                 * case 3 will give the User the choice to clear the Adverse Reactions list
                 */
                case 3:
                    ctrl.clearAdRea();
                    System.out.println("SNS User´s adverse reactions list was cleared!");
                    break;
                /**
                 * case 4 will give the User the choice to clear one specific SNS User's adverse reaction configuration from the list
                 */

                    case 4:
                    System.out.println(ctrl.getAdReaList());
                    int g = Utils.readIntegerFromConsole("Select the SNS User´s adverse reaction information that you want to remove: ");
                    ctrl.RemoveOneAdRea(g);
                    System.out.println("SNS User´s adverse reactions were cleared");
            }

        }
        while (!end);

    }

}
