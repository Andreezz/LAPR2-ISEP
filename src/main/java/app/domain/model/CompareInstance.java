package app.domain.model;

import app.domain.model.DateTime.DateTime;

import java.util.Calendar;
import java.util.Comparator;

public class CompareInstance {

    /** Compares two instances of the class DateTime
     * if the two dates are from the same day, their times are compared.
     * A positive value is returned if the first instance is the most recent.
     * A negative value is returned if the second instance is the most recent.
     * 0 is returned if the instances are from the same day and time.
     */
    static Comparator<DateTime> compareDateTime = new Comparator<DateTime>() {
        @Override
        public int compare(DateTime o1, DateTime o2) {
            if(compareDateDays.compare(o1, o2) > 0)
                return 1;
            else {
                if (compareDateDays.compare(o1, o2) < 0)
                    return -1;
            }
            //if dates are from the same day
            return compareTimeSeconds.compare(o1, o2);

        }
    };

    /** Compares the number of days from two dates to determine which one is the most recent
     */
    static Comparator<DateTime> compareDateDays = new Comparator<DateTime>() {
        @Override
        public int compare(DateTime date1, DateTime date2) {
            int totalDays1, totalDays2;
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.set(date1.getYears(), date1.getMonths(), date1.getDays());
            c2.set(date2.getYears(), date2.getMonths(), date2.getDays());
            totalDays1 = (int) (c1.getTime().getTime()/(24*60*60*1000));
            totalDays2 = (int) (c2.getTime().getTime()/(24*60*60*1000));
            return totalDays1 - totalDays2;
        }
    };

    /** Compares the number of seconds from two times to determine which one occurs latest
     */
    static Comparator<DateTime> compareTimeSeconds = new Comparator<DateTime>() {
        @Override
        public int compare(DateTime time1, DateTime time2) {
            int totalSeconds1, totalSeconds2;
            totalSeconds1 = (time1.getHours() * 3600) + (time1.getMinutes() * 60);
            totalSeconds2 = (time2.getHours() * 3600) + (time2.getMinutes() * 60);
            return totalSeconds1 - totalSeconds2;
        }
    };
}
