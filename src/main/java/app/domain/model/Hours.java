package app.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;



public class Hours implements Comparable <Hours>, Serializable {
    private int hours;
    private int minutes;
    private int seconds;
    private VaccinationCenter vCenter;


    /**
     * Time full constructor
     * @param hours
     * @param minutes
     * @param seconds
     */

    public Hours(int hours, int minutes, int seconds) {
        setHours(hours);
        setMinutes(minutes);
        setSeconds(seconds);
    }

    /**
     * Time constructor without seconds;
     * @param hours
     * @param minutes
     */

    public Hours(int hours, int minutes){
        setHours(hours);
        setMinutes(minutes);

    }

    /**
     * Time constructor without seconds and hours
     * @param minutes
     */


    public Hours(int minutes) {
        this.minutes=minutes;

    }

    public Hours() {

    }

    /**
     * Method to get hours
     * @return hours
     */

    public int getHours() {
        return hours;
    }

    /**
     * Method to get minutes
     * @return minutes
     */

    public int getMinutes() {
        return minutes;
    }

    /**
     * Method to change and/or validate hours
     * if hours are less than 0 or greater than 23 throws an exception
     * @param hours
     */

    public void setHours(int hours) {

        this.hours = hours;

    }

    /**
     * Method to change and/or validate minutes
     * if minutes less than 0 or greater than 59 throws an exception
     * @param minutes
     */

    public void setMinutes(int minutes) {

        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        if (seconds<0 || seconds>59) {
            throw new IllegalArgumentException("Invalid seconds. The seconds must be between 0 and 59");
        }
        this.seconds = seconds;
    }

    /**
     * It obtains in text format the information of time
     *@return Textual and visible representation of time
     *
     */
    @Override

    public String toString () {
        if (hours<10 && minutes <10)
            return String.format("0%d:0%d ", hours, minutes);
        if (hours<10 && minutes >10)
            return String.format("0%d:%d ", hours, minutes);
        if (hours>10 && minutes <10)
            return String.format("%d:0%d ", hours, minutes);
        return String.format("%d:%d ", hours, minutes);
    }

    public String toStringMinutes() {
        return String.format("%d minutes",minutes );
    }

    /**
     * Conversion of time in seconds
     * @return seconds
     */

    private int toSeconds () {
        return this.hours*3600 + this.minutes*60 + this.seconds;
    }

    /**
     * Method to compare different times
     * @param otherTime
     * @return -1 if the time to be compared is less than the other,  1 if it is higher than the other hour and 0 if they are equal.
     */

    @Override
    public int compareTo(Hours otherTime) {
        return otherTime.isLaterTime(this) ? -1 : (this.isLaterTime(otherTime) ?1 : 0);
    }

    /**
     * Method to compare different times
     * @param otherTime
     * @return the time to compare is bigger than the other one
     */


    public boolean isLaterTime (Hours otherTime) {
        return this.toSeconds() > otherTime.toSeconds();
    }


    /**
     * Method to get the current time
     * @return current time (current hours, current minutes and current seconds)
     */

    public static Hours getActualTime () {
        Calendar timeNow = Calendar.getInstance();
        int hour = timeNow.get(Calendar.HOUR_OF_DAY);
        int minute = timeNow.get(Calendar.MINUTE);
        int second = timeNow.get(Calendar.SECOND);
        return new Hours(hour, minute, second);
    }

    /**
     * Method to convert a time in minutes
     * @return time total minutes
     */


    public static int toMinutes (Hours time) {
        return time.getHours()*60 + time.getMinutes();
    }

    /**
     * Method to sum a time interval to a time
     * @param interval of minutes
     * @param time to add interval
     * @return new time
     */


    public static Hours getTimeBySumMinutes(int interval, Hours time) {
        int toMinutes =toMinutes(time)+interval;
        int hours = toMinutes/60;
        int minutes = toMinutes%60;

        return new Hours(hours, minutes);

    }

    /**
     *
     * @param start
     * @param finish
     * @param interval
     * @return
     */

    public  static ArrayList<Hours> sumIntervalTime (Hours start, Hours finish, int interval) {
        int n = (toMinutes(finish)-toMinutes(start)) / interval;

        ArrayList <Hours> t = new ArrayList<>(n);

        for (int i=0; i<n; i++) {
            Hours temp = start;
            t.add(temp);
            start=getTimeBySumMinutes(interval,temp);
        }

        return t;
    }
}
