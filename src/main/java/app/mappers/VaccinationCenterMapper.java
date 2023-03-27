package app.mappers;

import app.domain.model.VaccinationCenter;
import app.mappers.dto.VaccinationCenterDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class VaccinationCenterMapper {

    public VaccinationCenterMapper() {
    }

    public VaccinationCenterDTO toDTO(VaccinationCenter vaccCenter) {
        return new VaccinationCenterDTO(vaccCenter.getName(), vaccCenter.getEmail(), vaccCenter.getPhoneNumber(),
                vaccCenter.getAddress(), vaccCenter.getWebAddress(), vaccCenter.getFaxNumber(),
                vaccCenter.getVctype(), vaccCenter.getSlotDuration(), vaccCenter.getMaxVaccines(), vaccCenter.getSchedule());
    }

    public List<VaccinationCenterDTO> toDTO(List<VaccinationCenter> vaccCenters) {
        List<VaccinationCenterDTO> vaccCentersDTO = new ArrayList<>();
        Iterator<VaccinationCenter> var3 = vaccCenters.iterator();

        while(var3.hasNext()) {
                VaccinationCenter vaccCenter = var3.next();
            vaccCentersDTO.add(this.toDTO(vaccCenter));
        }

        return vaccCentersDTO;
    }

}