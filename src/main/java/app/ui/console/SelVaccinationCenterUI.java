package app.ui.console;

import app.controller.SelVaccinationCenterController;
import app.mappers.dto.VaccinationCenterDTO;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class SelVaccinationCenterUI implements Runnable {

    private SelVaccinationCenterController ctrl;
    private String vaccCenterId;

    public SelVaccinationCenterUI() {
        ctrl = new SelVaccinationCenterController();
    }

    @Override
    public void run() {

        List<VaccinationCenterDTO> vaccCentersDTO = ctrl.getVaccinationCenters();

        int selVacc = 0;

        List<String> options = new ArrayList<String>();

        System.out.println("| List of Vaccination Centers: |");
        for( VaccinationCenterDTO i : vaccCentersDTO ) {
            options.add( i.getName() );
            System.out.println("Name: " + i.getName() + "\nEmail: "  + i.getEmail() + "\nPhone Number: " + i.getPhoneNumber());
            System.out.println("----------------------");
        }
        boolean success = false;
        do {
            try {
                selVacc = Utils.showAndSelectIndex(options, "\nSelect the vaccination center from the list below :");
                success = true;
            } catch (Exception e) {
                System.out.println("Invalid option!Try Again!");
            }
        }while(!success);

        if(ctrl.exists(options.get(selVacc))) {
            vaccCenterId = options.get(selVacc);
        }else{
            System.out.println("The selected Vaccination Center doesn't exist in the system");
        }
    }

    public String getSelectedVC() {
        return this.vaccCenterId;
    }
}
