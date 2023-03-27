package app.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleVaccineSNSUserTest {

    @Test
    void getNumber() {
    }

    @Test
    void getBirthdate() {
    }

    @Test
    void getGender() {
    }

    @Test
    void getVaccine() {
    }

    @Test
    void getDate() {
    }

    @Test
    void getHours() {
    }

    @Test
    void getNameCenter() {
    }

    @Test
    void checkNumber() {
        SNSUser user = new SNSUser("sakcnqnc", "klckqw ckqlw", "qkcnqc", "35191234567", "iyvyuyvu", "ASDFF", "1111111", "12345678-1-AA1");
        try{
            user.checkSNSNumber(user.getSNSNumber());
        }
        catch (Exception e){
            Assertions.assertEquals("SNS number must have 9 characters and can only contain digits!", e.getMessage());
        }
        SNSUser user1 = new SNSUser("A", "A", "male", "35191234567", "coisa@isep.pt", "22/10/1995", "12345678A", "12345678-1-AA1");
        try{
            user1.checkSNSNumber(user1.getSNSNumber());
        }
        catch (Exception e){
            Assertions.assertEquals("SNS number must have 9 characters and can only contain digits!", e.getMessage());
        }
    }

    @Test
    void checkBirthDateFormat() {
    }

    @Test
    void checkgender() {
        SNSUser user = new SNSUser("MAkn", "NON", "neve", "commqwdqkl", "000", "non", "100000000", "cnjkcqkcqjk");
        try{
            user.checkGenderFormat(user.getGender());
        }
        catch (Exception e){
            Assertions.assertEquals("The SNS user's gender was badly introduced!", e.getMessage());
        }
    }

    @Test
    void checkdate() {
    }

    @Test
    void checkhours() {
    }

    @Test
    void checkNameCenter() {
    }

    @Test
    void checkNameVaccine() {
    }

    @Test
    void testToString() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }
}