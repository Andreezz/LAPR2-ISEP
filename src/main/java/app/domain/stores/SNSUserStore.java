package app.domain.stores;

import app.domain.model.SNSUser;
import app.domain.shared.Constants;

import java.io.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Store of the UserStory: Register an SNS User, as a Receptionist
 * @author Lourenço Mayol -> 1211206@isep.ipp.pt
 */
public class SNSUserStore {

    private List<SNSUser> users;
    private SNSUser user;
    private FileOutputStream outFile;
    private ObjectOutputStream output;
    private FileInputStream inFile;
    private ObjectInputStream input;

    /**
     * creates a list of the class SNSUser
     */
    public SNSUserStore (){
        this.users = new ArrayList<>();
    }

    /** @return an SNSUser instance
     */
    public SNSUser createSNS(String name, String gender, String birthdate, String address, String phoneNumber, String email, String SNSNumber, String cc){
        return this.user =  new SNSUser(name, gender, birthdate, address, phoneNumber, email, SNSNumber, cc);
    }

    /**
     * @param user represents the SNSUser to be checked
     * Uses the equals method from the class SNSUser to determine if an equal user has already been saved to the user's list
     * The users attributes must be unique for each of them, so if they share an attribute that is equal it will not be possible to save the user.
     * @return false if the user doesn't exist inside the list, otherwise, returns true
     */
    public boolean userExists(SNSUser user) {
        for (SNSUser u : users) {
            try {
                if (u.equals(user))
                    throw new IllegalArgumentException("The SNS user already exists!\nPlease note that SNSUser's attributes must be unique for each employee!(this doesn't apply to gender,birthdate and address)");
            } catch (Exception e) {
                return true;
            }
        }
        return false;
    }

    public boolean checkSNSnumExists(String SNSnum){
        for (SNSUser u : users) {
            if(u.getSNSNumber().equals(SNSnum))
                return true;
        }
        return false;
    }

    public SNSUser getBySNSNum( String snsNum ) {
        SNSUser user;

        for( SNSUser i : users ) {
            if(i.getSNSNumber().equals(snsNum) ) {
                return i;
            }
        }
        return null;
    }

    /** Loads to the local list in the store, the information that was stored in the file, using serialization.
     */
    public void loadToLocalList(){
        try {
            this.inFile = new FileInputStream(Constants.SNS_USER_FILE);
            this.input = new ObjectInputStream(inFile);
            users = (List<SNSUser>) input.readObject();
            this.input.close();
            this.inFile.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //APENAS USADO PARA TESTAR LISTA
    public void testPrintSNSList() {
        for (SNSUser u : users) {
            System.out.println(u);
        }
    }

    /**
     * @param user represents the user to be checked
     * @return true if all the user's attributes are valid, otherwise, returns false
     */
    public boolean validateSNSUser(SNSUser user){
        return user.checkCCFormat(user.getCC()) && user.checkEmailFormat(user.getEmail()) && user.checkNameFormat(user.getName()) && user.checkPhoneNumberFormat(user.getPhoneNumber()) && /*user.checkGenderFormat(user.getGender()) &&*/ user.checkSNSNumber(user.getSNSNumber()) && user.checkBirthDateFormat(user.getBirthDate());
    }

    /**
     * @param user represents the user to be added in the list
     * saves a user by adding it to the user list
     */
    public void addSNSUser(SNSUser user){
        users.add(user);
        saveList();
    }

    /** saves the arraylist in a file, using serialization.This contains all the SNSUsers that were registered.
     */
    public void saveList(){
        try {
            this.outFile = new FileOutputStream(Constants.SNS_USER_FILE);
            this.output = new ObjectOutputStream(outFile);
            this.output.writeObject(users);
            this.output.close();
            this.outFile.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @return a String that contains the information of a possible user to be saved in the list (it will only be saved if this is valid and the administrator confirms its information)
     */
    public String getSNSUserInfo(){return this.user.toString();}

}
