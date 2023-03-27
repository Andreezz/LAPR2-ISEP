package app.domain.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Class for US17
 * @author Lourenço Mayol -> 1211206@isep.ipp.pt
 */
public class CheckTime {

    /** checks if the time is in the correct format (HH:mm)
     * @return true if time is valid, otherwise, returns false
     */
    public static boolean checkTime(String time){
        try{
            LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm").withResolverStyle (ResolverStyle.STRICT));
            return true;
        }catch (DateTimeParseException e) {
            return false;
        }
    }
}
