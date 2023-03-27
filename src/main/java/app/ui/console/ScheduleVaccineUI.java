 package app.ui.console;

import app.controller.ScheduleVaccineController;
import app.domain.model.ScheduleVaccine;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;

import java.util.*;

public class ScheduleVaccineUI implements Runnable {


    private ScheduleVaccineController controller;
    private ScheduleVaccine scheduleVaccine;
    private SNSUserUI ui;
    private String vaccCenterID;
    ArrayList<String> ElementsDate = new ArrayList<>();
    ArrayList<String> Elementshours = new ArrayList<>();


    public ScheduleVaccineUI(String vaccCenterID) {
        controller = new ScheduleVaccineController(vaccCenterID);
        this.vaccCenterID = vaccCenterID;
    }

    public int ScheduleVaccineMenu() {
        List<String> options = new ArrayList<String>();
        options.add("Register a new Schedule");
        options.add("Consult the currently available Schedules");
        options.add("Delete the Schedule vaccine list");
        options.add("Remove a Schedule from the current list");
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
                    String number;
                    String birthdate;
                    String gender;
                    number = Utils.readLineFromConsole("\nType the number, this should be a 9 digit number: ");
                    String vaccine = Utils.readLineFromConsole("Please enter vaccine: ");
                    String date = Utils.readLineFromConsole("Please enter date (yyyy-mm-dd): ");
                    String hours = Utils.readLineFromConsole("Please enter hours (HH:mm): ");
                    birthdate = Utils.readLineFromConsole("\nType the  birthdate, this should be in the following format DD/MM/YYYY: ");
                    do {
                        gender = chooseGender();
                    }while(gender == null);
                    if(Elementshours.contains(hours) && ElementsDate.contains(date)){
                        System.out.println("Já existe um agendamento para essa data");
                        ScheduleVaccineMenu();
                    }else {
                        ScheduleVaccine scheduleVaccine = new ScheduleVaccine(number, birthdate, gender, vaccine, date, hours, vaccCenterID);
                        controller.create(number, birthdate, gender, vaccine, date, hours, vaccCenterID);
                        if (controller.savevalidateScheduleVaccine(scheduleVaccine)) {
                            System.out.println("The New Schedule Vaccine was saved with success");
                            controller.saveScheduleVaccine(number, birthdate, gender, vaccine, date, hours, vaccCenterID);
                            ElementsDate.add(date);
                            Elementshours.add(hours);
                        } else {
                            System.out.println("Couldn't save the New Schedule Vaccine please try again ");
                        }
                    }
                    break;
                case 2:
                    if (controller.getScheduleVaccineList() != null) {
                        System.out.println(controller.getScheduleVaccineList());
                    } else
                        System.out.println("Don´t have Schedules");
                    break;
                case 3:
                    controller.ClearSchedule();
                    System.out.println("Schedule was cleares");
                    break;
                case 4:
                    System.out.println(controller.getScheduleVaccineList());
                    int i = Utils.readIntegerFromConsole("Select that Schedule that you want to remove: ");
                    controller.Remove(i);
                    break;
                case 5:
                    end = true;
                    break;
            }

        }
        while (!end);


    }
    public String chooseGender(){
        System.out.println("\nChoose a gender for the SNS user that you want to register.");
        List<String> options = new ArrayList<String>();
        options.add(Constants.SNS_USER_GENDER_FEMALE);
        options.add(Constants.SNS_USER_GENDER_MALE);
        options.add(Constants.SNS_USER_GENDER_OTHER);
        int option = 0;
        try {
            option = Utils.showAndSelectIndex(options, "\n\nSelect an option to continue:");
        }
        catch (Exception e){
            System.out.println("Invalid option!Try again!");
            return null;
        }
        switch(option + 1) {
            case 1:
                return Constants.SNS_USER_GENDER_FEMALE;
            case 2:
                return Constants.SNS_USER_GENDER_MALE;
            case 3:
                return Constants.SNS_USER_GENDER_OTHER;
        }
        return null;
    }
}