package eva.trainschedule.rest;

import eva.trainschedule.models.DataTime;
import eva.trainschedule.models.Train;
import eva.trainschedule.repositories.TrainRepository;
import eva.trainschedule.services.StationService;
import eva.trainschedule.services.TrainFinderService;
import eva.trainschedule.services.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trains")
public class MainRestController {

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    TrainFinderService trainFinderService;

    @Autowired
    StationService stationService;

    @GetMapping
    public ResponseEntity<?> getTrains(@RequestParam String stationOfDeparture, @RequestParam String stationOfArrival, @RequestParam String dateTime){
        if (stationOfDeparture == null || stationOfArrival == null || dateTime == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(trainFinderService.getSuitableTrain(stationService.findByName(stationOfDeparture), stationService.findByName(stationOfArrival), DataTime.stringToDataTime(dateTime)));
    }
}