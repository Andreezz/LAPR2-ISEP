package app.domain.model;

import app.ui.console.AdverseReacUI;
import app.domain.model.AdverseReac;
import app.domain.stores.AdverseReacStore;
import app.domain.shared.Constants;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdverseReacTest {

    @Test
    void checkSnsNumber(){
        String invalidSNSnumber = "12345";
        String validSNSnumber = "123456789";
        AdverseReac AdRea = new AdverseReac( invalidSNSnumber, "fever and pain in the head");
        AdverseReac AdvRea = new AdverseReac( validSNSnumber, "fever and pain in the head");

        Assertions.assertFalse(AdRea.checkSnsNumber());
        Assertions.assertTrue(AdvRea.checkSnsNumber());


    }

}
