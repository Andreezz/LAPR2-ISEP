package app.controller;

import app.domain.model.Company;
import app.domain.model.NewVaccine;
import app.domain.stores.VaccineStore;
import app.domain.model.VaccineType;
import app.domain.model.Administrationprocess;
import app.domain.stores.VaccineTypeStore;

import java.util.List;


/**
 * Controller of the UserStory: Register a new Vaccine and its administration process
 *
 * @author Miguel Moreira (1211240) - 1DN
 */


public class NewVaccineController {

    private App app;

    private Company company;

    private VaccineStore VS;

    private NewVaccine NV;

    private VaccineType vacctypee;

    private Administrationprocess adminnprocess;

    /**
     * gets the instances of company, vaccine store and new vaccine
     */

    public NewVaccineController() {
        this.company = App.getInstance().getCompany();
        this.VS = company.getVaccineStore();
        this.app = App.getInstance();
        this.NV = null;
        this.vacctypee = null;
    }

    public void setvacctypee(VaccineType vacctypee) {
        this.vacctypee = vacctypee;
    }

    /**
     * creates an instance of the class NV using the method createNewVaccine() from the VaccineStore class
     */
    public void createNewVaccine(int id, String name, String manufacturercompany, List<Integer> ageGroup, List<Integer> timeInterval, List<Float> dosage) {
        NV = VS.createNewVaccine(id, name, manufacturercompany, vacctypee, ageGroup, timeInterval, dosage);
    }

    /**
     * @returns a String containing the vaccine info by accessing the Vaccine Store(VS)
     */
    public String getVaccineInfo() {
        return VS.getNewVaccineInfo();
    }

    /**
     * clears the currently available vaccine list using the method VaccineStore(VS)
     */
    public void clearVaccineList() {
        VS.clearVaccineList();
    }

    /**
     * @returns info related to all vaccine's that are in the vaccine list by accessing the method VaccineStore(VS)
     */
    public String getVaccineList() {
        return VS.getVaccineList();
    }

    public boolean saveNewVaccine() {
        VS.saveNewVaccine(this.NV);
        return true;
    }

    public List<VaccineType> getTypesList() {
        return company.getVaccinetypeList().getTypes();
    }


}


