package app.ui.console;

import app.controller.ExportCtrl;
import app.ui.console.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExportVaccinatedDailyUI implements Runnable {

    private ExportCtrl ctrl;

    public ExportVaccinatedDailyUI(){
        this.ctrl = new ExportCtrl();
    }


    @Override
    public void run() {
        boolean end = false;
        do {
            switch (exportMenu()) {
                case 0:
                    end = true;
                    break;
                case 1:
                    String fileName = Utils.readLineFromConsole("Please type here the file's name with the statistics to be exported:");
                    System.out.println("Please type the time interval of the statistics that should be exported\n");
                    String date1 = Utils.readLineFromConsole("First date:");
                    String date2 = Utils.readLineFromConsole("Second date:");
                    try {
                        ctrl.createExport(fileName);
                        if (ctrl.checkFileName(fileName)) {
                            if(ctrl.export(date1, date2))
                                    System.out.println("The file was exported!");
                            }
                        }catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    break;
            }
        }
        while (!end) ;

    }

    public int exportMenu() {
        List<String> options = new ArrayList<String>();
        options.add("Export vaccinated users statistics");
        int option = 0;
        try {
            option = Utils.showAndSelectIndex(options, "\nSelect an option to continue:\n");
        }
        catch (Exception e){
            System.out.println("Invalid Option!Try Again!");
            return 0;
        }
        return option + 1;
    }
}
