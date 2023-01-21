package eva.trainschedule.services;

import eva.trainschedule.models.Station;

import java.util.Map;

public interface StationService {
    void save(Station station);
    Station findByName(String name);
    Station findById(int id);
    void delete(int id);
    void editName(int id, String newName);

    Map<Integer, Station> findAll();    
}
