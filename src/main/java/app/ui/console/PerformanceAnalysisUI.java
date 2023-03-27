package app.ui.console;

import app.algorithms.BruteForce;
import app.controller.PerformanceAnalysisController;
import app.domain.model.PerformanceEvaluator;
import app.mappers.dto.ArrivalDTO;
import app.mappers.dto.CenterDataDTO;
import app.properties.PropertiesCache;
import app.ui.console.utils.Utils;

import java.text.ParseException;

import java.util.Arrays;
import java.util.List;

import static app.domain.model.CheckDate.checkDate;

/**
 * UI UserStory [016]
 * @author Pedro Ferreira -> 1210825@isep.ipp.pt
 */

public class PerformanceAnalysisUI implements Runnable {

    private PerformanceAnalysisController ctrl;

    public PerformanceAnalysisUI() {
        ctrl = new PerformanceAnalysisController();
    }

    @Override
    public void run() {
        boolean success = false;
        String date = " ";
        int time = 0;

        do {
            try {
                date = Utils.readLineFromConsole("\nInsert the date(dd/MM/yyyy) : ");
                time = Utils.readIntegerFromConsole("Insert the time interval(minutes) : ");
                if(checkDate(date) && checkTime(time)) {
                    success = true;
                }
            }catch (Exception e) {
                System.err.println("Error 404 : Date or Time values invalid! Try again");
            }
        }while(!success);

        List<CenterDataDTO> lstData = ctrl.getCenterDataDTO();
        List<ArrivalDTO> lstArrivals = ctrl.getArrivalsDTO();

        int[] inputList = new int[0];
        int[] maxSumSublist = new int[0];
        int sum = 0;
        String interval = null;

        try {
            PropertiesCache prop = new PropertiesCache();
            PerformanceEvaluator pe = ctrl.getPerformanceEvaluator();
            inputList = ctrl.createList(date , time , lstData , lstArrivals);
            maxSumSublist = ctrl.computeMaxSublist(inputList,pe);
            sum = ctrl.getSublistSum(maxSumSublist);
            if(prop.getProperty("Company.PerformanceAnalysis.Class").equals("app.algorithms.BruteForce"))
                interval = ctrl.getSublistTimeInterval((BruteForce) pe);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\n| Performance Analysis Result |");
        System.out.println("\nInput List: " + Arrays.toString(inputList));
        System.out.println("\nMaximum sum contiguous sublist: " + Arrays.toString(maxSumSublist));
        System.out.println("\nSublist sum: " + sum);
        System.out.println("\nSublist time interval: " + interval);
    }

    public boolean checkTime(int time) {
        if(time > 0) {
            if((720 % time) == 0 ) {
                return true;
            }else{
                System.err.println("Error 201 : Time value invalid! Try numbers that are dividers of 720!");
                return false;
            }
        }else{
            System.err.println("Error 101 : Time value cant be smaller than 0!! Try Again");
            return false;
        }
    }
}
