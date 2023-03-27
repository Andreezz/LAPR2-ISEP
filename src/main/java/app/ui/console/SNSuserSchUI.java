package app.ui.console;

import app.controller.ScSNSUserController;
import app.domain.model.ScheduleVaccineSNSUser;
import app.ui.console.utils.Utils;
import app.controller.SelVaccinationCenterController;
import java.util.ArrayList;
import java.util.List;




public class SNSuserSchUI implements Runnable {

    ArrayList<String> ElementsDate = new ArrayList<>();
    ArrayList<String> Elementshours = new ArrayList<>();


    private ScSNSUserController controller;




    public SNSuserSchUI() {
        this.controller = new ScSNSUserController();
    }


    /**
     * Gives you the possibility of choosing between multiple options
     *
     * @return
     */
    public int ScheduleVaccineMenu() {
        List<String> options = new ArrayList<String>();
        options.add("Register a new vaccination schedule");
        options.add("Consult the currently registered schedules");
        options.add("Delete the scheduled vaccines list");
        options.add("Remove a scheduled vaccine from the current list");
        options.add("Exit");
        int option = 0;
        option = Utils.showAndSelectIndex(options, "\nSelect an option to continue:\n");
        return option + 1;
    }


    @Override
    public void run() {
        boolean end = false;
        do {
            switch (ScheduleVaccineMenu()) {
                case 1:
                    boolean cont = true;
                    /**
                     *Orients the user of what values he/she should insert
                     */
                    String name= Utils.readLineFromConsole("Please, enter your name");
                    String number = Utils.readLineFromConsole("Please enter SNS Number: ");
                    String email= Utils.readLineFromConsole("Please enter your email: ");
                    String phonenumber = Utils.readLineFromConsole( "Please enter your phone number");
                    String birthdate = Utils.readLineFromConsole("Please enter birthdate: ");
                    String gender = Utils.readLineFromConsole("Please enter gender: ");
                    String vaccine = Utils.readLineFromConsole("Please enter vaccine: ");
                    String date = Utils.readLineFromConsole("Please enter date (yyyy-mm-dd): ");
                    String hours = Utils.readLineFromConsole("Please enter hours (HH:mm): ");

                    String NameCenter = Utils.readLineFromConsole("Please enter name center: ");

                    /**
                     *Checks given data veracity and stops or forwards data
                     */

                    ScheduleVaccineSNSUser scheduledVaccine = new ScheduleVaccineSNSUser(name,number,email, phonenumber, birthdate, gender, vaccine, date, hours, NameCenter);
                        controller.createSc(name,number,email, phonenumber, birthdate, gender, vaccine, date, hours, NameCenter);
                        if(Elementshours.contains(hours) && ElementsDate.contains(date)){
                            System.out.println("Já têm um agendamento para essa data");
                            ScheduleVaccineMenu();
                        }else {
                        if (controller.validateScheduledVaccine(scheduledVaccine)) {
                            System.out.println("The New Schedule Vaccine was saved with success");
                            controller.saveScheduledVaccine(name,number,email,phonenumber, birthdate, gender, vaccine, date, hours, NameCenter);
                            ElementsDate.add(date);
                            Elementshours.add(hours);
                        } else {
                            System.out.println("Couldn't save the New Schedule Vaccine please try again ");
                        }
                    }

                    break;
                case 2:
                    /**
                     * gets the user's scheduled vaccines
                     */
                    if (controller.getScheduledVaccineList() != null) {
                        System.out.println(controller.getScheduledVaccineList());
                    } else
                        System.out.println(" You don´t have scheduled vaccines");
                    break;
                case 3:
                    /**
                     * clears the scheduled vaccine´s list
                     */
                    controller.ClearScheduledVacc();
                    System.out.println("Scheduled vaccines list was cleared");
                    break;
                case 4:
                    System.out.println(controller.getScheduledVaccineList());
                    int i = Utils.readIntegerFromConsole("Select that Schedule that you want to remove: ");
                    controller.Removed(i);
                case 5:
                    end = true;
                    break;


            }
        } while(!end);
    }
}

