package eva.trainschedule.controllers;


import eva.trainschedule.models.DataTime;
import eva.trainschedule.models.Train;
import eva.trainschedule.services.TrainService;
import eva.trainschedule.services.StationService;
import eva.trainschedule.services.TrainFinderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    TrainFinderService trainFinderService;

    @Autowired
    StationService stationService;

    @Autowired
    TrainService trainService;

    @GetMapping("/")
    public String mainHtml(Model model){
        model.addAttribute("textLabel", "Всі потяги:");
        model.addAttribute("trains", trainService.findAll().values());
        return "index";
    }


    @GetMapping("/trains")
    public String trains (
           String stationOfDeparture,
           String stationOfArrival,
           String dateTime,
            Model model){
        List<Train> trains = trainFinderService.getSuitableTrain(stationService.findByName(stationOfDeparture), stationService.findByName(stationOfArrival), DataTime.stringToDataTime(dateTime));
        model.addAttribute("textLabel", "Потяги для маршруту: " + stationOfDeparture + " → " + stationOfArrival);
        model.addAttribute("trains", trains);
        return "index";
    }

    @GetMapping("/trains/{id}")
    public String train(@PathVariable String id, Model model) {
        Train train = trainService.findById(Integer.parseInt(id));
        model.addAttribute("trainStations", train.getSortedStations());
        return "train";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }
}
