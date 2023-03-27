package app.mappers;



import app.domain.model.CenterData;
import app.mappers.dto.CenterDataDTO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CenterDataMapper {

    public CenterDataDTO toDTO(CenterData cd) {
        return new CenterDataDTO(cd.getArr(), cd.getSch(), cd.getAdmin(), cd.getLeave(), cd.getSNSnum(), cd.getLot(), cd.getVacName(), cd.getCurrentDose());
    }

    public List<CenterDataDTO> toDTO(List<CenterData> cdList) {
        List<CenterDataDTO> cdListDTO = new ArrayList();
        Iterator var3 = cdList.iterator();

        while(var3.hasNext()) {
            CenterData cd = (CenterData) var3.next();
            cdListDTO.add(this.toDTO(cd));
        }

        return cdListDTO;
    }
}
