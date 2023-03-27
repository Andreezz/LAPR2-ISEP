package app.ui.console;

import app.controller.WaitingRoomListController;
import app.domain.stores.ArrivalStore;
import app.ui.console.utils.Utils;

public class WaitingRoomListUI implements Runnable{
    private WaitingRoomListController ctrl;
    private String vaccCenter;


    public WaitingRoomListUI(String vaccCenter) {
        ctrl = new WaitingRoomListController();
        this.vaccCenter = vaccCenter;
    }

    public void run(){
        boolean checkCentre, leave;
        checkCentre = Utils.confirm("Confirm if this is " + vaccCenter+ " your Vaccination Centre? (yes/no)\n");
        if(checkCentre) {
            do {
                ArrivalStore list = ctrl.getArrivals(vaccCenter);
                if (list.isEmpty()) {
                    System.out.println("The waiting room is empty.");
                    leave=true;
                } else {
                    System.out.println("SNS Users in the waiting room:\n");
                    ctrl.waitingRoomListing(list);
                    leave = Utils.confirm("Return to \"Nurse menu\"? (yes/no)\n");
                }
            } while (!leave);
    } else {
            System.out.println("Login in again and change your Vaccination Centre.");
        }
    }

}
