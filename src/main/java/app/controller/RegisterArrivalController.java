package app.controller;

import app.domain.model.Arrival;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;
import app.domain.stores.ArrivalStore;
import app.domain.stores.SNSUserStore;
import app.domain.stores.ScheduleVaccineStore;
import app.domain.stores.VaccinationCenterStore;

/**
 * Controller of the UserStory: Register arrival of SNS user
 * @author Pedro Ferreira -> 1210825@isep.ipp.pt
 */

public class RegisterArrivalController {

    private App app;
    private Arrival arrival;
    private ArrivalStore storeArr;
    private SNSUserStore storeSNS;
    private VaccinationCenter vaccCenter;
    private VaccinationCenterStore storeVC;

    public RegisterArrivalController(String vaccCenterID) {
        this.app = App.getInstance();
        this.storeArr = app.getCompany().getArrivalStore();
        this.storeSNS = app.getCompany().getSNSUserStore();
        this.storeVC = app.getCompany().getVaccinationCenterList();
        this.vaccCenter = storeVC.getVaccinationCenter(vaccCenterID);
    }

    public boolean createArrival(SNSUser snsNum, String date, String time) {
        arrival = this.storeArr.createArrival( snsNum, vaccCenter, date, time);
        return this.storeArr.validateArrival(arrival);
    }

    public boolean checkVaccineSchedule(SNSUser user, String date) {
        ScheduleVaccineStore storeSched = this.app.getCompany().getScheduleStore();
        return storeSched.existsSchedule(user, vaccCenter, date);
    }

    public SNSUser snsUserExists( String snsNum ) {
        return this.storeSNS.getBySNSNum(snsNum);
    }

    public boolean saveArrival() {
        return this.storeArr.saveArrival(arrival);
    }
}
