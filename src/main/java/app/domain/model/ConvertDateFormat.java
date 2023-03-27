package app.domain.model;

/**
 * Class for US17
 * @author Lourenço Mayol -> 1211206@isep.ipp.pt
 */
public class ConvertDateFormat {

    /** converts date format M/DD/YYYY from the legacy system, to DD/MM/YYYY
     */

    public static String convert(String date){
        try{
            if (!date.contains("/")) {
                throw new IllegalArgumentException("File's date is not separated by / ");
            }
            String [] dateParts = date.split("/");
            String day, month, year;
            day = dateParts[1];
            month = dateParts[0];
            year = dateParts[2];
            StringBuilder convertedDate = new StringBuilder();
            convertedDate.append(day);
            convertedDate.append("/");
            if(month.length() == 1){
                convertedDate.append("0");
                convertedDate.append(month);
                convertedDate.append("/");
            }
            else{
                convertedDate.append(month);
                convertedDate.append("/");
            }
            convertedDate.append(year);
            return String.valueOf(convertedDate);
        }
        catch (Exception e){
            return null;
        }
    }

}
