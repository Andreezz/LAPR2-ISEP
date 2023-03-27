package app.ui.console;

import app.controller.ListVaccinesController;
import app.controller.NewVaccineController;
import app.controller.VaccinationCenterController;
import app.domain.model.VaccineType;
import app.domain.shared.Constants;
import app.domain.stores.VaccineStore;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ListVaccinesUI implements Runnable {

    private ListVaccinesController ctrl;
    private NewVaccineController ctrlvac;

    /**
     * creates an instance of the class ListVaccinesController
     */


    public ListVaccinesUI() {
        this.ctrl = new ListVaccinesController();
    }


    @Override
    public void run() {


        try {

            System.out.println(ctrl.getVaccineList());


        } catch (Exception e) {
            System.out.println("\nThere's no vaccines in the system!!\n");
        }
    }
}

