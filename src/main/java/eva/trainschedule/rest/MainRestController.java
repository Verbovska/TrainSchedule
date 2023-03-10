package eva.trainschedule.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import eva.trainschedule.models.DataTime;
import eva.trainschedule.repositories.TrainRepository;
import eva.trainschedule.services.StationService;
import eva.trainschedule.services.TrainFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Trains",
        description = "Demonstration of main logic of application as REST ful web service"
)
@RestController
@RequestMapping("/trains_rest")
public class MainRestController {

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    TrainFinderService trainFinderService;

    @Autowired
    StationService stationService;
    @Operation(summary = "Get suitable train", description = "Getting suitable train by station of departure, station of arrival and date of departure used ad request params")
    @ApiResponse(responseCode = "200", description = "Everything is OK")
    @ApiResponse(responseCode = "404", description = "Error bad request", content = @Content)
    @GetMapping
    public ResponseEntity<?> getTrains(@RequestParam String stationOfDeparture, @RequestParam String stationOfArrival, @RequestParam String dateTime){
        if (stationOfDeparture == null || stationOfArrival == null || dateTime == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(trainFinderService.getSuitableTrain(stationService.findByName(stationOfDeparture), stationService.findByName(stationOfArrival), DataTime.stringToDataTime(dateTime)));
    }
}
