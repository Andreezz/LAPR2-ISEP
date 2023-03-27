package app.domain.model;

import app.algorithms.BruteForce;
import app.mappers.dto.ArrivalDTO;
import app.mappers.dto.CenterDataDTO;

import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 *  UserStory [016]
 * @author Pedro Ferreira -> 1210825@isep.ipp.pt
 */

public class PerformanceAnalysis {

    public PerformanceAnalysis() {}

    private List<String> times = new ArrayList<>();

    public int[] difArrivalLeaving(String date, int time_interval, List<CenterDataDTO> cdDTO, List<ArrivalDTO> arrivalsDTO) throws ParseException {
        List<String> lstArrival = getListOfArrivals(date, cdDTO, arrivalsDTO);
        List<String> lstLeaving = getListOfLeavings(date, cdDTO);
        int[] inputList = new int[720 / time_interval];

        DateTimeFormatter dt = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime ini_time = LocalTime.parse("08:00", dt);

        int count = 0;
        while(count < inputList.length) {

            LocalTime fin_time = ini_time.plusMinutes(time_interval);

            if(!times.contains(ini_time.toString()))
                this.times.add(ini_time.toString());

            if(!times.contains(ini_time.toString()))
                this.times.add(fin_time.toString());

            int countArr = 0;
            int countLea = 0;

            for (String i : lstArrival) {
                LocalTime arrival = LocalTime.parse( i , dt );

                if ((arrival.equals(ini_time) || arrival.isAfter(ini_time)) && (arrival.equals(fin_time) || arrival.isBefore(fin_time))) {
                    countArr++;
                }
            }

            for (String i : lstLeaving) {
                LocalTime leaving = LocalTime.parse( i , dt );

                if ((leaving.equals(ini_time) || leaving.isAfter(ini_time)) && (leaving.equals(fin_time) || leaving.isBefore(fin_time))) {
                    countLea++;
                }
            }

            inputList[count] = (countArr - countLea);
            ini_time = fin_time;
            count++;
        }
        this.times.add("20:00");
        return inputList;
    }

    public int[] computeMaxSublist(int[] inputList , PerformanceEvaluator pe ) {
        return pe.computeMaxSublist(inputList);
    }

    public int getSublistSum(int[] maxSumSublist) {
        int sum = 0;
        for( Integer i : maxSumSublist ) {
            sum += i;
        }
        return sum;
    }

    public String getSublistTimeInterval(BruteForce bf) {
        return bf.getSublistTimeInterval(this.times);
    }

    public List<String> getListOfArrivals(String date, List<CenterDataDTO> cdDTO, List<ArrivalDTO> arrivalsDTO ) {
        List<String> lst = new ArrayList<>();

        for( CenterDataDTO i : cdDTO ) {
            if( i.getArr().getDate().equals(date) ) {
                lst.add(i.getArr().getTime());
            }
        }

        for( ArrivalDTO i : arrivalsDTO ) {
            if( i.getDate().equals(date) ) {
                lst.add(i.getTime());
            }
        }

        return lst;
    }

    public List<String> getListOfLeavings(String date, List<CenterDataDTO> cdDTO) {
        List<String> lst = new ArrayList<>();

        for( CenterDataDTO i : cdDTO ) {
            if( i.getLeave().getDate().equals(date) ) {
                lst.add(i.getLeave().getTime());
            }
        }
        return lst;
    }

    public List<String> getTimes() {
        return this.times;
    }

}
