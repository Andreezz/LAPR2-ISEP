package app.controller;

/**
 * imports that are necessary to the correct functionality of the controller class
 */

import app.domain.model.Company;
import app.domain.model.AdverseReac;
import app.domain.model.ScheduleVaccineSNSUser;
import app.domain.stores.AdverseReacStore;
import app.domain.model.VaccineType;
import app.domain.stores.VaccineTypeStore;
import app.ui.console.AdverseReacUI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import app.properties.PropertiesCache;


import java.util.List;

/**
 * Controller of the UserStory: Register a new Vaccine and its administration process
 *
 * @author Miguel Moreira (1211240) - 1DN
 */


public class RegisterAdReaController {

    private Company company;

    private AdverseReacStore adversereacstore;

    private AdverseReac adversereac;

    private App app;

    public RegisterAdReaController() {

        this.company = App.getInstance().getCompany();
        this.adversereacstore = company.getAdversereacstore();

    }

    /**
     * creates the SNS User's adverse reactions registration
     * @param snsnumber is related to the SNS Number, by this it means, the number of the SNS User in the NHS system
     * @param adversereactions is related to adverse reactions, by this means, adverse reactions that were registered after reciving vaccine
     */
        public void createAdRea( String snsnumber, String adversereactions)
        {  adversereac= adversereacstore.createAdRea(snsnumber,adversereactions);

        }

    /**
     * Clears the SNS USer´s adverse reactions list
     */
        public void clearAdRea(){adversereacstore.clearAdRea();}

    /**
     * gets the SNS User's adverse reactions list
     * @return the SNS User's adverse reactions list and its content
     */
    public String getAdReaList(){return adversereacstore.getAdReaList();}

    /**
     * removes one selected SNS User's adverse reactions registration
     * @param g is relative to the number line that the user wants to remove
     */
    public void RemoveOneAdRea(int g){adversereacstore.RemoveOneAdRea(g);}

    /**
     * validates the information given by the User to the System
     * @param adversereac i
     * @return a response that validates/invalidates the information given by the User
     */
    public boolean validateAdRea(AdverseReac adversereac){
        try{
            return  adversereac.checkSnsNumber();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * function related to the introduction of the information
     * @return strings with the information
     */
    public String getToString(){
        return company.getAdversereacstore().toString();
    }

    /**
     *gets the information about the SNS User's registered adverse reactions
     * @return the information related to SNS User's registered adverse reactions
     */
    public AdverseReac getAdversereac(){
        return adversereacstore.getAdreaction();
    }

    /**
     *saves the given information about the SNS User´s adverse reactions in the Store
     * @param snsnumber is the number related to the SNS User number in the NHS
     * @param adversereactions is the adverse reactions registered after reciving the shot
     * @return the registered information
     */
    public boolean saveAdRea( String snsnumber, String adversereactions) {
        adversereacstore.saveNewAdRea(this.adversereac);
        return true;
    }



        }




