package app.controller;

import app.domain.model.Company;
import app.domain.model.ScheduleVaccine;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
import app.domain.stores.ScheduleVaccineStore;
import app.domain.stores.VaccinationCenterStore;
import pt.isep.lei.esoft.auth.AuthFacade;

public class ScheduleVaccineController {

    private App app;
    private ScheduleVaccineStore scheduleStore;
    private ScheduleVaccine schedule;
    private VaccinationCenterStore storeVC;
    private VaccinationCenter vaccCenter;

    public ScheduleVaccineController(String vaccCenterID){
        this.app = App.getInstance();
        this.scheduleStore = app.getCompany().getScheduleStore();
        this.storeVC = app.getCompany().getVaccinationCenterList();
        this.vaccCenter = storeVC.getVaccinationCenter(vaccCenterID);
    }

    public void create(String number, String age, String gender, String vaccine, String date, String hours, String NameCenter){
        schedule = scheduleStore.create(number, age, gender, vaccine, date, hours, NameCenter);
    }

    public void ClearSchedule(){
        scheduleStore.clearSchedules();
    }

    public String getScheduleVaccineList(){
        return scheduleStore.getScheduleVaccineList();
    }

    public String getScheduleVaccineinfo(){
        return scheduleStore.getScheduleVaccineinfo();
    }
    public boolean savevalidateScheduleVaccine(ScheduleVaccine schedule){
        try{
            return schedule.checkNumber(schedule.getNumber()) && schedule.checkgender(schedule.getGender()) && schedule.checkBirthDateFormat(schedule.getBirthdate()) && schedule.checkhours(schedule.getHours()) && schedule.checkNameVaccine(schedule.getVaccine()) && schedule.checkNameCenter(schedule.getNameCenter()) && schedule.checkdate(schedule.getDate()) ;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String getToString(){
        return app.getCompany().getScheduleStore().toString();
    }
    public ScheduleVaccine getScheduleVaccine(){
        return scheduleStore.getSchedulevaccine();
    }
    public boolean saveScheduleVaccine(String number, String age, String gender, String vaccine, String date, String hours, String NameCenter) {
        scheduleStore.saveNewScheduleVaccine(this.schedule);
        return true;
    }
    public void Remove(int g){scheduleStore.RemoveSchedules(g);}
}
