package app.domain.model;

/**
 * Class for US17
 * @author Lourenço Mayol -> 1211206@isep.ipp.pt
 */
public class ConvertTimeFormat {

    /** converts time format H:mm  from the legacy system, to HH:mm
     */
    public static String convert(String time){
        try{
            if (!time.contains(":")) {
                throw new IllegalArgumentException("File's time is not separated by :");
            }
            String[] timeParts = time.split(":");
            String hr, min;
            hr = timeParts[0];
            min = timeParts[1];
            StringBuilder convertTime = new StringBuilder();
            convertTime.append("0");
            convertTime.append(hr);
            convertTime.append(":");
            convertTime.append(min);
            return String.valueOf(convertTime);
        }
        catch (Exception e){
            return null;
        }

    }
}
