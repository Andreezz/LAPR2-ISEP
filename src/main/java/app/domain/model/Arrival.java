package app.domain.model;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Arrival class of the UserStory: Register arrival of SNS user
 * @author Pedro Ferreira -> 1210825@isep.ipp.pt
 */

public class Arrival implements Serializable {

    private static final long serialVersionUID = 2L;
    private String date;
    private String time;
    private SNSUser user;
    private VaccinationCenter vaccCenter;

    public Arrival( SNSUser user, VaccinationCenter vaccCenter, String date, String time ) {
        if( ObjectUtils.allNotNull(user, vaccCenter) && !StringUtils.isBlank(time)) {
            this.user = user;
            this.vaccCenter = vaccCenter;
            this.date = date;
            this.time = time;
        }else{
            throw new IllegalArgumentException( "Arrival cannot have values as null/blank!!!" );
        }
    }

    public SNSUser getUser() {
        return user;
    }

    public VaccinationCenter getVaccCenter() {
        return vaccCenter;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public boolean checkTimeFormat(String time) {
        try{
            DateTimeFormatter strictTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
                    .withResolverStyle(ResolverStyle.STRICT);
            LocalTime.parse(time, strictTimeFormatter);
            return true;
        }catch (DateTimeParseException e) {
            System.err.println("Error 405 : Invalid time string: " + time);
        }
        throw new IllegalArgumentException("Correct time format: HH:mm");
    }

    public boolean hasSNSnumber( SNSUser user) {
        return this.user.getSNSNumber().equals(user.getSNSNumber());
    }

    public boolean hasTime(String time) {
        return this.time.equals(time);
    }

    public boolean hasDate(String date) {
        return this.date.equals(date);
    }

    /**
     *
     * @return the string of information
     */
    public String toString() {
        return "Date = " + date  + " | time= " + time + " | user= " + user.getSNSNumber() + " | vaccCenter= " + vaccCenter.getName();
    }
}
