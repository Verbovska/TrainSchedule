package eva.trainschedule.services.sort;

import eva.trainschedule.models.Station;
import eva.trainschedule.models.Train;

import java.util.ArrayList;

public interface Sorter {
    ArrayList<Train> sort(ArrayList<Train> trains, Station station);
}
