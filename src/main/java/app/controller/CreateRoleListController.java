package app.controller;

import app.domain.model.Company;
import app.domain.stores.EmployeeStore;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.mappers.dto.UserDTO;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.ArrayList;
import java.util.List;


public class CreateRoleListController {
    private Company company;
    private EmployeeStore employees;
    private AuthFacade authFacade;

    /**
     * gets instances of the class Company and RoleStore
     */
    public CreateRoleListController() {
        this.company = App.getInstance().getCompany();
        this.employees = this.company.getEmployeeStore();
        this.authFacade = this.company.getAuthFacade();
    }

    /**
     * Constructor of the class, receives an instance of the company class
     *
     * @param company instance of Company
     */
    public CreateRoleListController(Company company) {
        this.company = company;
    }


    /**
     * @return list of all Roles
     */
    public List<String> getRoleList() {
        List<UserRoleDTO> roles = this.authFacade.getUserRoles();
        List<String> roleNames = new ArrayList<>();
        for(UserRoleDTO role : roles){
            roleNames.add(role.getDescription());
        }
        return roleNames;
    }

    /**
     * @param roleID the role selected for the list of employees
     * @return list of all users with the selected role
     */
    public List<String> getAllwithRole(String roleID) {
        return this.company.getAllwithRole(roleID);
    }

    /**
     *
     * @return list of all the employees in the system
     */
    public EmployeeStore getEmployees() {
        return this.employees;
    }

    /**
     *
     * @param allEmp list of all the employees in the system
     * @param ids list of all the users ID's, with the intended role
     * @return a filtered list of the employees with the intended role
     */
    public EmployeeStore filterList(EmployeeStore allEmp, List<String> ids){
        return allEmp.filterList(ids);
    }

}