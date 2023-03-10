package eva.trainschedule.repositories.implementation;

import eva.trainschedule.models.Train;
import eva.trainschedule.repositories.TrainRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TrainRepositoryImpl implements TrainRepository {
    private static final Map<Integer, Train> repository = new HashMap<>();

    public TrainRepositoryImpl() {
    }

    @Override
    public void save(Train train) {
        repository.put(train.getId(), train);
    }

    @Override
    public void delete(int id) {
        repository.remove(id);
    }

    @Override
    public Train findById(int id) {
        return repository.get(id);
    }

    @Override
    public Train findByName(String name) {
        Collection<Train> trains = repository.values();
            for (Train train : trains) {
                if (train.getName().equalsIgnoreCase(name))
                    return train;
                }
        return null;
    }

    @Override
    public Map<Integer, Train> findAll() {
        return repository;
    }
}
