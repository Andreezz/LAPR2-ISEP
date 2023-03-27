package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import app.domain.stores.SNSUserStore;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller of the UserStory: As, an administrator, load a set of SNS users from a CSV file
 * @author Lourenço Mayol -> 1211206@isep.ipp.pt
 */
public class FileLoaderController {

    private SNSUserStore store;
    private AuthFacade auth;
    private FileLoader loader;
    private Company comp;

    /** creates an instance of the class SNSuserStore
     */
    public FileLoaderController(){
        this.comp = App.getInstance().getCompany();
        this.auth = comp.getAuthFacade();
        this.store = comp.getSNSUserStore();

    }

    /** Creates an instance of the class FileLoader
     * @param filePath represents the path of the file to be loaded
     */
    public void createSNSFileLoader(String filePath){
        loader = new FileLoader(filePath);
    }

    /** Loads a SNS user CSV file
     * @return the result of the method of the same name from the class FileLoader
     */
    public boolean loadSNSFile(){return loader.loadFile(false);}

    /** Determines if an SNS user is valid, by checking if his attributes are in the correct format
     * @return true if the SNS user is valid, otherwise, returns false
     */
    public boolean validateFileUser(SNSUser user){return this.store.validateSNSUser(user);}

    /** Determines if an SNS user is valid, this is valid if the user doesn't exist
     * @return true if the SNS user is valid, otherwise, returns false
     */
    public boolean fileUserExists(SNSUser user){return this.store.userExists(user);}

    /** @return an instance of the SNSUser class
     */
    public SNSUser getUser (String name, String gender, String birthdate, String address, String phoneNumber, String email, String SNSNumber, String cc){return this.store.createSNS(name, gender, birthdate, address, phoneNumber, email, SNSNumber, cc);}

    /** Checks if an SNS number was already registered for an SNS user.
     * @param SNSnum to be checked.
     * @return true if the SNS number was registered, otherwise, returns false.
     */
    public boolean checkSNSnumExists (String SNSnum){
        return this.store.checkSNSnumExists(SNSnum);
    }

    /** Loads a set of users from a list by adding the users in the SNSUserStore's list and by saving them with AuthFacade
     * Repeated users from the file will not be saved
     * @param fileUsers a list of users that were loaded from a file
     * in the end uses the method saveList to serialize the list with the users that were added
     */
    public void loadSNSUserSet(List<SNSUser> fileUsers){
        List<String> names = new ArrayList<>();
        List<String> emails = new ArrayList<>();
        List<String> passwords = new ArrayList<>();
        for (SNSUser u : fileUsers) {
            if(!this.store.userExists(u)){
                String pass = PasswordGenerator.getPassword();
                passwords.add(pass);
                names.add(u.getName());
                emails.add(u.getEmail());
                this.store.addSNSUser(u);
                this.auth.addUserWithRole(u.getName(), u.getEmail(), pass, Constants.SNS_USER_ROLE);
                sendPasswordNotification.sendPassword(names, emails, passwords);
            }
        }
        this.store.saveList();
    }

    //APENAS PARA TESTAR SE LISTA TEM OS SNSUSERS GUARDADOS
    public void printListTest(){
        store.testPrintSNSList();
    }

}
