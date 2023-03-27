package app.domain.model;

import app.domain.stores.EmployeeStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    @Test
    void createNullEmployee(){
        //an exception will be activated because values are null
        try{
            Employee employeeA =new Employee(null,null, null, null,null,null);
        }
        catch (Exception e){
            Assertions.assertEquals("Employee cannot have null attributes!", e.getMessage());
        }

    }

    @Test
    void checkPhoneNumberFormat() {
        Employee employeeA =new Employee("rua A","aaaa", "912345678", "00000","coisa@isep.pt","39849069-4-ZV7");
        //phone number doesn't have 9 digits
        try{
            employeeA.checkPhoneNumberFormat("8288");
        }
        catch (Exception e){
            Assertions.assertEquals("Phonenumber must have 9 digits!", e.getMessage());
        }
        //phone number has a character that is not a digit
        try{
            employeeA.checkPhoneNumberFormat("91234567A");
        }
        catch (Exception e){
            Assertions.assertEquals("All the characters in the phone number must be digits! Symbols or letters are not valid!", e.getMessage());
        }
        //wrong indicative
        try{
            employeeA.checkPhoneNumberFormat("952345678");
        }
        catch (Exception e){
            Assertions.assertEquals("Phone number indicative must be 91 or 93 or 96!", e.getMessage());
        }
    }

    @Test
    void checkEmailFormat() {
        Employee employeeA = new Employee("rua A","aaaa", "351912345678", "00000","coisa@isep.pt","39849069-4-ZV7");
        //value should be false because email is in the wrong format
        Assertions.assertFalse(employeeA.checkEmailFormat("coisa@coisa"));
    }

    @Test
    void checkCCFormat() {
        Employee employeeA = new Employee("rua A","aaaa", "351912345678", "00000","coisa@isep.pt","39849069-4-ZV7");
        try{
            employeeA.checkCCFormat("3518182");
        }
        catch (Exception e){
            Assertions.assertEquals("cc format 11111111-1-AA1 must have 14 characters!", e.getMessage());
        }
        //cc doesn't have the (-) signs separating its parts correctly
        try{
            employeeA.checkCCFormat("398-490694ZV-7");
        }
        catch (Exception e){
            Assertions.assertEquals("cc must be written in the following format: 11111111-1-AA1", e.getMessage());
        }
        //cc has letters where it should be digits
        try{
            employeeA.checkCCFormat("39849A69-4-ZV7");
        }
        catch (Exception e){
            Assertions.assertEquals("cc must be written in the following format: 11111111-1-AA1 (1 represents a digit)", e.getMessage());
        }
        //cc has digits where it should be letters
        try{
            employeeA.checkCCFormat("39849569-4-Z17");
        }
        catch (Exception e){
            Assertions.assertEquals("cc must be written in the following format: 11111111-1-AA1 (A represents an uppercase letter)", e.getMessage());
        }

    }

    @Test
    void checkNameFormat() {
        Employee employeeA = new Employee("rua A","aaaa", "351912345678", "00000","coisa@isep.pt","39849069-4-ZV7");
        try{
            employeeA.checkNameFormat("Mari0");
        }
        catch (Exception e){
            Assertions.assertEquals("Employee's name must contain only letters! (ç is not accepted)", e.getMessage());
        }
    }

    @Test
    void testEquals() {
        Employee employeeA = new Employee("rua A","aaaa", "351912345678", "00000","coisa@isep.pt","39849069-4-ZV7");
        Employee employeeB = new Employee("rua A","aaaa", "351912345678", "00000","coisa@isep.pt","39849069-4-ZV7");
        //employees are equal
        assertEquals(employeeA, employeeB);
        //employees have an equal attribute (in this case their names is the same)
        Employee employeeC = new Employee("rua B","aaaa", "351912345600", "00001","coisa@coisa.pt","39849069-4-ZV8");
        assertEquals(employeeA, employeeC);
    }

    @Test
    void testToString(){
        Employee employeeA = new Employee("rua A","aaaa", "351912345678", "00000","coisa@isep.pt","39849069-4-ZV7");
        String expected = "Employee{" + "\n" +
                "address=" + "rua A" + "\n" +
                "name=" + "aaaa" +  "\n" +
                "phoneNumber=" + "351912345678" + "\n" +
                "id=" + "00000" + "\n" +
                "email=" + "coisa@isep.pt" + "\n" +
                "cc=" + "39849069-4-ZV7" + "\n" +
                '}';
        Assertions.assertEquals(expected, employeeA.toString());
    }
}