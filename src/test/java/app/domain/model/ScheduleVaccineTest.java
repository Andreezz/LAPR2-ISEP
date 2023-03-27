package app.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleVaccineTest {

    @Test
    public void checkSNSNumber() {
        SNSUser user = new SNSUser("A", "A", "male", "35191234567", "coisa@isep.pt", "22/10/1995", "12345678", "12345678-1-AA1");
        //SNS number doesn't have 9 digits
        try{
            user.checkSNSNumber(user.getSNSNumber());
        }
        catch (Exception e){
            Assertions.assertEquals("SNS number must have 9 characters and can only contain digits!", e.getMessage());
        }
        SNSUser user1 = new SNSUser("A", "A", "male", "35191234567", "coisa@isep.pt", "22/10/1995", "12345678A", "12345678-1-AA1");
        //SNS number has a character that is not a digit
        try{
            user1.checkSNSNumber(user1.getSNSNumber());
        }
        catch (Exception e){
            Assertions.assertEquals("SNS number must have 9 characters and can only contain digits!", e.getMessage());
        }
    }
    @Test
    public void checkGenderFormat() {
        ScheduleVaccine SchedulevaccineA = new ScheduleVaccine("123456789", "10/10/2002", "masculine", "pfizer", "10/10/2022", "10:10", "ASas");        //gender has a character that is not a digit
        try{
            SchedulevaccineA.checkgender(SchedulevaccineA.getGender());
        }
        catch (Exception e){
            Assertions.assertEquals("The SNS user's gender must contain only letters!", e.getMessage());
        }
    }

        private ScheduleVaccine SchedulevaccineA = new ScheduleVaccine("123456789", "10/10/2002", "masculine", "pfizer", "10/10/2022", "10:10", "ASas");

    @Test
    void testToString() {

        String expected = "ScheduleVaccine{" +
                "number='" + "123456789" + '\'' +
                ", birthdate='" + "10/10/2002" + '\'' +
                ", gender='" + "masculine" + '\'' +
                ", vaccine='" + "pfizer" + '\'' +
                ", date='" + "10/10/2022" + '\'' +
                ", hours='" + "10:10" + '\'' +
                ", NameCenter='" + "ASas" + '\'' +
                '}';

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

        Assertions.assertEquals(expected, SchedulevaccineA.toString());
    }



}