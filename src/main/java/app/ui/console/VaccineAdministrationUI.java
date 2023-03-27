package app.ui.console;

import app.controller.VaccineAdministrationController;
import app.controller.WaitingRoomListController;
import app.domain.stores.ArrivalStore;
import app.ui.console.utils.Utils;

public class VaccineAdministrationUI implements Runnable {

    private WaitingRoomListController waitRoomctrl;
    private VaccineAdministrationController ctrl;
    private String vaccCenterID;

    public VaccineAdministrationUI( String vaccCenterID ) {
        ctrl = new VaccineAdministrationController(vaccCenterID);
        waitRoomctrl = new WaitingRoomListController();
        this.vaccCenterID = vaccCenterID;
    }

    @Override
    public void run() {
        boolean leave=true;
        do {
            //checkar WaitingRoomList
            ArrivalStore list = waitRoomctrl.getArrivals(vaccCenterID);
            if (list.isEmpty()) {
                System.out.println("The waiting room is empty.");
                leave = false;
            } else {
                //Selecionar SNSUser e remove-lo da ArrivalList
                waitRoomctrl.waitingRoomListing(list);
                int index = Utils.readIntegerFromConsole("Select the SNSUser:\n");
                String user = ctrl.getUserIDbyIndex(index, list);
                ctrl.removeFromWaitList(user);
                //Checkar info:
                // VaccineType,
                // SNSUser Vaccination History,
                // vacina,
                // dosage,
                // adverse reactions
           //     ctrl.checkUserVaccInfo(user);
                //Regista VaccineName e Lot (onde? - VaccineAdministrationStore, vai estar associado a um SNSUser)
                //VaccineAdministration - User ID, centre, Vac, VacType, Lot, Date&time, dose, adverseReactions (vai ser vazio inicialmente)
//                ctrl.registerVaccineAdministration(user);
                //Manda SNSUser para recovery room
//                ctrl.sendUserRecoveryRoom(user);
                //no final do period, SNSUser recebe email/SMS
            }
        } while (leave) ;
    }
}
