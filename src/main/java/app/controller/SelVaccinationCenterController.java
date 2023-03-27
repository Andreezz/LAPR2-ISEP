package app.controller;

import app.domain.model.VaccinationCenter;
import app.domain.stores.VaccinationCenterStore;
import app.mappers.VaccinationCenterMapper;
import app.mappers.dto.VaccinationCenterDTO;

import java.util.ArrayList;
import java.util.List;

public class SelVaccinationCenterController {

    private App app;
    private VaccinationCenterMapper vcMapper = new VaccinationCenterMapper();

    public SelVaccinationCenterController() {
        this.app = App.getInstance();
    }

    public List<VaccinationCenterDTO> getVaccinationCenters() {
        VaccinationCenterStore storeVC = app.getCompany().getVaccinationCenterList();
        List<VaccinationCenter> vaccCenters =  storeVC.getALL();
        return vcMapper.toDTO(vaccCenters);
    }

    public boolean exists( String vaccCenterName ) {
        VaccinationCenterStore storeVC = app.getCompany().getVaccinationCenterList();
        return storeVC.exists(vaccCenterName);
    }
}
