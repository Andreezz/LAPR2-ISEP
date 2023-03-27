package app.domain.model;

import app.domain.shared.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VaccineTypeTest {

    @Test
    void checkVaccineTypeCodeRules(){
        VaccineType vaccineTypeA =new VaccineType("12312", Constants.TECH_1_TYPE,Constants.TECH_1);
        try{
            vaccineTypeA.checkVaccineTypeCodeRules("3518182");
        }
        catch (Exception e){
            Assertions.assertEquals("Vaccine Type Code must have 5 numbers.", e.getMessage());
        }
        //Code can only contain numbers
        try {
            vaccineTypeA.checkVaccineTypeCodeRules("AAAAA");
        }
        catch (Exception e){
            Assertions.assertEquals("Vaccine Type Code must have 5 numbers.", e.getMessage());
        }
        //code can't be null
        try{
            vaccineTypeA.checkVaccineTypeCodeRules("");
        }
        catch (Exception e){
            Assertions.assertEquals("Vaccine Type Code cannot be blank.",e.getMessage());
        }

    }

    @Test
    void testToString() {
        VaccineType vaccineTypeA =new VaccineType("12312", Constants.TECH_1_TYPE,Constants.TECH_1);
        String expected = "Vaccine Type: \n" +
                "Code -> "  + "12312" +
                "\nTech -> "  + Constants.TECH_1+
                "\nType -> " + Constants.TECH_1_TYPE;

        Assertions.assertEquals(expected, vaccineTypeA.toString());
    }

}