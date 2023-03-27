package app.ui.console;


import app.controller.NewVaccineController;
import app.controller.VaccineTypeController;
import app.domain.model.VaccineType;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class NewVaccinesUI implements Runnable {

    private NewVaccineController ctrl;
    private VaccineTypeController ctrltype;

    public NewVaccinesUI() {
        ctrl = new NewVaccineController();
        ctrltype = new VaccineTypeController();

        /*ctrltype.createVaccineType("12333", Constants.TECH_1,Constants.TECH_1_TYPE);
        ctrltype.saveVaccinetype();
        ctrltype.createVaccineType("12334", Constants.TECH_2,Constants.TECH_2_TYPE);
        ctrltype.saveVaccinetype();
        ctrltype.createVaccineType("12335", Constants.TECH_3,Constants.TECH_3_TYPE);
        ctrltype.saveVaccinetype();
        ctrltype.createVaccineType("12336", Constants.TECH_4,Constants.TECH_4_TYPE);
        ctrltype.saveVaccinetype();
        ctrltype.createVaccineType("12337", Constants.TECH_5,Constants.TECH_5_TYPE);
        ctrltype.saveVaccinetype();
        ctrltype.createVaccineType("12338", Constants.TECH_6,Constants.TECH_6_TYPE);
        ctrltype.saveVaccinetype();*/

    }

    public int VaccineMenu() {
        List<String> options = new ArrayList<String>();
        options.add("Register a new vaccine");
        options.add("Consult the currently available vaccines");
        options.add("Delete the vaccine list");
        int option = 0;
        option = Utils.showAndSelectIndex(options, "\nSelect an option to continue:\n");
        return option+1;
    }

    public boolean confirmNewVaccine() {
        System.out.println("Do you wish to save this vaccine with the following information?");
        System.out.println(ctrl.getVaccineInfo());
        return Utils.confirm("Answer with a yes or a no:");
    }



    /**
     * The user will be kept in a loop until he/she decides to exit NewVaccinesUI
     * while he doesn't leave the vaccine menu the user has 5 options: register a new vaccine, get the vaccine list, clear the vaccine list, clear a specific vaccine or exit
     */
    @Override
    public void run() {
        boolean end = false;
        do {
            switch (VaccineMenu()) {
                case 0:
                    end = true;
                    break;
                case 1:
                    boolean cont = true;
                    String name;
                    String manufacturercompany;
                    int id;
                    List<Integer> ageGroup = new ArrayList<>();
                    int dose;
                    List<Integer> timeInterval = new ArrayList<>();
                    List<Float> dosage = new ArrayList<>();

                    ctrl.setvacctypee((VaccineType) Utils.showAndSelectOne(ctrl.getTypesList(), "please select a type"));

                    name = Utils.readLineFromConsole("Type the new vaccine's name: ");
                    manufacturercompany = Utils.readLineFromConsole("\nType the company responsible for the vaccine's manufacturer: ");
                    id = Utils.readIntegerFromConsole("\nType the id of the new vaccine: ");
                    ageGroup.add(Utils.readIntegerFromConsole("Type the minimum age:"));
                    ageGroup.add(Utils.readIntegerFromConsole("Type the maximum age:"));
                    dose = Utils.readIntegerFromConsole("Type the number of doses:");

                    for (int i = 0; i < dose; i++) {
                        System.out.println("Dose nº" + (i+1));
                        dosage.add(Utils.readFloatFromConsole("VaccineDosage:"));
                        timeInterval.add(Utils.readIntegerFromConsole("Interval's Day:"));
                    }

                    ctrl.createNewVaccine(id, name, manufacturercompany, ageGroup, timeInterval, dosage);

                    if (ctrl.saveNewVaccine()) {
                        System.out.println("The NewVaccine was saved with success");
                    } else {
                        System.out.println("Couldn't save the New Vaccine please try again ");
                    }
                    break;
                case 2:
                    System.out.println(ctrl.getVaccineList());
                    break;
                case 3:
                        ctrl.clearVaccineList();
                        System.out.println("Vaccine list was cleared!");
                    break;
            }

        }
        while (!end);

    }

}