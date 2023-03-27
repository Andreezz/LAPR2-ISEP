package app.mappers.dto;

import app.domain.model.Schedule;

public class VaccinationCenterDTO {

    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String webAddress;
    private int faxNumber;
    private String vctype;
    private Double slotDuration;
    private int maxVaccines;
    private Schedule schedule;

    public VaccinationCenterDTO(String name, String email, String phoneNumber, String address, String webAddress, int faxNumber,
                                String vctype, Double slotDuration, int maxVaccines, Schedule schedule) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.webAddress = webAddress;
        this.faxNumber = faxNumber;
        this.vctype = vctype;
        this.slotDuration = slotDuration;
        this.maxVaccines = maxVaccines;
        this.schedule = schedule;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public int getFaxNumber() {
        return faxNumber;
    }

    public String getVctype() {
        return vctype;
    }

    public Double getSlotDuration() {
        return slotDuration;
    }

    public int getMaxVaccines() {
        return maxVaccines;
    }

    public Schedule getSchedule() {
        return schedule;
    }
}
