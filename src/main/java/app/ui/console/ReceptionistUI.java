package app.ui.console;

import app.controller.SelVaccinationCenterController;
import app.domain.model.VaccinationCenter;
import app.ui.console.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * UserInterface of Receptionist
 * @author Pedro Ferreira -> 1210825@isep.ipp.pt
 */

public class ReceptionistUI implements Runnable {

    private SelVaccinationCenterUI sel;

    public ReceptionistUI() { sel = new SelVaccinationCenterUI(); }

    @Override
    public void run() {
        sel.run();
        String vaccCenterID = sel.getSelectedVC();
        System.out.println("yes");

        List<MenuItem> options = new ArrayList<MenuItem>();

        options.add(new MenuItem("Register an SNS User", new SNSUserUI()));
        options.add(new MenuItem( "Register arrival of SNS user" , new RegisterArrivalUI(vaccCenterID) ));
        options.add(new MenuItem("Register vaccine schedule of SNS user" , new ScheduleVaccineUI(vaccCenterID) ));
        int option = 0;
        do {
            try {
                option = Utils.showAndSelectIndex(options, "\n\nReceptionist Menu:");
                if ((option >= 0) && (option < options.size())) {
                    options.get(option).run();
                }
            } catch (Exception e){System.out.print("Invalid option! Try again"+"\n"+e.getMessage());}
        }while(option != -1);
    }


}
