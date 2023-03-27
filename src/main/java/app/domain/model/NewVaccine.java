package app.domain.model;

import org.apache.commons.lang3.ObjectUtils;

import java.io.Serializable;
import java.util.List;

public class NewVaccine implements Serializable {

    /**
     * Related variables (attributes) of a vaccine
     */
    private static final long serialVersionUID = 4L;
    private  String name;
    private String manufacturercompany;
    private VaccineType vacctype;
    private Administrationprocess adminprocess;
    private int id;
    private Dose dose;



    /**
     * @param name: Represents the vaccine´s name
     * @param manufacturercompany: Represents the name of the company that is  developing and manufacturing the vaccine
     *
     *
     */
    public NewVaccine(int id, String name, String manufacturercompany, VaccineType vacctype, List<Integer> ageGroup, List<Integer> timeInterval, List<Float> dosage)
    {
        if(ObjectUtils.allNotNull(id,name, manufacturercompany, vacctype)) {

            this.adminprocess = new Administrationprocess();
            this.name = name;
            this.manufacturercompany = manufacturercompany;
            this.adminprocess.CreateAdministrationProcess(ageGroup.get(0), ageGroup.get(1), dosage, timeInterval);
            this.vacctype= vacctype;
            this.id= id;
        }
        else {
            throw new IllegalArgumentException("Vaccine registration and its properties not well inserted");
        }

    }
    public  Administrationprocess getAdministrationprocess(){

        return adminprocess;
    }



    @Override
    public String toString() {
        return "NewVaccine{" + "\n"+
                "Insert: ID= "+id+'\''+
                ", Name= " + name + '\''+
                ", Manufacturer Company = " + manufacturercompany + '\'' +
                ", Vaccine Type = " + vacctype.getType()+ "\n" + adminprocess.toString();
    }

    public String ListByTypeAndAlfabetical(){
        return "\tName= " + name + "\n";
               // "\tVaccine Type = " + vacctype.getType();
    }

    public void setId(int id)throws IllegalArgumentException {
        if (id<=0 ) {
            throw new IllegalArgumentException("The lot can't be negative number!");
        }
        this.id = id;
    }


    public void setName(String name) throws  IllegalArgumentException{
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("The name can't be null or empty!");
        }
        this.name = name;
    }


    public void setvType(VaccineType teck) {
        this.vacctype = teck;
    }


    public void setProcess(Administrationprocess adminprocess) {
        this.adminprocess = adminprocess;
    }


    public void setManufacturercompany(String manufacturercompany) throws IllegalArgumentException {
        if (manufacturercompany== null || manufacturercompany.trim().isEmpty()) {
            throw new IllegalArgumentException("The brand can't be null or empty!");
        }
        this.manufacturercompany = manufacturercompany;
    }


    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getManufacturercompany() {
        return manufacturercompany;
    }

    /**
     * Gets the VaccineType
     * @return
     */
    public VaccineType getVacctype() {
        return vacctype;
    }
}
