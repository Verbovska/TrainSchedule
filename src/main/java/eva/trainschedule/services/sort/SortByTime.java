package eva.trainschedule.services.sort;

import eva.trainschedule.models.Station;
import eva.trainschedule.models.Train;

import java.util.ArrayList;
import java.util.Comparator;

public class SortByTime implements Sorter {
    @Override
    public ArrayList<Train> sort(ArrayList<Train> trains, Station station) {
        trains.sort(Comparator.comparing(o -> o.getDepTime(station)));
        return trains;
    }
}
