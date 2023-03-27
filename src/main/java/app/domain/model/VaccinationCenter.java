package app.domain.model;

import app.domain.shared.Constants;
import app.domain.stores.ArrivalStore;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;


import java.io.Serializable;
import java.util.regex.Pattern;

/** Class that represents a Vaccination Center
 *@author André Gonçalves <1210804@isep.ipp.pt>
 */
public abstract class VaccinationCenter implements Serializable {

    private static final long serialVersionUID = 5L;

    private final String name;
    private final String email;
    private final String phoneNumber;
    private final String address;
    private final String webAddress;
    private final int faxNumber;
    private final String vctype;
    private final Double slotDuration;
    private final int maxVaccines;
    private transient final  Schedule schedule;

    private ArrivalStore arrStore;

    /** Constructor of the VaccinationCenter, it calls methods in order to validate the parameters
     * @param name represents the VaccinationCenter's Name
     * @param email represents the VaccinationCenter's Email
     * @param phoneNumber represents the VaccinationCenter's Phone Number
     * @param address represents the VaccinationCenter's Address
     * @param vctype represents the VaccinationCenter's type
     * @param schedule represents the VaccinationCenter's opening and closing hours
     */
    public VaccinationCenter(String name, String email,String phoneNumber,String address, String webAddress, int faxNumber,
                             String vctype, Double slotDuration, int maxVaccines,  Schedule schedule) {
        checkNameRules(name);
        checkEmailRules(email);
        checkAddressRules(address);
        checkPhoneNumberRules(phoneNumber);

        this.name=name;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.webAddress = webAddress;
        this.faxNumber = faxNumber;
        this.vctype=vctype;
        this.slotDuration = slotDuration;
        this.maxVaccines = maxVaccines;
        this.schedule = schedule;

        this.arrStore = new ArrivalStore();
        this.arrStore.loadToLocalList();

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

    public String getVctype() {
        return vctype;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public int getFaxNumber() {
        return faxNumber;
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

    public ArrivalStore getArrivalStore() {
        return this.arrStore;
    }

    /**
     * This method checks if the code provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param id Vaccination Center's ID
     */
    public void checkVaccinationCenterIDRules(String id) {
        if (StringUtils.isBlank(id))
            throw new IllegalArgumentException("Vaccination Center ID cannot be blank.");
        if (id.length() != Constants.MAX_CODE)
            throw new IllegalArgumentException("Vaccination Center ID must have 5 number.");
    }
    /**
     * This methode checks if email is correct.
     * @param email unique email that belongs to client
     */

    public void checkEmailRules(String email) {

        if (StringUtils.isBlank(email))
            throw new IllegalArgumentException("Email cannot be blank.");

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (!pat.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid Email.");
        }
    }

    /**
     * This method checks if the name provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param name name of the Vaccination Center
     */
    public void checkNameRules(String name) {
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be blank.");
        if (name.length() > Constants.MAX_VACCINATION_CENTER_NAME)
            throw new IllegalArgumentException("Name must have at maximum 20 chars.");
    }

    /**
     * This method checks if the address provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param address address of the Vaccination Center
     */
    public void checkAddressRules(String address) {
        if (StringUtils.isBlank(address))
            throw new IllegalArgumentException("Address cannot be blank.");
        if (address.length() > Constants.MAX_VACCINATION_CENTER_ADDRESS)
            throw new IllegalArgumentException("Address has, at maximum, 30 chars.");
    }


    /**
     * @param phoneNumber represents the phone number to be checked
     * The phone number is valid if this follows these rules:
     * phone number must have 12 characters (inculding portuguese prefix)
     * all of the characters in the phone number must be digits
     * if the prefix is 351 and the indicative is 91, 93 or 96
     * @return true if the phone number is valid
     */
    public boolean checkPhoneNumberRules (String phoneNumber){
        int numberDigits = phoneNumber.length();
        //check number of digits
        if (numberDigits != Constants.PHONE_NUMBER_DIGITS) {
            throw new IllegalArgumentException("Phonenumber must have 9 digits!");
        }
        //check String is a number
        else {
            if (!NumberUtils.isDigits(phoneNumber))
                throw new IllegalArgumentException("All the characters in the phone number must be digits! Symbols or letters are not valid!");
            else {
                String indicative = String.valueOf(phoneNumber.charAt(0)) + String.valueOf(phoneNumber.charAt(1));
                if (!((indicative.equals("93") || indicative.equals("91") || indicative.equals("96") || indicative.equals("95"))))
                    throw new IllegalArgumentException("Phone number indicative must be 91 or 93 or 95 or 96!");
            }
        }
        return true;
    }


    /**
     * @return A string with the format Vaccination Center's: name= name, address= address, id= id, phonenumber= phoneNumber, Vaccination Center type=vctype
     * ,Email= email, Open Hour= openhour, Close Hour= closehour
     */
    @Override
    public String toString() {
        return "---------------------------------------\n"+
                "\t. Vaccination Center -> "+this.vctype+"\n"+
                "\t. Name -> "+this.name+"\n"+
                "\t. Address -> "+this.address+"\n"+
                "\t. Phone Number -> "+this.phoneNumber+"\n"+
                "\t. Fax Number -> "+this.faxNumber+"\n"+
                "\t. Email -> "+this.email+"\n"+/*+
                "\t. Open Hour -> "+this.schedule.getOpenHours()+"\n"+
                "\t. Close Hour -> "+this.schedule.getCloseHours()+"\n"+*/
                "--------------------------------------";

    }


}
