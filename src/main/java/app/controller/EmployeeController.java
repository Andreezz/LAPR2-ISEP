package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import app.domain.stores.EmployeeStore;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller of the UserStory: Register an Employee
 * @author Lourenço Mayol -> 1211206@isep.ipp.pt
 */

public class EmployeeController
{
    private Company company;
    private EmployeeStore store;
    private AuthFacade auth;
    private Employee employee;

    /**
     * gets instances of the class Company, AuthFacade and EmployeeStore
     */
    public EmployeeController() {
        this.company = App.getInstance().getCompany();
        this.store = company.getEmployeeStore();
        this.auth = company.getAuthFacade();
    }

    /**
     * creates an instance of the classe employee using the method createEmployee() from the EmployeeStore class
     */
    public void createEmployee(String address , String name, String phoneNumber, String email, String cc){
        employee = store.createEmployee(address , name, phoneNumber, email, cc);
    }

    /**
     * @param roleId represents the employee's role
     * uses the methods validateEmployee() and employeeExists from the EmployeeStore class to verify if the employee is valid
     * if the employee is valid it will register the employee, creating a user in the system for him and saving the employee in the employees list
     * @return true if the employee was registered, otherwise, returns false
     */
    public boolean saveEmployee(String roleId, String pwd){
        if (store.validateEmployee(employee) && !(store.employeeExists(employee))) {
            store.saveEmployee(this.employee);
            auth.addUserWithRole(this.employee.getName(), String.valueOf(this.employee.getEmail()), pwd, roleId);
            return true;
        }
        else return false;
    }

    /**
     * @return a String containing an employee's information by accessing the method getEmployeeInfo() of the EmployeeStore class
     */
    public String getEmployeeInfo(){return store.getEmployeeInfo();}

    /**
     * clears the employees list using the method of the same name from the EmployeeStore class
     */
    public void clearEmployeeList(){store.clearEmployeeList();}

    /**
     * @return the information of all the employee's that are in the employees list by accessing the method of the same name from the class EmployeeStore
     */
    public String getEmployeesList(){return store.getEmployeesList();}

    public void serializeAll(){
        CenterData cd = new CenterData("10/05/2020", "10:05","10/05/2020", "10:05","10/05/2020", "10:05",
                "10/05/2020", "10:05","123456789", "21C16-05", "Spikevax", "first");
        VaccineType type = new VaccineType("12345", Constants.TECH_6_TYPE, Constants.TECH_6);
        List<Integer> ageGroup = new ArrayList<>();
        List<Float> dosage = new ArrayList<>();
        List<Integer> timeInterval = new ArrayList<>();
        ageGroup.add(12);
        ageGroup.add(70);
        dosage.add(30.0F);
        dosage.add(30.0F);
        timeInterval.add(15);
        //NewVaccine vaccine = new NewVaccine(12345, "Spikevax", "Pfizer",type, ageGroup,timeInterval, dosage);
        Employee employee = new Employee("rua","Quim", "912345678", "00001","coisa@isep.pt","39849069");
        SNSUser user = new SNSUser("Joao", "male", "10/10/2010", "rua", "912345678", "coisa@isep.pt", "123456789", "12345678");
        HealthCareCenter vaccinationCenterA = new HealthCareCenter( "Vacinasdoze", "a@a.pt",
                "950000000", "aaaa", "www.morbius.pt", 343543,Constants.VC_TYPE_1,
                5.5, 10, new Schedule("14:00", "20:00"));
        ScheduleVaccine SchedulevaccineA = new ScheduleVaccine("123456789", "10/10/2002", "masculine", "pfizer", "10/10/2022", "10:10", "ASas");
        Arrival arrival = new Arrival(user, vaccinationCenterA, "10/10/2000", "10:00");
        //company.getEmployeeStore().saveEmployee(employee);
        //company.getSNSUserStore().addSNSUser(user);
        //company.getVaccinationCenterList().add(vaccinationCenterA);
        //company.getArrivalStore().saveArrival(arrival);
        //company.getScheduleStore().saveNewScheduleVaccine(SchedulevaccineA);
        //company.getVaccineStore().saveNewVaccine(vaccine);
        //company.getCenterDataStore().add(cd);
        //company.getVaccinetypeList().add(type);
    }

}
