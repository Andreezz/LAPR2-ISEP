package app.domain.model;

import app.domain.model.DateTime.ArrivalDateTime;
import app.domain.model.DateTime.LeavingDateTime;
import app.domain.model.DateTime.NurseAdminDateTime;
import app.domain.model.DateTime.ScheduleDateTime;
import app.domain.shared.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;


/**
 * Main Class for US17
 * @author Lourenço Mayol -> 1211206@isep.ipp.pt
 */
public class CenterData implements Serializable {

    /** attributes that should be extracted from each of the file's line
     */

    private static final long serialVersionUID = 1L;
    private ArrivalDateTime arr;

    private ScheduleDateTime sch;

    private NurseAdminDateTime admin;

    private LeavingDateTime leave;

    private String SNSnum;

    private String lot;

    private String vacName;

    private String currentDose;

    /** Checks if the parameters are blank (no need to check dates and times because they are checked in DateTime constructor), if they are an exception is thrown.
     * @param arrDate arrival date
     * @param arrTime arrival time
     * @param schDate schedule date
     * @param schTime schedule time
     * @param adminDate nurse administration date
     * @param adminTime nurse administration time
     * @param leaveDate leave date
     * @param leaveTime leave time
     * @param SNSnum SNSUser's SNS number
     * @param lot lot number
     * @param vacName administrated vaccine name
     * @param currentDose dose that was administrated
     */
    public CenterData(String arrDate, String arrTime, String schDate, String schTime, String adminDate,String adminTime, String leaveDate,String leaveTime, String SNSnum, String lot, String vacName, String currentDose){
        if(StringUtils.isNoneBlank(SNSnum,lot,vacName,currentDose)){
            this.arr = new ArrivalDateTime(arrDate, arrTime);
            this.admin = new NurseAdminDateTime(adminDate, adminTime);
            this.sch = new ScheduleDateTime(schDate, schTime);
            this.leave = new LeavingDateTime(leaveDate, leaveTime);
            this.currentDose = currentDose;
            this.SNSnum = SNSnum;
            this.lot = lot;
            this.vacName = vacName;
        }else throw new IllegalArgumentException("There cannot be Center Data blank center data attributes!");
    }

    /** Validates an SNS number by checking if this has the correct number of digits.
     * @return true if the number is valid, otherwise, returns false.
     */
    public boolean validateSNSnumber(){
        return this.SNSnum.length() == Constants.SNS_NUMBER_DIGITS;
    }

    /** Validates the CenterData instance by checking the SNS number, date and time formats.
     * @return true if the CenterData instance is valid, otherwise, returns false.
     */
    public boolean validateCenterData(){
        /*System.out.println("SNS RESULT:"+validateSNSnumber());
        System.out.println("LOT RESULT:"+validateLotNumber());
        System.out.println("ARR RESULT:"+arr.validate());
        System.out.println("SCH RESULT:"+sch.validate());
        System.out.println("LEAVE RESULT:"+leave.validate());
        System.out.println("ADMIN RESULT:"+admin.validate());*/
        return validateSNSnumber() && validateLotNumber() && arr.validate() && sch.validate() && leave.validate() && admin.validate();
    }

    public boolean validateLotNumber(){
        String[] lotParts;
        String lotRegex = "^[a-zA-Z0-9]*$";
        Pattern pattern = Pattern.compile(lotRegex);
        try {
            if(this.lot.contains("-"))
                lotParts = this.lot.split("-");
            else throw new IllegalArgumentException("lot number must contain an hyphen");
            if(!pattern.matcher(lotParts[0]).matches())
                throw new IllegalArgumentException("The first five characters of the lot number must be alphanumeric characters!");
            if(!NumberUtils.isDigits(lotParts[1]))
                throw new IllegalArgumentException("The last two digits of the lot number must be digits!");
            return true;
        }
        catch (Exception e){
            return false;
        }
    }


    /** @return the instance's registered SNS number
     */
    public String getSNSnum() {return this.SNSnum;}

    /** @return the instance's registered Lot number
     */
    public String getLot() {return this.lot;}

    /** @return the instance's registered vaccine name
     */
    public String getVacName() {return this.vacName;}

    /** @return the instance's registered current dose
     */
    public String getCurrentDose() {return this.currentDose;}

    public ArrivalDateTime getArr() {return arr;}

    public LeavingDateTime getLeave() {return leave;}

    public ScheduleDateTime getSch() {return sch;}

    public NurseAdminDateTime getAdmin() {return admin;}

    /** @return a text description of the class
     */

    @Override
    public String toString() {
        return "CenterData:" + "\n\n"+
                arr.toString(arr) +
                sch.toString(sch) +
                admin.toString(admin) +
                leave.toString(leave) +
                "SNSnumber=" + SNSnum + "\n" +
                "Lot Number=" + lot + "\n" +
                "Vaccine name=" + vacName + "\n" +
                "Current dose=" + currentDose + "\n\n";
    }

    /** Checks if an object is equal to the CenterData's instance.
     *  There can't be two objects with the same SNS number and the same currentDose or two objects with a dateTime registered for the same SNSnumber.
     *  If any of these cases are detected the objects are considered equals.
     * @param o Object instance to be checked.
     * @return true if the objects are considered equal, otherwise, returns false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CenterData that = (CenterData) o;
        return (SNSnum.equals(that.SNSnum) && currentDose.equals(that.currentDose)) || (SNSnum.equals(that.SNSnum) && arr.equals(that.arr)) || (SNSnum.equals(that.SNSnum) && sch.equals(that.sch))
                || (SNSnum.equals(that.SNSnum) && sch.equals(that.sch)) || (SNSnum.equals(that.SNSnum) && leave.equals(that.sch)) || (SNSnum.equals(that.SNSnum) && admin.equals(that.sch));
    }

    @Override
    public int hashCode() {
        return Objects.hash(arr, sch, admin, leave, SNSnum, lot, vacName, currentDose);
    }
}
