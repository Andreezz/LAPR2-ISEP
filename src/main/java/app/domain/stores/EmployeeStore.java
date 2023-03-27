package app.domain.stores;

import app.domain.model.Employee;
import app.domain.model.IdGenerator;
import app.domain.shared.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Store of the UserStory: Register an Employee
 * @author Lourenço Mayol -> 1211206@isep.ipp.pt
 */

public class EmployeeStore
{

    private FileOutputStream outFile;
    private ObjectOutputStream output;
    private FileInputStream inFile;
    private ObjectInputStream input;
    private List<Employee> employees;
    private Employee employee;

    /**
     * creates a list of the class Employee
     */
    public EmployeeStore(){
        this.employees = new ArrayList<>();
    }

    /**
     * generates an id for the employee and creates it with the specified information
     * @return an employee
     */
    public Employee createEmployee(String address ,String name, String phoneNumber, String email, String cc){
        String id = IdGenerator.getId(employees.size());
        return this.employee = new Employee(address, name, phoneNumber, id, email, cc);
    }

    /**
     * @param employee represents the employee to be checked
     * Uses the equals method from the class Employee to determine if an equal employee has already been saved to the employee's list
     * The employees attributes must be unique for each of them, so if they share an attribute that is equal it will not be possible to save the employee.
     * @return false if the employee doesn't exist inside the list
     */
    public boolean employeeExists(Employee employee){
        for (Employee e : employees){
            if(e.equals(employee))
                throw new IllegalArgumentException("The employee already exists!Please note that employee attributes must be unique for each employee!");
        }
        return false;
    }

    /**
     * clears the employee list
     */
    public void clearEmployeeList(){
        employees.clear();
    }

    /**
     * @return a String containing the information of all the employees inside the list
     */
    public String getEmployeesList(){
        StringBuilder employeesList = new StringBuilder();
        for (Employee e : employees) {
            employeesList.append(e.toString());
            employeesList.append("\n");
        }
        return String.valueOf(employeesList);
    }

    /**
     * @param employee represents the employee to be checked
     * @return true if all the employee's attributes are valid, otherwise, returns false
     */
    public boolean validateEmployee(Employee employee){
        try {
            return employee.checkCCFormat(employee.getCc()) && employee.checkEmailFormat(employee.getEmail()) && employee.checkNameFormat(employee.getName()) && employee.checkPhoneNumberFormat(employee.getPhoneNumber());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * @return a String that contains the information of a possible employee to be saved in the list (it will only be saved if this is valid and the user confirms its information)
     */
    public String getEmployeeInfo(){return this.employee.toString();}

    /**
     * @param employee represents the employee to be added in the list
     * saves an employee by adding it to the employees list
     */
    public void saveEmployee(Employee employee){
        employees.add(employee);
        saveList();
    }


    /**
     *
     * @param ids list of the IDs (emails) of all users with a certain role
     * @return filtered of the employees with IDs that match the users'
     */
    public EmployeeStore filterList(List<String> ids){
        EmployeeStore list = new EmployeeStore();
        for (Employee value : employees) {
            for (String id : ids) {
                if (id.equals(value.getEmail())) {
                    list.saveEmployee(value);
                    break;
                }
            }
        }
        return list;
    }
    /**
     *
     * @return checks if the list is empty
     */
    public boolean isEmpty(){
        return this.employees.size() == 0;
    }

    /** saves the arraylist in a file, using serialization.This contains all the employees that were registered.
     */
    public void saveList(){
        try {
            this.outFile = new FileOutputStream(Constants.EMPLOYEE_FILE);
            this.output = new ObjectOutputStream(outFile);
            this.output.writeObject(employees);
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
            this.inFile = new FileInputStream(Constants.EMPLOYEE_FILE);
            this.input = new ObjectInputStream(inFile);
            employees = (List<Employee>) input.readObject();
            this.input.close();
            this.inFile.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
