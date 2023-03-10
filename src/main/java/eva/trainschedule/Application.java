package eva.trainschedule;

import eva.trainschedule.models.DataTime;
import eva.trainschedule.models.Station;
import eva.trainschedule.models.Train;
import eva.trainschedule.repositories.StationRepository;
import eva.trainschedule.repositories.TrainRepository;
import eva.trainschedule.repositories.implementation.StationRepositoryImpl;
import eva.trainschedule.repositories.implementation.TrainRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;



@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);

        StationRepository stationRepository = new StationRepositoryImpl();
        Station kiev = new Station(0,"Київ");
        Station vin = new Station(1,"Вінниця");
        Station odesa = new Station(2,"Одеса");
        Station kherson = new Station(3,"Херсон");
        stationRepository.save(kiev);
        stationRepository.save(vin);
        stationRepository.save(odesa);
        stationRepository.save(kherson);


        TrainRepository trainRepository = new TrainRepositoryImpl();

        HashMap<Station, ArrayList<DataTime>> train1 = new HashMap<>();
        train1.put(kiev, new ArrayList<>(Arrays.asList(new DataTime(15,10,19,0),new DataTime(15,10,19,0))));
        train1.put(vin, new ArrayList<>(Arrays.asList(new DataTime(15,10,19,30),new DataTime(15,10,20,40))));
        train1.put(odesa, new ArrayList<>(Arrays.asList(new DataTime(15,10,21,0),new DataTime(15,10,21,0))));
        Train train11 = new Train(0,"Київ → Одеса",train1);
        trainRepository.save(train11);

        HashMap<Station, ArrayList<DataTime>> train2 = new HashMap<>();
        train2.put(vin, new ArrayList<>(Arrays.asList(new DataTime(15,10,19,30),new DataTime(15,10,20,0))));
        train2.put(odesa, new ArrayList<>(Arrays.asList(new DataTime(15,10,21,0),new DataTime(15,10,21,0))));
        Train train22 = new Train(1,"Вінниця → Одеса",train2);
        trainRepository.save(train22);

        HashMap<Station, ArrayList<DataTime>> train3 = new HashMap<>();
        train3.put(kherson, new ArrayList<>(Arrays.asList(new DataTime(15,10,19,30),new DataTime(15,10,20,0))));
        train3.put(odesa, new ArrayList<>(Arrays.asList(new DataTime(15,10,14,0),new DataTime(15,10,14,0))));
        train3.put(vin, new ArrayList<>(Arrays.asList(new DataTime(15,10,19,30),new DataTime(15,10,20,0))));
        Train train33 = new Train(2,"Херсон → Вінниця",train3);
        trainRepository.save(train33);
    }
}
