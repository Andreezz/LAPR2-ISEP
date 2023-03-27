package app.controller;


import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.stores.ArrivalStore;

public class VaccineAdministrationController {

    private Company company;
    private ArrivalStore waitRoom;

    public VaccineAdministrationController(String vaccCenterID) {
        this.company = App.getInstance().getCompany();
        this.waitRoom = company.getArrivalStore();
    }


    public String getUserIDbyIndex(int index, ArrivalStore list){
        return list.getUserIDByIndex(index);
    }

    public void removeFromWaitList(String user){
        waitRoom.removeFromWaitList(user);
    }


  /* public String checkUserVaccInfo(String user){


    }*/
}
