package app.domain.stores;


import app.domain.model.ScheduleVaccineSNSUser;

import app.domain.model.NewVaccine;
import app.domain.model.VaccinationCenter;
import app.domain.shared.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class ScheduledSNSUserStore {

    private FileOutputStream outFile;
    private ObjectOutputStream output;
    private FileInputStream inFile;
    private ObjectInputStream input;
    private List<ScheduleVaccineSNSUser> scheduledvacc;
    private ScheduleVaccineSNSUser scheduledvaccine;
    private List<NewVaccine> NVs;
    private NewVaccine NV;
    private List<VaccinationCenter> VCs;
    private VaccinationCenter VC;


    public ScheduledSNSUserStore(){
        this.scheduledvacc = new ArrayList<>();
    }

    /**
     *
     *Creates the vaccine
     * @param number
     * @param phonenumber is the phone number of the sns user
     * @param age the sns user age
     * @param gender the sns user gender
     * @param vaccine the vaccine that the sns user intends to take
     * @param date the day that the sns user intends to take the vaccine shot
     * @param hours the time that the sns user intends to take the vaccine shot
     * @param NameCenter  the vaccination center that the user intends to be vaccinated on
     * @return the vaccine
     */
    public ScheduleVaccineSNSUser createSc( String name, String number, String email, String phonenumber, String age, String gender, String vaccine, String date, String hours, String NameCenter){
        return this.scheduledvaccine = new ScheduleVaccineSNSUser (name,number,phonenumber,email, age, gender, vaccine, date, hours, NameCenter);
    }

    /**
     * Checks if there are any existing vaccines
     * @param scheduledvaccine
     * @return a logical value of true or false
     */
    public boolean ValidateScheduleVaccine( ScheduleVaccineSNSUser scheduledvaccine) {
        if (scheduledvaccine == null || contains(scheduledvaccine)) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the vaccine already exists or not
     * @param schedule
     * @return a logical value and affirms if the vaccine is already registered
     */
    public boolean ScheduledCheck(ScheduleVaccineSNSUser schedule){
        for(ScheduleVaccineSNSUser s : scheduledvacc){
            if(s.equals(schedule))
                throw new IllegalArgumentException("This schedule already exist");
            return true;
        }
        return false;
    }

    /**
     * Function that gives you the scheduled vaccine´s list
     * @return the scheduled vaccine´s list
     */
    public String getScheduledVaccineList(){
        StringBuilder scheduleList = new StringBuilder();
        for (ScheduleVaccineSNSUser s : scheduledvacc) {
            scheduleList.append(s.toString());
            scheduleList.append("\n");
        }
        return String.valueOf(scheduleList);
    }

    /**
     * Deletes the vaccine´s list
     */
    public void clearSchedulesSNS(){
        scheduledvacc.clear();
    }
    public String getScheduledList(){
        StringBuilder scheduleList = new StringBuilder();
        for (ScheduleVaccineSNSUser s : scheduledvacc) {
            scheduleList.append(s.toString());
            scheduleList.append("\n");
        }
        return String.valueOf(scheduleList);
    }

    /**
     * Gets the scheduled vaccine´s information
     * @return the info related to a schedule
     */
    public String getScheduledVaccineinfo(){
        return this.scheduledvaccine.toString();
    }

    /**
     * gets the scheduled vaccine
     * @return a specific vaccine
     */
    public ScheduleVaccineSNSUser getScheduledvaccine(){
        return scheduledvaccine;
    }


    public void RemoveSchedulevacc(int g){scheduledvacc.remove(g-1);}



    public boolean contains(ScheduleVaccineSNSUser scheduledvaccine) {
        if (this.scheduledvacc.contains(scheduledvaccine)) {
            return true;
        } else {
            return false;
        }
    }
    public boolean add(ScheduleVaccineSNSUser scheduledvaccine) {
        scheduledvacc.add(scheduledvaccine);
        return true;
    }

    public void saveNewScheduledVaccine(ScheduleVaccineSNSUser scheduledvaccine){
        scheduledvacc.add(scheduledvaccine);
        saveList();
    }
    public List<NewVaccine> getList() {
        return this.NVs;
    }

    public List<VaccinationCenter> getListt(){return  this.VCs;}

    /** saves the arraylist in a file, using serialization.This contains all the vaccine schedules that were registered.
     */
    public void saveList(){
        try {
            this.outFile = new FileOutputStream(Constants.SCHEDULE_SNS_FILE);
            this.output = new ObjectOutputStream(outFile);
            this.output.writeObject(scheduledvacc);
            this.output.close();
            this.outFile.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /** Loads to the local list in the store, the information that was stored in the file, using serialization.
     */
    public void loadToLocalList(){
        try {
            this.inFile = new FileInputStream(Constants.SCHEDULE_SNS_FILE);
            this.input = new ObjectInputStream(inFile);
            scheduledvacc = (ArrayList) input.readObject();
            this.input.close();
            this.inFile.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}