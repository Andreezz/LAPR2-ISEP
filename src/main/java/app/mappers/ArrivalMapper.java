package app.mappers;

import app.domain.model.Arrival;
import app.domain.model.VaccinationCenter;
import app.mappers.dto.ArrivalDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ArrivalMapper {

    public ArrivalMapper() {
    }

    public ArrivalDTO toDTO(Arrival arrival) {
        return new ArrivalDTO(arrival.getDate(), arrival.getTime(), arrival.getUser(), arrival.getVaccCenter());
    }

    public List<ArrivalDTO> toDTO(Set<Arrival> arrivals) {
        List<ArrivalDTO> arrivalsDTO = new ArrayList<>();
        Iterator<Arrival> var3 = arrivals.iterator();

        while(var3.hasNext()) {
            Arrival arrival = var3.next();
            arrivalsDTO.add(this.toDTO(arrival));
        }

        return arrivalsDTO;
    }
}
