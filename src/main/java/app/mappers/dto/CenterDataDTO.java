package app.mappers.dto;

import app.domain.model.DateTime.ArrivalDateTime;
import app.domain.model.DateTime.LeavingDateTime;
import app.domain.model.DateTime.NurseAdminDateTime;
import app.domain.model.DateTime.ScheduleDateTime;
import org.apache.commons.lang3.StringUtils;

public class CenterDataDTO {

    private ArrivalDateTime arr;

    private ScheduleDateTime sch;

    private NurseAdminDateTime admin;

    private LeavingDateTime leave;

    private String SNSnum;

    private String lot;

    private String vacName;

    private String currentDose;

    public CenterDataDTO(ArrivalDateTime arr, ScheduleDateTime sch, NurseAdminDateTime admin, LeavingDateTime leave, String SNSnum, String lot, String vacName, String currentDose){
        this.arr = arr;
        this.sch = sch;
        this.admin = admin;
        this.leave = leave;
        this.currentDose = currentDose;
        this.SNSnum = SNSnum;
        this.lot = lot;
        this.vacName = vacName;
    }


    public ArrivalDateTime getArr() {
        return arr;
    }

    public ScheduleDateTime getSch() {
        return sch;
    }

    public NurseAdminDateTime getAdmin() {
        return admin;
    }

    public LeavingDateTime getLeave() {
        return leave;
    }

    public String getSNSnum() {
        return SNSnum;
    }

    public String getLot() {
        return lot;
    }

    public String getVacName() {
        return vacName;
    }

    public String getCurrentDose() {
        return currentDose;
    }

}
