package app.domain.model;


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
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.math.NumberUtils;



public class ScheduleVaccine implements Serializable {

    private static final long serialVersionUID = 8L;
    private String number;
    private String birthdate;
    private String gender;
    private String vaccine;
    private String date;
    private String hours;
    private String NameCenter;


    public ScheduleVaccine(String number, String birthdate, String gender, String vaccine, String date, String hours, String NameCenter) {
        this.number = number;
        this.birthdate = birthdate;
        this.gender = gender;
        this.vaccine = vaccine;
        this.date = date;
        this.hours = hours;
        this.NameCenter = NameCenter;
    }

    public String getNumber() {
        return number;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getGender() {
        return gender;
    }

    public String getVaccine() {
        return vaccine;
    }

    public String getDate() {
        return date;
    }

    public String getHours() {
        return hours;
    }

    public String getNameCenter() {
        return NameCenter;
    }

    public boolean checkNumber(String number) {
        String NumberRegex = "^[0-9]{9}$";
        Pattern pattern = Pattern.compile(NumberRegex);
        if (!pattern.matcher(number).matches()) {
            throw new IllegalArgumentException("SNS number must have 9 characters and can only contain digits!");
        }
        return true;
    }

    public boolean checkBirthDateFormat(String birthDate) {
        if (birthDate.length() != 10)
            throw new IllegalArgumentException("birth date must have 10 characters!\nPlease note that the birthdate must follow this format: DD/MM/YYYY");
        else {
            String check = String.valueOf(birthDate.charAt(2)) + String.valueOf(birthDate.charAt(5));
            if (!check.equals("//"))
                throw new IllegalArgumentException("birth date must be written in the following format: DD/MM/YYYY");
            else {
                String[] dateParts = birthDate.split("/");
                if (!(NumberUtils.isDigits(dateParts[0]) && NumberUtils.isDigits(dateParts[1]) && NumberUtils.isDigits(dateParts[2])))
                    throw new IllegalArgumentException("the birthdate's days, months and years must be digits!\nPlease note that the birthdate must follow this format: DD/MM/YYYY");
                else {
                    int days = Integer.parseInt(dateParts[0]);
                    int months = Integer.parseInt(dateParts[1]);
                    int years = Integer.parseInt(dateParts[2]);
                    if (!(days > 0 && days < (monthLenght(months, years) + 1) && months > 0 && months < 13 && years > 1899 && years < 2023))
                        throw new IllegalArgumentException("days must have a value between 1 and 31 according to the month's limit!Months must have a value between 1 and 12!Years must have a value between 1900 and 2022!\nPlease note that the birthdate must follow this format: DD/MM/YYYY");
                }
            }
        }
        return true;
    }

    public boolean checkgender(String gender) {
        if (!gender.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("The SNS user's gender must contain only letters!");
        }
        return true;
    }

    public boolean checkdate(String date) {

        try {
            LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("uuuu-M-d")
                            .withResolverStyle(ResolverStyle.STRICT)
            );

        } catch (DateTimeParseException e) {
            System.out.println("Try yyyy-m-d");

        }

        return true;
    }

    public boolean checkhours(String hours) {
        try {
            LocalTime.parse(hours);
        } catch (DateTimeParseException | NullPointerException e) {
            System.out.println("Invalid time try HH:mm");
        }
        return true;
    }

    public boolean checkNameCenter(String NameCenter) {
        if (StringUtils.isBlank(NameCenter))
            throw new IllegalArgumentException("Name cannot be blank.");
        if (NameCenter.length() > Constants.MAX_VACCINATION_CENTER_NAME)
            throw new IllegalArgumentException("Name must have at maximum 20 chars.");
        return true;
    }

    public boolean checkNameVaccine(String vaccine) {
        String[] nameParts = vaccine.split(" ");
        for (int i = 0; i < nameParts.length; i++) {
            if (!nameParts[i].matches("[a-zA-ZáàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ']+")) {
                throw new IllegalArgumentException("Vaccine´s name must contain only letters!");
            }
        }
        return true;
    }

    private int monthLenght(int month, int year) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                if (isLeapYear(year))
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


    @Override
    public String toString() {
        return "ScheduleVaccine{" +
                "number='" + number + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", gender='" + gender + '\'' +
                ", vaccine='" + vaccine + '\'' +
                ", date='" + date + '\'' +
                ", hours='" + hours + '\'' +
                ", NameCenter='" + NameCenter + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleVaccine that = (ScheduleVaccine) o;
        return number.equals(that.number) && birthdate.equals(that.birthdate) && gender.equals(that.gender) && vaccine.equals(that.vaccine) && date.equals(that.date) && hours.equals(that.hours) && NameCenter.equals(that.NameCenter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, birthdate, gender, vaccine, date, hours, NameCenter);
    }
}
