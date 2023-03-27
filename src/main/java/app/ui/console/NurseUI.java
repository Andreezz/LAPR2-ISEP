package app.ui.console;

import app.controller.SelVaccinationCenterController;
import app.domain.model.VaccinationCenter;
import app.mappers.dto.VaccinationCenterDTO;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class NurseUI implements Runnable {
    private SelVaccinationCenterUI sel;

    public NurseUI() { sel = new SelVaccinationCenterUI(); }

    @Override
    public void run() {

        sel.run();

        String vc = sel.getSelectedVC();

        List<MenuItem> options = new ArrayList<MenuItem>();

            options.add(new MenuItem("Consult the users in the waiting room", new WaitingRoomListUI(vc)));
            options.add(new MenuItem("Record the administration of a vaccine", new VaccineAdministrationUI(vc)));
            options.add(new MenuItem("Register adverse reactions", new AdverseReacUI(vc)));
            int option = 0;

            do {
                try {
                    option = Utils.showAndSelectIndex(options, "\n\nNurse Menu:");
                    if ((option >= 0) && (option < options.size())) {
                        options.get(option).run();
                    }
                } catch (Exception e) {
                    System.out.print("Invalid option! Try again");
                }
            }
            while (option != -1);
        }

    }
