package app.mappers.dto;

import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;

public class ArrivalDTO {

    private String date;
    private String time;
    private SNSUser user;
    private VaccinationCenter vaccCenter;

    public ArrivalDTO(String date, String time, SNSUser user, VaccinationCenter vaccCenter) {
        this.date = date;
        this.time = time;
        this.user = user;
        this.vaccCenter = vaccCenter;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public SNSUser getUser() {
        return user;
    }

    public VaccinationCenter getVaccCenter() {
        return vaccCenter;
    }
}
