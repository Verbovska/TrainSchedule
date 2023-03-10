package eva.trainschedule.repositories.implementation;

import eva.trainschedule.models.Station;
import eva.trainschedule.repositories.StationRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class StationRepositoryImpl implements StationRepository {

    private static final Map<Integer, Station> repository = new HashMap<>();

    public StationRepositoryImpl() {

    }

    @Override
    public void save(Station station) {
        repository.put(station.getId(), station);
    }

    @Override
    public Station findByName(String name) {
        Collection<Station> stations = repository.values();
        for (Station station : stations) {
            if (station.getName().equalsIgnoreCase(name))
                return station;
        }
        return null;
    }

    @Override
    public Station findById(int id) {
        return repository.get(id);
    }

    @Override
    public void delete(int id) {
        repository.remove(id);
    }

    @Override
    public Map<Integer, Station> findAll() {
        return repository;
    }
}
