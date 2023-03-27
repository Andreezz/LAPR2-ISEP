package app.domain.model;

import app.domain.shared.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VaccinationCenterTest {

    private HealthCareCenter vaccinationCenterA = new HealthCareCenter( "Vacinasdoze", "a@a.pt",
            "960000000", "aaaa", "www.morbius.pt", 343543,Constants.VC_TYPE_1,
            5.5, 10, new Schedule("14:00", "20:00"));

    private MassVaccinationCenter vaccinationCenterB = new MassVaccinationCenter("Vacinasdoze", "a@a.pt",
            "960000000", "aaaa", "www.morbius.pt", 343543,Constants.VC_TYPE_2,
            5.5, 10, new Schedule("14:00", "20:00"));

    @Test
    void checkEmailRules() {

        //Email can't be null
        try {
            vaccinationCenterA.checkEmailRules("");
            vaccinationCenterB.checkEmailRules("");
        } catch (Exception e) {
            Assertions.assertEquals("Email cannot be blank.", e.getMessage());
        }
        //Email must contain @ and a .
        try {
            vaccinationCenterA.checkEmailRules("aa");
            vaccinationCenterB.checkEmailRules("aB");
        } catch (Exception e) {
            Assertions.assertEquals("Invalid Email.", e.getMessage());
        }
        try {
            vaccinationCenterA.checkEmailRules("aa@aa");
            vaccinationCenterB.checkEmailRules("aa@aB");
        } catch (Exception e) {
            Assertions.assertEquals("Invalid Email.", e.getMessage());
        }
        try {
            vaccinationCenterA.checkEmailRules("aa@aa.");
            vaccinationCenterB.checkEmailRules("aa@aB.");
        } catch (Exception e) {
            Assertions.assertEquals("Invalid Email.", e.getMessage());
        }

    }

    @Test
    void checkNameRules() {

        //name can't be null
        try {
            vaccinationCenterA.checkNameRules("");
            vaccinationCenterB.checkNameRules("");
        } catch (Exception e) {
            Assertions.assertEquals("Name cannot be blank.", e.getMessage());
        }
        //name must not surpass 20 chars
        try {
            vaccinationCenterA.checkNameRules("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            vaccinationCenterB.checkNameRules("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        } catch (Exception e) {
            Assertions.assertEquals("Name must have at maximum 20 chars.", e.getMessage());
        }

    }

    @Test
    void checkAddressRules() {

        //address can't be null
        try {
            vaccinationCenterA.checkAddressRules("");
            vaccinationCenterB.checkAddressRules("");
        } catch (Exception e) {
            Assertions.assertEquals("Address cannot be blank.", e.getMessage());
        }
        //address must not surpass 30 chars
        try {
            vaccinationCenterA.checkAddressRules("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            vaccinationCenterB.checkAddressRules("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        } catch (Exception e) {
            Assertions.assertEquals("Address has, at maximum, 30 chars.", e.getMessage());
        }
    }
    @Test
    void checkPhoneNumberRules(){

        //phone number doesn't have 9 digits
        try{
            vaccinationCenterA.checkPhoneNumberRules("8288");
            vaccinationCenterB.checkPhoneNumberRules("8288");
        }
        catch (Exception e){
            Assertions.assertEquals("Phonenumber must have 9 digits!", e.getMessage());
        }
        //phone number has a character that is not a digit
        try{
            vaccinationCenterA.checkPhoneNumberRules("91234567A");
            vaccinationCenterB.checkPhoneNumberRules("91234567B");
        }
        catch (Exception e){
            Assertions.assertEquals("All the characters in the phone number must be digits! Symbols or letters are not valid!", e.getMessage());
        }
        //wrong indicative
        try{
            vaccinationCenterA.checkPhoneNumberRules("952345678");
            vaccinationCenterB.checkPhoneNumberRules("952345679");
        }
        catch (Exception e){
            Assertions.assertEquals("Phone number indicative must be 91 or 93 or 96!", e.getMessage());
        }
    }



    @Test
    void testToString() {

        String expected = "---------------------------------------\n"+
                "\t. Vaccination Center -> "+"Health Care Center"+"\n"+
                "\t. Name -> "+"Vacinasdoze"+"\n"+
                "\t. Id -> "+"12312"+"\n"+
                "\t. Address -> "+"aaaa"+"\n"+
                "\t. Phone Number -> "+"960000000"+"\n"+
                "\t. Email -> "+"a@a.pt"+"\n"+
                "\t. Open Hour -> "+"08:00"+"\n"+
                "\t. Close Hour -> "+"21:00"+"\n"+
                "--------------------------------------";

        String expected2 ="---------------------------------------\n"+
                "\t. Vaccination Center -> "+"Mass Vaccination Center"+"\n"+
                "\t. Name -> "+"Vacinasdoze"+"\n"+
                "\t. Id -> "+"12313"+"\n"+
                "\t. Address -> "+"aaaa"+"\n"+
                "\t. Phone Number -> "+"960000000"+"\n"+
                "\t. Email -> "+"a@a.pt"+"\n"+
                "\t. Open Hour -> "+"08:00"+"\n"+
                "\t. Close Hour -> "+"21:00"+"\n"+
                "--------------------------------------";

        Assertions.assertEquals(expected, vaccinationCenterA.toString());
        Assertions.assertEquals(expected2, vaccinationCenterB.toString());
    }
}
