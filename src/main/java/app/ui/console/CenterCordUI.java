package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * UserInterface of Receptionist
 *
 * @author André Gonçalves -> 1210804@isep.ipp.pt
 */

public class CenterCordUI implements Runnable {

    public CenterCordUI() {
    }

    @Override
    public void run() {

        List<MenuItem> options = new ArrayList<MenuItem>();

        options.add(new MenuItem("List all vaccines.", new ListVaccinesUI()));
        options.add(new MenuItem("Analyze the performance of a vaccination center" , new PerformanceAnalysisUI()));
        options.add(new MenuItem("Load and view center data" , new CenterDataUI()));
        options.add(new MenuItem("Export vaccination statistics" , new ExportVaccinatedDailyUI()));
       
        int option = 0;

        do {
            option = Utils.showAndSelectIndex(options, "\n\nCenter Coordinator Menu:");
            if ((option >= 0) && (option < options.size())) {
                try {
                    options.get(option).run();
                }
                catch (Exception e){
                    System.out.println("Invalid Option! Try Again!");
                    //e.printStackTrace();
                }
            }
        } while (option != -1);
    }
}
