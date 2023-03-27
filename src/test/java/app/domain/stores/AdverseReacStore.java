package app.domain.stores;

import app.domain.model.AdverseReac;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdverseReacStore {

        private AdverseReac AdverseReactions = new AdverseReac("123456789", "fever and pain in the head");
        private void saveNewAdRea(AdverseReac adverseReactions) {
        }

        @Test
        boolean createAdRea(String snsnumber, String adversereactions) {
        AdverseReacStore AdRea = new AdverseReacStore();
        String actual= String.valueOf(AdverseReactions);
        String expected = String.valueOf(AdRea.createAdRea("123456789", "fever and pain in the head"));
        Assertions.assertEquals(actual, expected);
            return false;
        }

        @Test
        boolean getAdreaction(){
                AdverseReacStore AdRea = new AdverseReacStore();
                AdverseReac AdverseReactions = new AdverseReac("123456789", "fever and pain in the head");
                String expected1 = String.valueOf(AdverseReactions);
                AdRea.createAdRea( "123456789", "fever and pain in the head");
                AdRea.saveNewAdRea(AdverseReactions);
                String expected = String.valueOf(AdRea.getAdreaction());
                Assertions.assertEquals(expected, expected1);
                return false;
        }



}
