package app.controller;

import app.domain.model.Company;
import app.domain.model.ScheduleVaccineSNSUser;
import app.domain.stores.ScheduledSNSUserStore;
import app.domain.stores.VaccineStore;
import app.domain.model.NewVaccine;
import app.domain.model.VaccinationCenter;
import app.domain.stores.VaccinationCenterStore;

public class ScSNSUserController {
    private Company company;
    private ScheduledSNSUserStore scheduledstore;
    private ScheduleVaccineSNSUser scheduled;
    private VaccineStore VS;
    private VaccinationCenterStore store;


    private NewVaccine NV;

    public ScSNSUserController(){
        this.company = App.getInstance().getCompany();
        this.scheduledstore = company.getScheduledStore();
        this.VS = company.getVaccineStore();
        this.NV = null;
        this.store= company.getVaccinationCenterList();

    }

    /**
     * Creates the Vaccine Schedule
     * @param number the  user number
     * @param phonenumber user phone number
     * @param birthdate the sns user age
     * @param gender the sns user gender
     * @param vaccine the vaccine that the sns user intends to take
     * @param date the day that the sns user intends to take the vaccine shot
     * @param hours the time that the sns user intends to take the vaccine shot
     * @param NameCenter  the vaccination center that the user intends to be vaccinated on
     */
    public void createSc( String name,String number,String email, String phonenumber, String birthdate, String gender, String vaccine, String date, String hours, String NameCenter){
        scheduled = scheduledstore.createSc(name,number, email, phonenumber, birthdate, gender, vaccine, date, hours, NameCenter);
    }

    /**
     * Function that executes the cleaning of the scheduled vaccine list
     */
    public void ClearScheduledVacc(){
        scheduledstore.clearSchedulesSNS();
    }

    /**
     * Function that returns the scheduled vaccination list
     *
     * @return scheduled vaccination list
     */
    public String getScheduledVaccineList(){
        return scheduledstore.getScheduledVaccineList();
    }

    /**
     * Removes a determined schedule
     * @param g
     */
    public void Removed(int g){scheduledstore.RemoveSchedulevacc(g);}

    /**
     * gets the vaccine list for complement the program
     * @return the vaccine arraylist
     */
    public String getVaccineeList() {
        return VS.getVaccineList();
    }


    /**
     *Validates the vaccine
     * @param scheduledvaccine
     * @return an confirmation of the validation
     */
    public boolean validateScheduledVaccine(ScheduleVaccineSNSUser scheduledvaccine){
        try{
            return scheduledvaccine.checkNumber(scheduledvaccine.getNumber()) && scheduledvaccine.checkPhoneNumber(scheduledvaccine.getPhonenumber()) && scheduledvaccine.checkBirthDateFormat(scheduledvaccine.getBirthdate()) && scheduledvaccine.checkhours(scheduledvaccine.getHours()) && scheduledvaccine.checkNameVaccine(scheduledvaccine.getVaccine()) && scheduledvaccine.checkNameCenter(scheduledvaccine.getNameCenter()) && scheduledvaccine.checkdate(scheduledvaccine.getDate()) ;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }


    public String getToString(){
        return company.getSNSUserStore().toString();
    }
    public ScheduleVaccineSNSUser getScheduledVaccine(){
        return scheduledstore.getScheduledvaccine();
    }
      /**
      * Function that saves the schedule
       */
    public boolean saveScheduledVaccine( String name, String number, String email, String phonenumber,  String birthdate, String gender, String vaccine, String date, String hours, String NameCenter) {
        scheduledstore.saveNewScheduledVaccine(this.scheduled);
        return true;
    }

    /**
     * gets the vaccine list to improve the print
     * @return the vaccine list
     */
    public String getVaccineList() {
        return VS.getVaccineList();
    }


}
