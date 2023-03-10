package eva.trainschedule.repositories;

import eva.trainschedule.models.Station;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface StationRepository {
    void save(Station station);
    Station findByName(String name);
    Station findById(int id);
    void delete(int id);
    Map<Integer, Station> findAll();
}
