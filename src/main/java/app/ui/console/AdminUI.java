package app.ui.console;


import
        app.ui.console.utils.Utils;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class
AdminUI implements Runnable{
    public AdminUI()
    {
    }

    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();

        options.add(new MenuItem("Register a vaccination center ", new VaccinationCentreUI()));
        options.add(new MenuItem("Register an employee ", new EmployeeUI()));
        options.add(new MenuItem("Specify a new vaccine and its administration process ", new NewVaccinesUI()));
        options.add(new MenuItem("Specify a new vaccine type ", new VaccineTypeUI()));
        options.add(new MenuItem("List of Employees by role", new CreateRoleListUI()));
        options.add(new MenuItem("Load a set of users from a CSV file ", new FileLoaderUI()));

        int option = 0;
        do
        {
            try {
                option = Utils.showAndSelectIndex(options, "\n\nAdmin Menu:");
                if ( (option >= 0) && (option < options.size()))
                {
                    options.get(option).run();
                }
            } catch (Exception e){System.out.print("Invalid option! Try again");;}
        }
        while (option != -1 );
    }
}
