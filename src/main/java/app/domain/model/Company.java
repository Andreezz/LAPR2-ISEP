package app.domain.model;


import app.domain.stores.*;
import app.properties.PropertiesCache;
import pt.isep.lei.esoft.auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;
import pt.isep.lei.esoft.auth.mappers.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {

    private ArrivalStore arrStore;
    private EmployeeStore employeeStore;
    private VaccinationCenterStore vaccinationCenterList;
    private VaccineTypeStore vtstore;
    private VaccineStore vaccineStore;
    private String designation;
    private AuthFacade authFacade;
    private SNSUserStore userStore;
    private ScheduleVaccineStore scheduleStore;
    private ScheduledSNSUserStore scheduledstore;
    private AdverseReacStore adrealist;

    private Hours time;

    private CenterDataStore centerDataStore;

    private PropertiesCache configFile;

    public Company(String designation) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();

        this.vaccinationCenterList = new VaccinationCenterStore();
        this.vaccinationCenterList.loadToLocalList();

        this.vtstore = new VaccineTypeStore();
        this.vtstore.loadToLocalList();

        this.employeeStore = new EmployeeStore();
        this.employeeStore.loadToLocalList();

        this.vaccineStore = new VaccineStore();
        this.vaccineStore.loadToLocalList();

        this.arrStore = new ArrivalStore();
        this.arrStore.loadToLocalList();

        this.userStore = new SNSUserStore();
        this.userStore.loadToLocalList();

        this.scheduledstore = new ScheduledSNSUserStore();
        this.scheduledstore.loadToLocalList();

        this.scheduleStore = new ScheduleVaccineStore();
        this.scheduleStore.loadToLocalList();

        this.centerDataStore = new CenterDataStore();
        this.centerDataStore.loadToLocalList();

        this.adrealist = new AdverseReacStore();
        this.adrealist.loadToLocalList();



        try {
            configFile = new PropertiesCache();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public String getDesignation() {
        return designation;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    /**
     * @return the list of Vaccination Centers in the System
     */
    public VaccinationCenterStore getVaccinationCenterList() {
        return this.vaccinationCenterList;
    }

    /**
     * @return the list of Vaccine types in the System
     */
    public VaccineTypeStore getVaccinetypeList() {
        return this.vtstore;
    }

    /**
     * @return an instance of the class EmployeeStore
     */
    public EmployeeStore getEmployeeStore() {
        return this.employeeStore;
    }

    /**
     * @return the list of currently registered vaccines and its properties
     */
    public VaccineStore getVaccineStore() {
        return this.vaccineStore;
    }

    public ArrivalStore getArrivalStore() {
        return this.arrStore;
    }

    public AdverseReacStore getAdversereacstore() {
        return this.adrealist;
    }

    /**
     * @return an instance of the class SNSUserStore
     */
    public SNSUserStore getSNSUserStore() {
        return this.userStore;
    }

    /**
     * @return an instance of the class ScheduleVaccineStore
     */

    public ScheduleVaccineStore getScheduleStore() {
        return this.scheduleStore;
    }

    /**
     * @return an instance of the class ScheduleSNSUserStore
     */

    public ScheduledSNSUserStore getScheduledStore() {
        return this.scheduledstore;
    }

    /**
     * @return an instance of the class DailyRecordStore
     */



    /**
     * @return an instance of the class CenterDataStore
     */
    public CenterDataStore getCenterDataStore() {
        return this.centerDataStore;
    }

    public PropertiesCache getConfigFile() {
        return this.configFile;
    }

    public void setTime(Hours time) {
        this.time = time;
    }

    /**
     * @param roleID role intended for the list of employees
     * @return list of users with the intended role
     */
    public List<String> getAllwithRole(String roleID) {
        List<String> ids = new ArrayList<>();
        List<UserDTO> users = this.authFacade.getUsersWithRole(roleID);
        for (UserDTO user : users) {
            ids.add(user.getId());
        }
        return ids;
    }


}
