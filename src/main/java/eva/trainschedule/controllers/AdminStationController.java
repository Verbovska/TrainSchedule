package eva.trainschedule.controllers;

import eva.trainschedule.models.Station;
import eva.trainschedule.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
public class AdminStationController {

    @Autowired
    StationService stationService;

    @GetMapping("/admin/station")
    public String adminStation(Model model){
        model.addAttribute("station",stationService.findAll().values().stream()
                .map(Station::toString)
                .collect(Collectors.joining(", ")));
        return "adminStation";
    }

    @GetMapping(value = "/admin/station", params = "add")
    public String add(String stationId, String stationName) {
        if (!stationName.equals("") && !stationId.equals("") && !stationService.findAll().containsKey(Integer.parseInt(stationId))) {
            stationService.save(new Station(Integer.parseInt(stationId), stationName));
        }
        return "redirect:/admin/station";
    }

    @GetMapping(value = "/admin/station", params = "delete")
    public String delete(String stationId) {
        if (!stationId.equals("")) {
            try {
                stationService.delete(Integer.parseInt(stationId));
            }catch(Exception ignored){}
        }
        return "redirect:/admin/station";
    }

    @GetMapping(value = "/admin/station", params = "edit")
    public String edit(String stationId, String stationName) {
        if (!stationName.equals("") && !stationId.equals("")) {
            try {
                stationService.editName(Integer.parseInt(stationId), stationName);
            }catch(Exception ignored){}
        }
        return "redirect:/admin/station";
    }
}
