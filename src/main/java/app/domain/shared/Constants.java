package app.domain.shared;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Constants {
    public static final String ROLE_ADMIN = "ADMINISTRATOR";
    public static final String ROLE_NURSE = "NURSE";
    public static final String ROLE_COORDINATOR = "COORDINATOR";
    public static final String ROLE_RECEPTIONIST = "RECEPTIONIST";
    public  static final String ROLE_SNSUSER = "SNS USER";

    public static final String TECH_1 = "Live-attenuated vaccines";
    public static final String TECH_1_TYPE = "Live-attenuated vaccines contain live pathogens from either\n" +
            "a bacteria or a virus that have been \"attenuated,\" or weakened. According to Dr. Scully, live-attenuated vaccines\n" +
            " are produced by selecting strains of a bacteria or virus that\n " +
            "still produce a robust enough immune response but that does not cause disease. \n";
    public static final String TECH_2 = "Inactivated vaccines";
    public static final String TECH_2_TYPE = "Inactivated vaccines take a live pathogen and inactivate or \n" +
            "kill it. When the vaccine is then introduced to a human through a shot, for example, the inactivated \n" +
            "pathogen is strong enough to create an \n" +
            "immune response, however, is incapable of causing disease. Multiple doses are often needed in order to\n" +
            " build up immunity and offer full protection.";
    public static final String TECH_3 = "Subunit vaccines";
    public static final String TECH_3_TYPE = "Subunit vaccines are made from a piece of a pathogen,\n" +
            " not the whole organism, so they do not contain any live pathogens. Some important subunit \n" +
            "vaccines are polysaccharide vaccines, conjugate vaccines, and protein-based vaccines.";
    public static final String TECH_4 = "Toxoid vaccines";
    public static final String TECH_4_TYPE = "Toxoid vaccines use inactivated toxins to target the toxic activity created by the bacteria, \n" +
            "rather than targeting the bacteria itself.";
    public static final String TECH_5 = "Viral vector vaccines";
    public static final String TECH_5_TYPE = "Viral vector vaccines use a harmless virus to deliver to the hosts\n" +
            " cells the genetic code of the antigen you want the immune system to fight";
    public static final String TECH_6 = "Messenger RNA (mRNA) vaccines";
    public static final String TECH_6_TYPE = "When an mRNA vaccine is delivered, the RNA material\n" +
            " teaches our body how to make a specific type of protein that is unique to the virus, but\n" +
            " does not make the person sick. The protein triggers an immune response, which includes the generation\n " +
            "of antibodies that recognize the protein. That way, if a person is ever exposed to that virus in the future,\n " +
            "the body would like have the tools (antibodies) to fight against it.4";

    public static final String VC_TYPE_1 = "Health Care Center";
    public static final String VC_TYPE_2 = "Mass Vaccination Center";

    public static final int MAX_CODE = 5;
    public static final int PHONE_NUMBER_DIGITS = 9;

    public static final int MAX_VACCINATION_CENTER_NAME = 20;
    public static final int MAX_VACCINATION_CENTER_ADDRESS = 30;

    public static final String SNS_USER_ROLE = "SNSUSER";
    public static final String SNS_USER_GENDER_MALE = "MALE";
    public static final String SNS_USER_GENDER_OTHER = "N/A";
    public static final String SNS_USER_GENDER_FEMALE = "FEMALE";

    public static final String PARAMS_FILENAME = "config.properties";
    public static final String PARAMS_COMPANY_DESIGNATION = "Company.Designation";

    public static final int SNS_USER_ATTRIBUTES = 8;

    public static final int CENTER_DATA_ATTRIBUTES = 8;

    public static final int SNS_NUMBER_DIGITS = 9;

    public static final String PARAMS_CHECK_TIME = "hour";

    public static final String SNS_USER_FILE = "SNSuserData2";

    public static final String ARRIVAL_FILE = "arrivalData1";

    public static final String EMPLOYEE_FILE = "employeeData1";

    public static final String VACCINATION_CENTER_FILE = "centerData1";

    public static final String VACCINE_TYPE_FILE = "typeData1";

    public static final String VACCINE_FILE = "vaccineData1";

    public static final String SCHEDULE_VACCINE_FILE = "scheduleVaccineData1";

    public static final String SCHEDULE_SNS_FILE = "scheduleSNSData1";

    public static final String CENTER_DATA_FILE = "CenterPerformanceData1";

    public static final String DAILY_RECORDS_FILE = "dailyRecordsData1";

    public static final String ADVERSE_REACTIONS_FILE = "adverseReactionsData1";
}
