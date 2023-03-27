package app.controller;

import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.shared.Constants;
import app.domain.stores.SNSUserStore;
import pt.isep.lei.esoft.auth.AuthFacade;

/**
 * Controller of the UserStory: Register an SNS User, as a Receptionist
 * @author Lourenço Mayol -> 1211206@isep.ipp.pt
 */

public class SNSUserController {

    private Company company;
    private SNSUserStore store;
    private AuthFacade auth;
    private SNSUser user;

    /**
     * gets instances of the class Company, AuthFacade and EmployeeStore
     */
    public SNSUserController() {
        this.company = App.getInstance().getCompany();
        this.store = company.getSNSUserStore();
        this.auth = company.getAuthFacade();
    }

    /**
     * creates an instance of the class SNSUser using the method create() from the SNSUserStore class
     */
    public void create(String name, String gender, String birthdate, String address, String phoneNumber, String email, String SNSNumber, String cc){
        this.user = store.createSNS(name, gender, birthdate, address, phoneNumber, email, SNSNumber, cc);
    }

    public SNSUser snsUserExists( String snsNum ) {
        return this.store.getBySNSNum(snsNum);
    }

    /** saves an SNS user by adding it to the user list and adding it as a user with AuthFacade
     * @param pwd a random password generated by the class PasswordGenerator
     * @return true if the SNS user was saved in the system, otherwise, returns false
     */
    public boolean saveSNSUserWithAuth(String pwd){
        try {
            if (store.validateSNSUser(user) && !store.userExists(user)) {
                store.addSNSUser(this.user);
                auth.addUserWithRole(this.user.getName(), String.valueOf(this.user.getEmail()), pwd, Constants.SNS_USER_ROLE);
                return true;
            } else {
                return false;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * @return a String containing an employee's information by accessing the method getEmployeeInfo() of the EmployeeStore class
     */
    public String getSNSUserInfo(){return store.getSNSUserInfo();}

}