package eva.trainschedule.services.implementation;

import eva.trainschedule.models.Station;
import eva.trainschedule.models.Train;
import eva.trainschedule.repositories.StationRepository;
import eva.trainschedule.services.StationService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public void save(Station station) {
        stationRepository.save(station);
    }

    @Override
    public Station findByName(String name) {
       return stationRepository.findByName(name);
    }

    @Override
    public Station findById(int id) {
        return stationRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        for (Train train : stationRepository.findById(id).getTrains()){
            train.getArrivalStations().remove(stationRepository.findById(id));
        }
        stationRepository.delete(id);
    }

    @Override
    public void editName(int id, String newName) {
        for (Train train : stationRepository.findById(id).getTrains()){
            for(Station station : train.getArrivalStations().keySet()){
                if (station.equals(stationRepository.findById(id))){
                    station.setName(newName);
                }
            }
        }
        stationRepository.findById(id).setName(newName);
    }

    @Override
    public Map<Integer, Station> findAll() {
        return stationRepository.findAll();
    }
}
