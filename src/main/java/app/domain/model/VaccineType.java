package app.domain.model;

import app.domain.shared.Constants;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Class that represents a Vaccine Type
 * @author André Gonçalves <1210804@isep.ipp.pt>
 */
public class VaccineType implements Serializable {


    private static final long serialVersionUID = 11L;

    private final String code;
    private final String type;
    private final String tech;

    /**
     * Constructor of the VaccineType, it calls methods in order to validate the parameters
     *
     * @param code represents the VaccineType code
     * @param type represents the VaccineType Name
     * @param tech represents the VaccineType teck
     */

    public VaccineType(String code, String type, String tech) {
        checkVaccineTypeCodeRules(code);
        this.code = code;
        this.type = type;
        this.tech = tech;
    }

    public String getType() {
        return type;
    }

    public String getTech() {
        return tech;
    }

    public String getCode() {
        return code;
    }

    /**
     * This method checks if the code provided meets the requirements, if not it throws a exception making the execution to stop
     *
     * @param code Vaccine Type Code
     */
    public void checkVaccineTypeCodeRules(String code) {
        if (StringUtils.isBlank(code))
            throw new IllegalArgumentException("Vaccine Type Code cannot be blank.");
        if (code.length() != Constants.MAX_CODE)
            throw new IllegalArgumentException("Vaccine Type Code must have 5 numbers.");
    }

    /**
     * @return A string with the format Vaccine Type: Code= code, Type= type, Tech = tech
     */
    @Override
    public String toString() {
        return "Vaccine Type: \n" +
                "Code -> " + this.code +
                "\nTech -> " + this.tech +
                "\nType -> " + this.type;
    }
}
