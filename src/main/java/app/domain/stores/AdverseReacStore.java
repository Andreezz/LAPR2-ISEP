package app.domain.stores;

import app.domain.model.ScheduleVaccineSNSUser;
import app.domain.shared.Constants;
import app.domain.model.AdverseReac;
import app.controller.RegisterAdReaController;
import app.domain.model.Company;
import app.ui.console.AdverseReacUI;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class AdverseReacStore {

    /**
     *Functions to save SNS User's adverse reactions list in a File
     */

    private FileOutputStream outFile;
    private ObjectOutputStream output;
    private FileInputStream inFile;
    private ObjectInputStream input;

    /**
     * creation of an arraylist to save the SNS User's adverse reactions
     */

    private List<AdverseReac> adrealist;
    private AdverseReac adrea;

    public AdverseReacStore(){this.adrealist = new ArrayList<>();}

    /**
     * creates the SNS User's adverse reactions registration
     * @param snsnumber is related to the SNS Number, by this it means, the number of the SNS User in the NHS system
     * @param adversereactions is related to adverse reactions, by this means, adverse reactions that were registered after reciving vaccine
     * @return the newly created SNS User's adverse reactions to the list
     */

    public AdverseReac createAdRea(String snsnumber, String adversereactions){
        return this.adrea = new AdverseReac (snsnumber, adversereactions);
    }

    /**
     * validates the information given by the User to the System
     * @return a response that validates/invalidates the information given by the User
     */
    public boolean ValidateAdRea( AdverseReac adrea) {
        if (adrea == null || contains(adrea)) {
            return false;
        }
        return true;
    }
    /**
     * gets the SNS User's adverse reactions list
     * @return the SNS User's adverse reactions list and its content
     */
    public String getAdReaList(){
        StringBuilder AdReaList = new StringBuilder();
        for (AdverseReac s : adrealist) {
            AdReaList.append(s.toString());
            AdReaList.append("\n");
        }
        return String.valueOf(AdReaList);
    }
    /**
     * Clears the SNS USer´s adverse reactions list
     */
    public void clearAdRea(){adrealist.clear();}

    /**
     *gets the information about the SNS User's registered adverse reactions
     * @return the information related to SNS User's registered adverse reactions
     */
    public AdverseReac getAdreaction(){
        return adrea;
    }

    /**
     * removes one selected SNS User's adverse reactions registration
     * @param g is relative to the number line that the user wants to remove
     */

    public void RemoveOneAdRea(int g){adrealist.remove(g-1);}

    public boolean contains(AdverseReac adrea) {
        if (this.adrealist.contains(adrea)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *adds the newly created SNS User's adverse reaction configuration to the list
     * @return an addition to the Adverse Reactions list
     */
    public boolean add(AdverseReac adrea) {
        adrealist.add(adrea);
        return true;
    }
    /**
     *saves the given information about the SNS User´s adverse reactions in the Store
     */
    public void saveNewAdRea(AdverseReac adrea){
        adrealist.add(adrea);
    }


    /**
     * loads the SNS User's adverse reactions list to a file
     */
    public void loadToLocalList(){
        try {
            this.inFile = new FileInputStream(Constants.ADVERSE_REACTIONS_FILE);
            this.input = new ObjectInputStream(inFile);
            adrealist = (ArrayList) input.readObject();
            this.input.close();
            this.inFile.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * saves the SNS User's adverse reactions ina  file
     */
    public void saveList(){
        try {
            this.outFile = new FileOutputStream(Constants.ADVERSE_REACTIONS_FILE);
            this.output = new ObjectOutputStream(outFile);
            this.output.writeObject(adrealist);
            this.output.close();
            this.outFile.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }






}
