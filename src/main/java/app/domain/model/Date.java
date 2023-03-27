package app.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Date implements Comparable <Date>, Serializable {

    private int year;
    private int month;
    private int day;
    private VaccinationCenter vc;



    public Date(int day, int month, int year) {
        setYear(year);
        setMonth(month);
        setDay(day);


    }

    public Date() {

    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public void setYear(int year) {

        this.year = year;

    }

    public void setMonth(int month) {

        this.month = month;

    }

    public void setDay(int day) {

        this.day = day;
    }


    public static Date getCurrentDate () {
        Calendar dateNow= Calendar.getInstance();
        int day=dateNow.get(Calendar.DAY_OF_MONTH);
        int month=dateNow.get(Calendar.MONTH)+1;//java.calendar January = 0;
        int year = dateNow.get(Calendar.YEAR);
        return new Date(day,month,year);
    }

    public static void dataIsGreaterThanTheCurrentDate(Date date){
        Date dateNow=Date.getCurrentDate();

        if(dateNow.year>date.year){
            throw new IllegalArgumentException("The year entered is less than the current date");

        }
        if(dateNow.month> date.month){
            throw new IllegalArgumentException("The month entered is less than the current date");
        }
        if(dateNow.month==date.month){
            if(dateNow.day> date.day){
                throw new IllegalArgumentException("the day entered is less than the current date");
            }
        }
    }

    @Override
    public String toString() {
        if (day<10 && month<10)
            return String.format("0%d/0%d/%d", day,month, year);
        if (day<10 && month>10)
            return String.format("0%d/%d/%d", day,month, year);
        if (day>10 && month<10)
            return String.format("%d/0%d/%d", day,month, year);

        return String.format("%d/%d/%d", day,month, year);
    }


    @Override
    public int compareTo(Date otherDate) {
        if (this.year > otherDate.getYear()) {
            return 1;
        }else if(this.year == otherDate.getYear()){
            if (this.month == otherDate.getMonth()){
                if (this.day == otherDate.getDay()){
                    return 0;
                }else if (this.day > otherDate.getDay()){
                    return 1;
                }else{
                    return -1;
                }
            }else if(this.month > otherDate.getMonth()){
                return 1;
            }else{
                return -1;
            }
        }else{
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date)) return false;
        Date date = (Date) o;
        return year == date.year && month == date.month && day == date.day;
    }


}
