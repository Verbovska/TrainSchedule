package eva.trainschedule.services;

import eva.trainschedule.models.DataTime;
import eva.trainschedule.models.Station;
import eva.trainschedule.models.Train;

import java.util.List;

public interface TrainFinderService {
    List<Train> getSuitableTrain(Station stationOfDeparture, Station stationOfArrival, DataTime dataTime);
}
