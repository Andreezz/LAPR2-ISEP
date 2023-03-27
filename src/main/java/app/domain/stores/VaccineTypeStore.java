
package app.domain.stores;


import app.domain.model.VaccineType;
import app.domain.shared.Constants;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VaccineTypeStore {
    List<VaccineType> array;
    VaccineType vt;

    private FileOutputStream outFile;
    private ObjectOutputStream output;
    private FileInputStream inFile;
    private ObjectInputStream input;

    /**
     * Constructor of the class it creates an empty list to be filled with objects of Vaccine Type
     */
    public VaccineTypeStore() {
        this.array = new ArrayList<VaccineType>();
    }

    /**
     * This method creates a new Vaccine type object by calling his constructor
     *
     * @param code represents the Vaccinetype id
     * @param type represents the Vaccinetype Name
     * @param tech represents the Vaccinetype Email
     * @return Vaccine type object created
     */


    public VaccineType createVaccineType(String code, String type, String tech) {
        this.vt = new VaccineType(code, type, tech);
        return this.vt;
    }

    /**
     * this method checks if the Vaccine type object received is not null, if don't already exists in the ArrayList
     *
     * @param vt Vaccine type object
     * @return boolean value that is true if the object is not null and dont already exists in the ArrayList
     */
    public boolean ValidateVaccineType(VaccineType vt) {
        if (vt == null || contains(vt)) {
            return false;
        }
        return true;
    }

    /**
     * this method checks if the Vaccine type object received already exits in the ArrayList
     *
     * @param vt Vaccine type object
     * @return boolean value that is true if the object already exists in the ArrayList
     */

    public boolean contains(VaccineType vt) {
        if (this.array.contains(vt)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * this method is used to save the Vaccine type object in the arrayList already created, before adding the object the method validates it
     *
     * @return a boolean value that indicates the success of the operation
     */
    public boolean saveVaccineType() {
        if (ValidateVaccineType(this.vt)) {
            add(vt);
            return true;
        } else {
            return false;
        }
    }

    /**
     * this method adds the Vaccine Type  object to the arrayList
     *
     * @param vt VaccineType object
     * @return a boolean value that indicates the success of the operation
     */
    public boolean add(VaccineType vt) {
        array.add(vt);
        saveList();
        return true;
    }

    /**
     * This method search for a Vaccine Type object by the index of that object in the ArrayList
     *
     * @param index index of the array list where we want to get the object
     * @return the Vaccine type object that was in the index of the array list
     */
    public VaccineType get(int index) {
        return array.get(index);
    }

    /**
     * Go through all the objects in the ArrayList and appends the String of the method toString to a new String creating a new line for object
     *
     * @return String with all the objects in the ArrayList
     */


    public String toString() {
        StringBuilder listString = new StringBuilder();

        for (VaccineType s : array) {
            listString.append(s.toString()).append(" \n");
            //System.out.println(""+s);
        }

        return String.valueOf(listString);
    }

    /**
     * @return VaccineType object
     */
    public VaccineType getVt() {
        return vt;
    }

    public List<VaccineType> getTypes() {
        return array;
    }

    /** saves the arraylist in a file, using serialization.This contains all the vaccine types that were registered.
     */
    public void saveList(){
        try {
            this.outFile = new FileOutputStream(Constants.VACCINE_TYPE_FILE);
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
            this.inFile = new FileInputStream(Constants.VACCINE_TYPE_FILE);
            this.input = new ObjectInputStream(inFile);
            array = (List<VaccineType>) input.readObject();
            this.input.close();
            this.inFile.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}