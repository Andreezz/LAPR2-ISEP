package app.domain.model.DateTime;


import app.domain.model.CheckDate;
import app.domain.model.CheckTime;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Comparator;

/**
 * Class for US17
 * @author Lourenço Mayol -> 1211206@isep.ipp.pt
 */
public class DateTime implements Serializable {

    private static final long serialVersionUID = 7L;

    /** attributes for all DateTime subclasses
     */
    private String date;
    private String time;

    private static final String DEFAULT_TIME = "no time registered";

    /** constructor for all DateTime subclasses, checks if the date and time are blank, if they are then an exception is thrown
     * @param date represents a date for any of the subclasses
     * @param time represents a time for any of the subclasses
     */
    public DateTime(String date, String time){
        if(!(StringUtils.isBlank(date) && StringUtils.isBlank(time))) {
            this.date = date;
            this.time = time;
        } else throw new IllegalArgumentException("Date and time cannot be blank!");
    }

    public DateTime(String date){
        if(!(StringUtils.isBlank(date))) {
            this.date = date;
            this.time = DEFAULT_TIME;
        } else throw new IllegalArgumentException("Date cannot be blank!");
    }

    /**
     * @return the instance's registered time
     */
    public String getTime() {return this.time;}

    /**
     * @return the instance's registered date
     */
    public String getDate() {return this.date;}

    /**
     * @return checks the instance's registered date and time
     */
    public boolean validate() {return CheckDate.checkDate(this.date) && CheckTime.checkTime(this.time);}

    //POLIMORFISMO
    /**
     * @return a text description of the class ArrivalDateTime
     */
    public String toString(ArrivalDateTime arr) {
        return "Arrival date and time:" + "\n"+
                "time=" + arr.getTime() + "\n"+
                "date=" + arr.getDate() + "\n";
    }

    /**
     * @return a text description of the class ScheduleDateTime
     */
    public String toString(ScheduleDateTime sch) {
        return "Scheduled date and time:" + "\n"+
                "time=" + sch.getTime() + "\n" +
                "date=" + sch.getDate() + "\n";
    }

    /**
     * @return a text description of the class LeavingDateTime
     */
    public String toString(LeavingDateTime leave) {
        return "Leaving date and time:" + "\n"+
                "time=" + leave.getTime() + "\n" +
                "date=" + leave.getDate() + "\n";
    }

    /**
     * @return a text description of the class NurseAdminDateTime
     */
    public String toString(NurseAdminDateTime admin) {
        return "Nurse administration date and time:" + "\n"+
                "time=" + admin.getTime() + "\n" +
                "date=" + admin.getDate() + "\n";
    }

    /** @return the year value of a date
     */
    public int getYears(){
        String[] dateParts;
        dateParts = this.date.split("/");
        return Integer.parseInt(dateParts[0]);
    }

    /** @return the month value of a date
     */
    public int getMonths(){
        String[] dateParts;
        dateParts = this.date.split("/");
        return Integer.parseInt(dateParts[1]);
    }

    /** @return the day value of a date
     */
    public int getDays(){
        String[] dateParts;
        dateParts = this.date.split("/");
        return Integer.parseInt(dateParts[2]);
    }

    /** @return the hour value of a time
     */
    public int getHours(){
        String[] timeParts;
        timeParts = this.time.split(":");
        return Integer.parseInt(timeParts[0]);
    }

    /** @return the minute value of a time
     */
    public int getMinutes(){
        String[] timeParts;
        timeParts = this.time.split(":");
        return Integer.parseInt(timeParts[1]);
    }




}
