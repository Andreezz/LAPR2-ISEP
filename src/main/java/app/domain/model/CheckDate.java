package app.domain.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Class for US17
 * @author Lourenço Mayol -> 1211206@isep.ipp.pt
 */
public class CheckDate {

    /** checks if a date is in the correct format (DD/MM/YYYY)
     * @return true if date is valid, otherwise, returns false
     */
    public static boolean checkDate(String date){
        try{
            LocalDate.parse(date,DateTimeFormatter.ofPattern ("dd/MM/uuuu").withResolverStyle (ResolverStyle.STRICT));
            return true;
        } catch (Exception e) {
            System.err.println("Error 9000 : Date Invalid! Date must be in the format dd/MM/yyyy");
            return false;
        }
    }
}
