package app.domain.model;



import org.apache.commons.lang3.RandomStringUtils;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Password Generator for the UserStory: Register an Employee
 * @author Lourenço Mayol -> 1211206@isep.ipp.pt
 */

public class PasswordGenerator
{
    /**
     * @return a String that contains a random password with 3 random uppercase letters, 2 random alphanumeric(if they are letters they will be turned to lowercase letters) and 2 random digits.
     */
    public static String getPassword() {
        StringBuilder randomPwd = new StringBuilder();
        ArrayList<String> randomchar  = new ArrayList<String>();
        randomchar.add(RandomStringUtils.randomNumeric(1));
        randomchar.add(RandomStringUtils.randomNumeric(1));
        randomchar.add(RandomStringUtils.randomAlphabetic(1).toUpperCase(Locale.ROOT));
        randomchar.add(RandomStringUtils.randomAlphabetic(1).toUpperCase(Locale.ROOT));
        randomchar.add(RandomStringUtils.randomAlphabetic(1).toUpperCase(Locale.ROOT));
        randomchar.add(RandomStringUtils.randomAlphanumeric(1).toLowerCase(Locale.ROOT));
        randomchar.add(RandomStringUtils.randomAlphanumeric(1).toLowerCase(Locale.ROOT));
        for(int i = 0; i < 7; i++){
            int index = (int)(Math.random() * randomchar.size());
            randomPwd.append(randomchar.get(index));
            randomchar.remove(index);
        }
        return String.valueOf(randomPwd);
    }
}
