package app.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SNSUserTest {

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
        SNSUser user = new SNSUser("A", "A", "mal0", "35191234567", "coisa@isep.pt", "22/10/1995", "123456789", "12345678-1-AA1");
        //gender has a character that is not a digit
        try{
            user.checkGenderFormat(user.getGender());
        }
        catch (Exception e){
            Assertions.assertEquals("The SNS user's gender must contain only letters!", e.getMessage());
        }
    }

    @Test
    public void checkBirthDateFormat() {
        SNSUser user = new SNSUser("A", "A", "male", "35191234567", "coisa@isep.pt", "22/10/19", "123456789", "12345678-1-AA1");
        //birthdate doesn't have 10 characters
        try{
            user.checkBirthDateFormat(user.getBirthDate());
        }
        catch (Exception e){
            Assertions.assertEquals("birth date must have 10 characters!\nPlease note that the birthdate must follow this format: DD/MM/YYYY", e.getMessage());
        }
        SNSUser user1 = new SNSUser("A", "A", "male", "35191234567", "coisa@isep.pt", "22_10_1995", "123456789", "12345678-1-AA1");
        //birthdate isn't written in the format: DD/MM/YYYY
        try{
            user1.checkBirthDateFormat(user1.getBirthDate());
        }
        catch (Exception e){
            Assertions.assertEquals("birth date must be written in the following format: DD/MM/YYYY", e.getMessage());
        }
        SNSUser user2 = new SNSUser("A", "A", "male", "35191234567", "coisa@isep.pt", "22/10/199S", "123456789", "12345678-1-AA1");
        //birthdate contains a character that is not a digit
        try{
            user2.checkBirthDateFormat(user2.getBirthDate());
        }
        catch (Exception e){
            Assertions.assertEquals("the birthdate's days, months and years must be digits!\nPlease note that the birthdate must follow this format: DD/MM/YYYY", e.getMessage());
        }
        SNSUser user3 = new SNSUser("A", "A", "male", "35191234567", "coisa@isep.pt", "34/10/1995", "123456789", "12345678-1-AA1");
        //days value is invalid according to the month's limit
        try{
            user3.checkBirthDateFormat(user3.getBirthDate());
        }
        catch (Exception e){
            Assertions.assertEquals("days must have a value between 1 and 31 according to the month's limit!Months must have a value between 1 and 12!Years must have a value between 1900 and 2022!\nPlease note that the birthdate must follow this format: DD/MM/YYYY", e.getMessage());
        }
        SNSUser user4 = new SNSUser("A", "A", "male", "35191234567", "coisa@isep.pt", "10/14/1995", "123456789", "12345678-1-AA1");
        //months value does not belong to the interval [1,12]
        try{
            user4.checkBirthDateFormat(user4.getBirthDate());
        }
        catch (Exception e){
            Assertions.assertEquals("days must have a value between 1 and 31 according to the month's limit!Months must have a value between 1 and 12!Years must have a value between 1900 and 2022!\nPlease note that the birthdate must follow this format: DD/MM/YYYY", e.getMessage());
        }
        SNSUser user5 = new SNSUser("A", "A", "male", "35191234567", "coisa@isep.pt", "34/14/2056", "123456789", "12345678-1-AA1");
        //years value does not belong to the interval [1900, 2022]
        try{
            user5.checkBirthDateFormat(user5.getBirthDate());
        }
        catch (Exception e){
            Assertions.assertEquals("days must have a value between 1 and 31 according to the month's limit!Months must have a value between 1 and 12!Years must have a value between 1900 and 2022!\nPlease note that the birthdate must follow this format: DD/MM/YYYY", e.getMessage());
        }
    }
}