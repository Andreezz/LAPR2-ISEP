package app.ui.console;

import app.controller.CreateRoleListController;
import app.domain.stores.EmployeeStore;
import app.ui.console.utils.Utils;

import java.util.List;
import java.util.Objects;

public class CreateRoleListUI implements Runnable {

    private CreateRoleListController ctrl;

    public CreateRoleListUI() {
        ctrl = new CreateRoleListController();
    }

    @Override
    public void run() {
        boolean check;
        do {
            String role = (String) Utils.showAndSelectOne(ctrl.getRoleList(), "Select a role:");
            if (Objects.equals(role, "0")) {
                List<String> listUsers = ctrl.getAllwithRole(role);
                EmployeeStore allEmp = ctrl.getEmployees();
                if (allEmp.isEmpty()) {
                    System.out.println("There's no employees in the system, please add them first.");
                } else {
                    EmployeeStore listE = ctrl.filterList(allEmp, listUsers);
                    System.out.println("Employees List:\n");
                    System.out.println(listE.getEmployeesList());
                }
                check = Utils.confirm("Leave back to \"Admin menu\"? (yes/no)\n");
            } else {break;}
        } while (!check) ;
    }
}