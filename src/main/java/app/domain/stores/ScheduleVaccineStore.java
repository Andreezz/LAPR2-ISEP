package app.domain.stores;

import app.domain.model.SNSUser;
import app.domain.model.ScheduleVaccine;
import app.domain.model.VaccinationCenter;
import app.domain.shared.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleVaccineStore {

    private List<ScheduleVaccine> schedules;
    private ScheduleVaccine schedulevaccine;

    private FileOutputStream outFile;
    private ObjectOutputStream output;
    private FileInputStream inFile;
    private ObjectInputStream input;


    public ScheduleVaccineStore(){
        this.schedules = new ArrayList<>();
    }


    public ScheduleVaccine create(String number, String age, String gender, String vaccine, String date, String hours, String NameCenter){
        return this.schedulevaccine = new ScheduleVaccine(number, age, gender, vaccine, date, hours, NameCenter);
    }

    public boolean Scheduleexist(ScheduleVaccine schedule){
        for(ScheduleVaccine s : schedules){
            if(s.equals(schedule))
                throw new IllegalArgumentException("This schedule already exist");
            return true;
        }
        return false;
    }

    public boolean existsSchedule(SNSUser user, VaccinationCenter vaccCenter, String date ) {
        for(ScheduleVaccine s : schedules){

            if( s.getNumber().equals(user.getSNSNumber()) && s.getNameCenter().equals(vaccCenter.getName() )
                    && s.getDate().equals(date) ) {
                return true;
            }
        }
        return false;
    }

    public String getScheduleVaccineList(){
        StringBuilder scheduleList = new StringBuilder();
        for (ScheduleVaccine s : schedules) {
            scheduleList.append(s.toString());
            scheduleList.append("\n");
        }
        return String.valueOf(scheduleList);
    }
    public void clearSchedules(){
        schedules.clear();
    }
    public void RemoveSchedules(int g){
        schedules.remove(g-1);
    }
    public String getScheduleList(){
        StringBuilder scheduleList = new StringBuilder();
        for (ScheduleVaccine s : schedules) {
            scheduleList.append(s.toString());
            scheduleList.append("\n");
        }
        return String.valueOf(scheduleList);
    }
    public boolean validateScheduleVaccine(ScheduleVaccine schedulevaccine){
        try{
            return schedulevaccine.checkNumber(schedulevaccine.getNumber()) && schedulevaccine.checkgender(schedulevaccine.getGender()) && schedulevaccine.checkBirthDateFormat(schedulevaccine.getBirthdate()) && schedulevaccine.checkhours(schedulevaccine.getHours()) && schedulevaccine.checkNameVaccine(schedulevaccine.getVaccine()) && schedulevaccine.checkNameCenter(schedulevaccine.getNameCenter()) && schedulevaccine.checkdate(schedulevaccine.getDate()) ;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean validateSchedule(ScheduleVaccine schedulevaccine){
        return schedulevaccine.checkNumber(schedulevaccine.getNumber()) && schedulevaccine.checkgender(schedulevaccine.getGender()) && schedulevaccine.checkBirthDateFormat(schedulevaccine.getBirthdate()) && schedulevaccine.checkhours(schedulevaccine.getHours()) && schedulevaccine.checkNameVaccine(schedulevaccine.getVaccine()) && schedulevaccine.checkNameCenter(schedulevaccine.getNameCenter()) && schedulevaccine.checkdate(schedulevaccine.getDate()) ;

    }
    /*public void saveScheduleVaccine(ScheduleVaccine scheduleVaccine){
        schedules.add(scheduleVaccine);
    }*/
    public String getScheduleVaccineinfo(){
        return this.schedulevaccine.toString();
    }
    public ScheduleVaccine getSchedulevaccine(){
        return schedulevaccine;
    }
    /*public boolean saveScheduleVaccine() {
        if (ValidateScheduleVaccine(schedulevaccine)) {
            add(schedulevaccine);
            return true;
        } else {
            return false;
        }
    }*/
    public boolean ValidateScheduleVaccine( ScheduleVaccine schedulevaccine) {
        if (schedulevaccine == null || contains(schedulevaccine)) {
            return false;
        }
        return true;
    }
    public boolean contains(ScheduleVaccine schedulevaccine) {
        if (this.schedules.contains(schedulevaccine)) {
            return true;
        } else {
            return false;
        }
    }
    public boolean add(ScheduleVaccine schedulevaccine) {
        schedules.add(schedulevaccine);
        return true;
    }

    public void saveNewScheduleVaccine(ScheduleVaccine schedulevaccine){
        schedules.add(schedulevaccine);
        saveList();
    }

    /** saves the arraylist in a file, using serialization.This contains all the SNSUser schedules that were registered.
     */
    public void saveList(){
        try {
            this.outFile = new FileOutputStream(Constants.SCHEDULE_VACCINE_FILE);
            this.output = new ObjectOutputStream(outFile);
            this.output.writeObject(schedules);
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
            this.inFile = new FileInputStream(Constants.SCHEDULE_VACCINE_FILE);
            this.input = new ObjectInputStream(inFile);
            schedules = (List<ScheduleVaccine>) input.readObject();
            this.input.close();
            this.inFile.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

