package app.domain.model;

import java.io.Serializable;

public class Schedule implements Serializable{

    private static final long serialVersionUID = 6L;
    private String openHours;
    private String closeHours;

    public Schedule(String openHours, String closeHours) {
        this.openHours = openHours;
        this.closeHours = closeHours;
    }

    public String getOpenHours() {
        return openHours;
    }

    public String getCloseHours() {
        return closeHours;
    }
}
