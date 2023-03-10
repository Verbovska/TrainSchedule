package eva.trainschedule.controllers;

import eva.trainschedule.models.DataTime;
import eva.trainschedule.models.Station;
import eva.trainschedule.models.Train;
import eva.trainschedule.services.StationService;
import eva.trainschedule.services.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;


@Controller
public class AdminTrainController {

    @Autowired
    TrainService trainService;

    @Autowired
    StationService stationService;

    @GetMapping("/admin/train")
    public String adminTrain(Model model) {
        ArrayList<String> temp = new ArrayList<>();
        for (Station station: stationService.findAll().values()) {
            temp.add(station.getName());
        }

        model.addAttribute("trainsName", trainService.findAll().values().toString().replaceAll("[\\[\\]]",""));
        model.addAttribute("stations", temp);
        model.addAttribute("trains",trainService.findAll().values());
        return "adminTrain";
    }

    @GetMapping(value = "/admin/train", params = "delete")
    public String delete(String trainId) {
        if (!trainId.equals("")) {
            try {
                trainService.delete(Integer.parseInt(trainId));
            }catch(Exception ignored){}
        }
        return "redirect:/admin/train";
    }

    @GetMapping(value = "/admin/train", params = "add")
    public String add(String trainId, String trainName) {
        if (!trainId.equals("") && !trainName.equals("") && !trainService.findAll().containsKey(Integer.parseInt(trainId))) {
            HashMap<Station, ArrayList<DataTime>> stations = new HashMap<>();
            trainService.save(new Train(Integer.parseInt(trainId), trainName, stations));
        }
        return "redirect:/admin/train";
    }

    @GetMapping(value = "/admin/train", params = "edit")
    public String edit(String trainId, String trainName) {
        if (!trainId.equals("")) {
            if (!trainName.equals("")) {
                try {
                    trainService.findById(Integer.parseInt(trainId)).setName(trainName);
                }catch(Exception ignored){}
            }
            return "redirect:/admin/train/" + trainId;
        }
        return "redirect:/admin/train";
    }

    @GetMapping("/admin/train/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        Train train = trainService.findById(Integer.parseInt(id));

        if (train != null) {
            model.addAttribute("trainStations", train.getSortedStations());
            model.addAttribute("stations", stationService.findAll().values());
        }
        model.addAttribute("id", id);
        model.addAttribute("trains", trainService);
        return "editTrain";
    }

    @GetMapping(value = "/admin/train/{id}", params = "addStation")
    public String addStation(@PathVariable String id, String timeArr, String timeDep, String stations) {
        Train train = trainService.findById(Integer.parseInt(id));
        train.addStation(stationService.findByName(stations), DataTime.stringToDataTime(timeArr), DataTime.stringToDataTime(timeDep));

        return "redirect:/admin/train/"+id;
    }

    @GetMapping(value = "/admin/train/{id}", params = "deleteStation")
    public String deleteStation(@PathVariable String id, String stations) {

        Train train = trainService.findById(Integer.parseInt(id));
        if (train != null) {
            train.removeStation(stationService.findByName(stations));
        }
        return "redirect:/admin/train/"+id;
    }
}
