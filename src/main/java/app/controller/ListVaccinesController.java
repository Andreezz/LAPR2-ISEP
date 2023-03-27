package app.controller;


import app.domain.model.Company;

import app.domain.model.NewVaccine;
import app.domain.stores.VaccineStore;

import java.util.List;


/**
 * Controller of the UserStory: As a center coordinator, I want to get a list of all vaccines.
 *
 * @author André Gonçalves -> 1210804@isep.ipp.pt
 */

public class ListVaccinesController {

    private Company company;
    private List<NewVaccine> newVaccines;
    private VaccineStore store;

    /**
     * Constructor of the class, gets an instance of the company class and store
     */
    public ListVaccinesController() {
        this.company = App.getInstance().getCompany();
        this.store = company.getVaccineStore();
    }

    public List<NewVaccine> getlist() {
        return store.getList();
    }

    public String getVaccineList() {
        return store.getVaccineList();
    }
    public String ListOutput(){
        return store.ListOutput();
    }

}

