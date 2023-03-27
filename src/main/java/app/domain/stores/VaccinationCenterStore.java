package app.domain.stores;

import app.domain.model.*;
import app.domain.shared.Constants;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class VaccinationCenterStore {
    List<VaccinationCenter> array;
    VaccinationCenter vc;

    private FileOutputStream outFile;
    private ObjectOutputStream output;
    private FileInputStream inFile;
    private ObjectInputStream input;

    /**
     * Constructor of the class it creates an empty list to be filled with objects of Vaccination Center
     */
    public VaccinationCenterStore() {
        this.array = new ArrayList<VaccinationCenter>();
    }

    /**
     * This method creates a new Vaccination Center object by calling his constructor

     * @param name represents the VaccinationCenter's Name
     * @param email represents the VaccinationCenter's Email
     * @param phoneNumber represents the VaccinationCenter's Phone Number
     * @param address represents the VaccinationCenter's Address
     * @param vctype represents the VaccinationCenter's stock of vaccines
     * @return Vaccination Center object created
     */


    public VaccinationCenter createVaccinationCenter(String name, String email,String phoneNumber,String address, String webAddress, int faxNumber,
                                                     String vctype, Double slotDuration, int maxVaccines,  Schedule schedule) {
        if(Objects.equals(vctype, Constants.VC_TYPE_1)){
            this.vc = new HealthCareCenter(name,email,phoneNumber,address,webAddress,faxNumber,vctype,slotDuration,maxVaccines, schedule);
        }else
            this.vc = new MassVaccinationCenter(name,email,phoneNumber,address,webAddress,faxNumber,vctype,slotDuration,maxVaccines, schedule);

        return this.vc;
    }

    /**
     * this method checks if the Vaccination Center object received is not null, if don't already exists in the ArrayList
     *
     * @param vc Vaccination Center object
     * @return boolean value that is true if the object is not null and dont already exists in the ArrayList
     */
    public boolean ValidateVaccinationCenter( VaccinationCenter vc) {
        if (vc == null || contains(vc)) {
            return false;
        }
        return true;
    }

    /**
     * this method checks if the Vaccination Center object received already exits in the ArrayList
     *
     * @param vc Vaccination Center object
     * @return boolean value that is true if the object already exists in the ArrayList
     */

    public boolean contains(VaccinationCenter vc) {
        if (this.array.contains(vc)) {
            return true;
        } else {
            return false;
        }
    }

    public List<VaccinationCenter> getALL() {
        return this.array;
    }

    /**
     * this method is used to save the Vaccination Center object in the arrayList already created, before adding the object the method validates it
     *
     * @return a boolean value that indicates the success of the operation
     */
    public boolean saveVaccinationCenter() {
        if (ValidateVaccinationCenter(this.vc)) {
            add(vc);
            return true;
        } else {
            return false;
        }
    }

    /**
     * this method adds the Vaccination Center object to the arrayList
     *
     * @param vc ParameterCategory object
     * @return a boolean value that indicates the success of the operation
     */
    public boolean add(VaccinationCenter vc) {
        array.add(vc);
        saveList();
        return true;
    }

    /**
     * This method search for an Vaccination Center object by the index of that object in the ArrayList
     *
     * @param index index of the array list where we want to get the object
     * @return the Vaccination Center object that was in the index of the array list
     */
    public VaccinationCenter get(int index) {
        return array.get(index);
    }

    /**
     * Go through all the objects in the ArrayList and appends the String of the method toString to a new String creating a new line for object
     *
     * @return String with all the objects in the ArrayList
     */
    public String toString() {
        StringBuilder listString = new StringBuilder();

        for (VaccinationCenter s : array) {
            listString.append(s.toString()).append(" \n");
            //System.out.println(""+s);
        }

        return String.valueOf(listString);
    }

    /**
     * @return Parameter Category object
     */
    public VaccinationCenter getVc() {
        return vc;
    }

    public VaccinationCenter getVaccinationCenter(String vaccCenterID ) {
        for (VaccinationCenter i : array ) {
            if( i.getName().equals(vaccCenterID) ) {
                return i;
            }
        }
        return null;
    }

    public boolean exists(String vaccCenterName) {
        for (VaccinationCenter i : array ) {
            if( i.getName().equals(vaccCenterName) ) {
                return true;
            }
        }
        return false;
    }

    /** saves the arraylist in a file, using serialization.This contains all the Vaccination Centers that were registered.
     */
    public void saveList(){
        try {
            this.outFile = new FileOutputStream(Constants.VACCINATION_CENTER_FILE);
            this.output = new ObjectOutputStream(outFile);
            this.output.writeObject(array);
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
            this.inFile = new FileInputStream(Constants.VACCINATION_CENTER_FILE);
            this.input = new ObjectInputStream(inFile);
            array = (List<VaccinationCenter>) input.readObject();
            this.input.close();
            this.inFile.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}