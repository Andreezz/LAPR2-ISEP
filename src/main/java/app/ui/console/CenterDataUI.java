package app.ui.console;

import app.controller.CenterDataController;
import app.domain.model.CenterData;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CenterDataUI implements Runnable{

    private CenterDataController ctrl;

    public CenterDataUI(){
        ctrl = new CenterDataController();
    }


    @Override
    public void run() {
        boolean end = false;
        do{
            switch (centerDataMenu()){
                case 0:
                    end = true;
                    break;
                case 1:
                    String filePath = Utils.readLineFromConsole("Type the path of the file that you want to load:");
                    if(confirmFilePath(filePath)){
                        try {
                            ctrl.createCenterDataFileLoader(filePath);
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        if(ctrl.loadCenterDataFile())
                            System.out.println("The file was loaded successfully!");
                        else
                            System.out.println("The file was not loaded due to an error!");
                    }
                    break;
                case 2:
                    if(!ctrl.isListEmpty()) {
                        int order = 0;
                        int type = 0;
                        do {
                            order = chooseOrder();
                        }while(order == 0);
                        do {
                            type = chooseType();
                        }while(type == 0);
                        System.out.println("Center Data List:");
                        System.out.println(ctrl.getSortedList(order, type));
                    }
                    else
                        System.out.println("There is no registered center data in the system!");
                    break;
            }
        }while(!end);
    }

    private int chooseType() {
        List<String> options = new ArrayList<String>();
        options.add("by arrival date and time");
        options.add("by leaving date and time");
        int option = 0;
        try {
            option = Utils.showAndSelectIndex(options, "\nChoose the type of sorting that you want:\n");
        }
        catch (Exception e){
            System.out.println("Invalid Option!Try Again!");
            return 0;
        }
        return option + 1;
    }

    private int chooseOrder() {
        List<String> options = new ArrayList<String>();
        options.add("Ascending");
        options.add("Descending");
        int option = 0;
        try {
            option = Utils.showAndSelectIndex(options, "\nChoose the order in which you want to see the center data list:\n");
        }
        catch (Exception e){
            System.out.println("Invalid Option!Try Again!");
            return 0;
        }
        return option + 1;
    }

    public int centerDataMenu() {
        List<String> options = new ArrayList<String>();
        options.add("Load a center data file");
        options.add("View center data sorted in a list");
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

    public boolean confirmFilePath(String filePath){
        System.out.println("Do you wish to load this file?");
        System.out.println(filePath);
        return Utils.confirm("Answer here with a yes or a no:");
    }
}
