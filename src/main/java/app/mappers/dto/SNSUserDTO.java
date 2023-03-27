package app.mappers.dto;

public class SNSUserDTO {

    public String getSNSnumber() {
        return SNSnumber;
    }

    private String SNSnumber;

    public SNSUserDTO(String SNSnumber){
        this.SNSnumber = SNSnumber;
    }
}
