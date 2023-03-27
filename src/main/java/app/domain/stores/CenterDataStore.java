package app.domain.stores;

import app.domain.model.CenterData;
import app.domain.shared.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Store for US17
 * @author Lourenço Mayol -> 1211206@isep.ipp.pt
 */
public class CenterDataStore {



    private List<CenterData> store;

    private  CenterData centerData;

    private FileOutputStream outFile;

    private ObjectOutputStream output;

    private FileInputStream inFile;

    private ObjectInputStream input;


    /**
     * creates a list of the class CenterData
     */
    public CenterDataStore(){this.store = new ArrayList<>();}


    /** @param cd represents the centerData line to be added in the list
     *  adds a specified instance to the store
     */
    public void add(CenterData cd){
        this.store.add(cd);
        saveList();
    }

    /** creates a CenterData instance with the specified values
     * @return an instance of the class CenterData
     */
    public CenterData create(String arrDate, String arrTime, String schDate, String schTime, String adminDate,String adminTime, String leaveDate,String leaveTime, String SNSnum, String lot, String vacName, String currentDose){
        this.centerData = new CenterData(arrDate, arrTime, schDate, schTime, adminDate, adminTime, leaveDate, leaveTime, SNSnum, lot, vacName, currentDose);
        return this.centerData;
    }

    /** Validates a given instance of the class CenterData
     * @param cd instance to be checked
     * @return
     */
    public boolean validate(CenterData cd){return cd.validateCenterData();}

    /** Checks if a CenterData instance was already registered in the CenterData list
     *There can't be two objects with the same SNS number and the same currentDose or two objects with a dateTime registered for the same SNSnumber
     * If any of these cases are detected the instance is considered as invalid.
     * @param cd CenterData instance to be checked
     * @return true if the instance was already registered, otherwise, returns false
     */
    public boolean CenterDataExists(CenterData cd){
        for (CenterData CD : store) {
            try {
                if (CD.equals(cd))
                    throw new IllegalArgumentException("This line was already registered!");
            } catch (Exception e) {
                return true;
            }
        }
        return false;
    }

    /** saves the arraylist in a file, using serialization.This contains all the CenterData lines that were registered.
     */
    public void saveList(){
        try{
            this.outFile = new FileOutputStream(Constants.CENTER_DATA_FILE);
            this.output = new ObjectOutputStream(outFile);
            this.output.writeObject(store);
            this.output.close();
            this.outFile.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /** Loads to the local list in the store, the information that was stored in the file, using serialization.
     */
    public void loadToLocalList(){
        try{
            this.inFile = new FileInputStream(Constants.CENTER_DATA_FILE);
            this.input = new ObjectInputStream(inFile);
            store = (List<CenterData>) input.readObject();
            this.input.close();
            this.inFile.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<CenterData> getAll() {return this.store;}

    public boolean isEmpty() {
        return store.size() == 0;
    }
}
