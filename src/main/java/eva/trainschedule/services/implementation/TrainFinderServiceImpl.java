package eva.trainschedule.services.implementation;

import eva.trainschedule.models.DataTime;
import eva.trainschedule.models.Station;
import eva.trainschedule.models.Train;
import eva.trainschedule.services.TrainFinderService;
import eva.trainschedule.services.sort.Sorter;
import eva.trainschedule.services.sort.SortByTime;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TrainFinderServiceImpl implements TrainFinderService {

    @Override
    public ArrayList<Train> getSuitableTrain(Station stationOfDeparture, Station stationOfArrival, DataTime dataTime) {
        ArrayList<Train> resultTrains = new ArrayList<>();
        try {
            for (Train train : stationOfDeparture.getTrains()) {
                if (train.getArrivalStations().containsKey(stationOfArrival) && dataTime.getIntegerOfTime() <= train.getDepTime(stationOfDeparture) && train.getArrTime(stationOfArrival) > train.getDepTime(stationOfDeparture)) {
                    resultTrains.add(train);
                }
            }
            Sorter sorter = new SortByTime();

            return sorter.sort(resultTrains, stationOfDeparture);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
