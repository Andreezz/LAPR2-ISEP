package app.controller;

import app.domain.model.Company;
import app.domain.model.Schedule;
import app.domain.model.VaccinationCenter;
import app.domain.stores.VaccinationCenterStore;

/**
 * Controller of the UserStory: Register a new Vaccination Center
 * @author André Gonçalves -> 1210804@isep.ipp.pt
 */

public class VaccinationCenterController {
    private Company company;
    private VaccinationCenter vc;
    private VaccinationCenterStore store;


/**
 * Constructor of the class, gets an instance of the company class
 */

public VaccinationCenterController(){
    this(App.getInstance().getCompany());
}

    /**
     * Constructor of the class, receives an instance of the company class
     *
     * @param company instance of Company
     */

    public VaccinationCenterController(Company company) {
        this.company = company;
        this.vc = null;
    }

    /**
     * Creates a new VaccinationCenter  instance, firstly creates a instance of VaccinationCenterStore and then call the method of this instance that creates the VaccinationCenter instance
     *
     * @param name         name of the Vaccination Center
     * @param email        email of the Vaccination Center
     * @param phoneNumber  phonenumber of the Vaccination Center
     * @param address      address of the Vaccination Center
     */
    public void createVaccinationCenter(String name, String email,String phoneNumber,String address, String webAddress, int faxNumber,
                                        String vctype, Double slotDuration, int maxVaccines,  Schedule schedule) {
        store = company.getVaccinationCenterList();
        store.createVaccinationCenter(name,email,phoneNumber,address,webAddress,faxNumber,vctype,slotDuration,maxVaccines, schedule);

    }

    /**
     * @return String that represents the VaccinationCenter instance
     */
    public VaccinationCenter getVc() {
        return store.getVc();
    }

    /**
     * Calls the method of the instance of VaccinationCenterStore that saves the VaccinationCenter instance in the ArrayList
     *
     * @return success of the operation
     */
    public boolean saveVaccinationCenter() {
        return this.store.saveVaccinationCenter();
    }

    public String getToString(){
        return company.getVaccinationCenterList().toString();
    }


}
