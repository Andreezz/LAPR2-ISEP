package app.domain.model;

public class HealthCareCenter extends VaccinationCenter {

    /**
     * Constructor of the VaccinationCenter, it calls methods in order to validate the parameters
     *
     * @param name        represents the VaccinationCenter's Name
     * @param email       represents the VaccinationCenter's Email
     * @param phoneNumber represents the VaccinationCenter's Phone Number
     * @param address     represents the VaccinationCenter's Address
     * @param vctype      represents the VaccinationCenter's type
     */
    public HealthCareCenter(String name, String email,String phoneNumber,String address, String webAddress, int faxNumber,
                            String vctype, Double slotDuration, int maxVaccines,  Schedule schedule) {
        super(name,email,phoneNumber,address,webAddress,faxNumber,vctype,slotDuration,maxVaccines, schedule);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
