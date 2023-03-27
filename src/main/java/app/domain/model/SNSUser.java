package app.domain.model;

import app.domain.shared.Constants;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.math.NumberUtils;
import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * SNSUser class for the UserStory: Register an SNS User, as a Receptionist
 * @author Lourenço Mayol -> 1211206@isep.ipp.pt
 */
public class SNSUser implements Serializable {

    private static final long serialVersionUID = 10L;

    /** These attributes represent an SNS user
     */
    private String name;
    private String address;
    private String gender;
    private String phoneNumber;
    private String email;
    private String birthDate;
    private String SNSNumber;
    private String cc;

    /**
     * Creates an SNS user with the specified information. If any attribute is null then an exception will be activated.
     * Gender is specified by the user
     * @param name represents the SNS user's name
     * @param address represents the SNS user's address
     * @param gender represents the SNS user's gender
     * @param phoneNumber represents the SNS user's phone number
     * @param email represents the SNS user's email
     * @param birthdate represents the SNS user's birthdate
     * @param SNSNumber represents the SNS user's number
     * @param cc represents the SNS user's citizen card number
     */
    public SNSUser(String name, String gender, String birthdate, String address, String phoneNumber, String email, String SNSNumber, String cc){
        if(ObjectUtils.allNotNull(name, address, phoneNumber, email, birthdate, SNSNumber, cc)) {
            this.name = name;
            this.address = address;
            this.gender = gender;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.birthDate = birthdate;
            this.SNSNumber = SNSNumber;
            this.cc = cc;
        }
        else
            throw new IllegalArgumentException("SNSUser cannot have null attributes!");
    }

    /** Gets the user's name
     * @return a String representing the user's name
     */
    public String getName() {
        return name;
    }

    /** Gets the user's address
     * @return a String representing the user's address
     */
    public String getAddress() {
        return address;
    }

    /** Gets the user's gender
     * @return a String representing the user's gender
     */
    public String getGender() {
        return gender;
    }

    /** Gets the user's phone number
     * @return a String representing the user's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /** Gets the user's e-amail
     * @return a String representing the user's e-mail
     */
    public String getEmail() {
        return email;
    }

    /** Gets the user's birthdate
     * @return a String representing the user's birthdate
     */
    public String getBirthDate() {
        return birthDate;
    }

    /** Gets the user's phone number
     * @return a String representing the user's phone number
     */
    public String getSNSNumber() {
        return SNSNumber;
    }

    /** Gets the user's citizen card
     * @return a String representing the user's civil id
     */
    public String getCC() {
        return cc;
    }

    /**
     * @param phoneNumber represents the phone number to be checked
     * The phone number is valid if this follows these rules:
     * phone number must have 9 characters
     * all of the characters in the phone number must be digits
     * if the indicative is 91, 93 or 96 or 95
     * @return true if the phone number is valid
     */
    public boolean checkPhoneNumberFormat (String phoneNumber){
        int numberDigits = phoneNumber.length();
        //check number of digits
        if (numberDigits != Constants.PHONE_NUMBER_DIGITS) {
            throw new IllegalArgumentException("Phonenumber must have 9 digits!");
        }
        //check String is a number
        else {
            if (!NumberUtils.isDigits(phoneNumber))
                throw new IllegalArgumentException("All the characters in the phone number must be digits! Symbols or letters are not valid!");
            else {
                String indicative = String.valueOf(phoneNumber.charAt(0)) + String.valueOf(phoneNumber.charAt(1));
                if (!((indicative.equals("93") || indicative.equals("91") || indicative.equals("96") || indicative.equals("95"))))
                    throw new IllegalArgumentException("Phone number indicative must be 91 or 93 or 95 or 96!");
            }
        }
        return true;
    }

    /**
     * @param email represents the email to be checked
     * the email is valid if this follows the correct format for e-mails. Its parts must be separated by dots and it must have an at sign.
     * @return true if the e-mail is valid otherwise, it will return false
     */
    public boolean checkEmailFormat(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    /**
     * @param cc represents the cc to be checked
     * checks if the cc has the correct charcaters and if it is composed only of digits
     * @return true if the cc is valid
     */
    public boolean checkCCFormat(String cc){
        String CCRegex = "^[0-9]{8}$";
        Pattern pattern = Pattern.compile(CCRegex);
        if(!pattern.matcher(cc).matches()){
            throw new IllegalArgumentException("cc must have 8 characters and can only contain digits!");
        }
        return true;
    }

    /**
     * @param name represents the name to be checked
     * the name is valid if it only contains letters (ç is not valid).
     * @return true if the name is valid
     */
    public boolean checkNameFormat(String name){
        String [] nameParts = name.split(" ");
        for (int i = 0; i < nameParts.length; i++)
        {
            if (!nameParts[i].matches("[a-zA-ZáàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'.-]+")) {
                throw new IllegalArgumentException("SNS user's name must contain only letters!");
            }
        }
        return true;
    }

    public boolean checkGenderFormat(String gender){
        if(gender.isBlank()){
            System.out.println("NULO");
            return true;
        }
        else{
            if (!gender.matches("[a-zA-Z/]+")) {
                throw new IllegalArgumentException("The SNS user's gender must contain only letters!");
            }
        }
        return true;
    }

    /** Determines if an SNS number is valid, it will be valid if it has 9 characters that are digits
     * @param SNSNumber represents the SNS number to be checked
     * @return
     */
    public boolean checkSNSNumber(String SNSNumber){
        String SNSNumberRegex = "^[0-9]{9}$";
        Pattern pattern = Pattern.compile(SNSNumberRegex);
        if(!pattern.matcher(SNSNumber).matches()){
            throw new IllegalArgumentException("SNS number must have 9 characters and can only contain digits!");
        }
        return true;
    }


    public boolean checkBirthDateFormat (String birthDate){
        if (birthDate.length() > 10)
            throw new IllegalArgumentException("birth date cannot have more than 10 characters!\nPlease note that the birthdate must follow this format: DD/MM/YYYY");
        else{
            String check = String.valueOf(birthDate.charAt(2)) + String.valueOf(birthDate.charAt(5));
            if(!check.equals("//"))
                throw new IllegalArgumentException("birth date must be written in the following format: DD/MM/YYYY");
            else{
                String[] dateParts = birthDate.split("/");
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

    /** Determines if a given year is a leap year or not
     * @param year
     * @return true if the year is a leap year, otherwise returns false
     */
    private static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * @param month an integer that represents the month
     * @param year an integer that represents the year
     * @return the lenght of a given month as an integer, in february's case it will return 28 or 29 depending if the year is a leap year or not
     */
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

    @Override
    public String toString() {
        return "SNSUser{" + "\n" +
                "name=" + name + "\n" +
                "address=" + address + "\n" +
                "gender=" + gender + "\n" +
                "phoneNumber=" + phoneNumber + "\n" +
                "email=" + email + "\n" +
                "birthDate=" + birthDate + "\n" +
                "SNSNumber=" + SNSNumber + "\n" +
                "cc=" + cc + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SNSUser)) return false;
        SNSUser snsUser = (SNSUser) o;
        return name.equals(snsUser.name)  || phoneNumber.equals(snsUser.phoneNumber) || email.equals(snsUser.email) || SNSNumber.equals(snsUser.SNSNumber) || cc.equals(snsUser.cc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, phoneNumber, email, SNSNumber, cc);
    }
}
