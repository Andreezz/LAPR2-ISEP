package app.domain.model;

/**
 * imports that are necessary to the correct functionality of the controller class
 */

import org.apache.commons.lang3.ObjectUtils;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Objects;
import java.util.regex.Pattern;
import app.domain.shared.Constants;
import java.util.ArrayList;
import java.text.ParseException;




public class AdverseReac implements Serializable {


    /**
     * String related to the SNS Number, by this it means, the number of the SNS User in the NHS system
     */

    private String snsnumber;

    /**
     *String related to adverse reactions, by this means, adverse reactions that were registered after reciving vaccine
     */
    private String adversereactions;

    /**
     *constructor of the AdverseReac class
     * @param snsnumber  is related to the SNS Number, by this it means, the number of the SNS User in the NHS system
     * @param adversereaction  is related to adverse reactions, by this means, adverse reactions that were registered after reciving vaccine
     */

    public AdverseReac (String snsnumber, String adversereaction ) {

        this.snsnumber= snsnumber;

        this.adversereactions = adversereactions;

    }

    public AdverseReac() {

    }


    /**
     * gets the SNS User's name
     * @return the SNS User's name
     */

    public String getSnsnumber() {return snsnumber;}


    /**
     *gets the description of the adverse reactions
     * @return the description of the adverse reactions
     */
    public String getAdversereactions(){return adversereactions;}

    /**
     *validates the SNS Number given by the User, and responds a confirmation/denial message
     * @return a connfirmation/denial message
     */
    public boolean checkSnsNumber() {
        String NumberRegex = "^[0-9]{9}$";
        Pattern pattern = Pattern.compile(NumberRegex);
        if (!pattern.matcher(snsnumber).matches()) {
            throw new IllegalArgumentException("SNS number must have 9 characters and can only contain digits!");
        }
        return true;
    }


    public boolean checkPhoneNumber(String phonenumber) {
        String NumberRegex = "^[0-9]{9}$";
        Pattern pattern = Pattern.compile(NumberRegex);
        if (!pattern.matcher(phonenumber).matches()) {
            throw new IllegalArgumentException("Phone number must have 9 characters and can only contain digits!");
        }
        return true;
    }



    public boolean checkBirthDateFormat(String birthdate){
        if (birthdate.length() > 10)
            throw new IllegalArgumentException("birth date cannot have more than 10 characters!\nPlease note that the birthdate must follow this format: DD/MM/YYYY");
        else{
            String check = String.valueOf(birthdate.charAt(2)) + String.valueOf(birthdate.charAt(5));
            if(!check.equals("//"))
                throw new IllegalArgumentException("birth date must be written in the following format: DD/MM/YYYY");
            else{
                String[] dateParts = birthdate.split("/");
                if(!(NumberUtils.isDigits(dateParts[0]) && NumberUtils.isDigits(dateParts[1]) && NumberUtils.isDigits(dateParts[2])))
                    throw new IllegalArgumentException("the birthdate's days, months and years must be digits!\nPlease note that the birthdate must follow this format: DD/MM/YYYY");
                else{
                    int days = Integer.parseInt(dateParts[0]);
                    int months = Integer.parseInt(dateParts[1]);
                    int years = Integer.parseInt(dateParts[2]);
                    if(!(days > 0 && days < (monthLenght(months, years) + 1) && months > 0 && months < 13 && years > 1899 && years < 2023))
                        throw new IllegalArgumentException("days must have a value between 1 and 31 according to the month's limit!Months must have a value between 1 and 12!Years must have a value between 1900 and 2022!\nPlease note that the birthdate must follow this format: DD/MM/YYYY");
                }
            }
        }
        return true;
    }

    public  boolean checkdate ( String date) {
        try {
            LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("d-M-uuuu")
                            .withResolverStyle(ResolverStyle.STRICT)
            );
        } catch (DateTimeParseException e) {
            System.out.println("Try yyyy-mm-dd");
        }
        return true;
    }
    /**
     * Checks hours veracity
     * @param hours
     * @return a logical value confirming the inserted value
     */
    public boolean checkhours(String hours){
        try {
            LocalTime.parse(hours);
        } catch (DateTimeParseException | NullPointerException e) {
            System.out.println("Invalid time try HH:mm");
        }
        return true;
    }


    private int monthLenght(int month, int year){
        switch(month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                if(isLeapYear(year))
                    return 29;
                else
                    return 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
        }
        return 0;
    }
    private static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }



}
