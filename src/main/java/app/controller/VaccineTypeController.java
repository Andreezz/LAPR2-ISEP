package app.controller;

import app.domain.model.Company;

import app.domain.model.VaccineType;
import app.domain.stores.VaccineTypeStore;

import java.util.List;

/**
 * Controller of the UserStory: Specify a new vaccine type
 * @author André Gonçalves -> 1210804@isep.ipp.pt
 */

  public class VaccineTypeController {
    private Company company;
    private VaccineType vt;
    private VaccineTypeStore store;


    /**
     * Constructor of the class, gets an instance of the company class
     */

    public VaccineTypeController() {
        this(App.getInstance().getCompany());
    }

    /**
     * Constructor of the class, receives an instance of the company class
     *
     * @param company instance of Company
     */

    public VaccineTypeController(Company company) {
        this.company = company;
        this.vt = null;
    }

    /**
     * Creates a new Vaccinetype  instance, firstly creates a instance of VaccinetypeStore and then call the method of this instance that creates the Vaccinetype instance
     *
     * @param code id of the Vaccine type
     * @param type name of the Vaccine type
     * @param tech email of the Vaccine type
     */
    public void createVaccineType(String code, String type, String tech) {
        store = company.getVaccinetypeList();
        store.createVaccineType(code, type, tech);

    }

    /**
     * @return String that represents the Vaccinetype instance
     */
    public VaccineType getVt() {
        return store.getVt();
    }

    /**
     * Calls the method of the instance of VaccinetypeStore that saves the Vaccinetype instance in the ArrayList
     *
     * @return success of the operation
     */
    public boolean saveVaccinetype() {
        return this.store.saveVaccineType();
    }



}


