package com.pyrolocate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WildfireController {

    @Autowired
    private WildfireRepository wildfireRepository;

    // Show the main map page
    @GetMapping("/")
    public String index(Model model) {
        List<Wildfire> wildfires = wildfireRepository.findAll();
        model.addAttribute("wildfires", wildfires);
        model.addAttribute("newWildfire", new Wildfire());
        return "index";
    }

    // Handle form submission
    @PostMapping("/add")
    public String addWildfire(@ModelAttribute Wildfire wildfire) {
        wildfireRepository.save(wildfire);
        return "redirect:/";
    }

    // JSON API used by the map
    @GetMapping("/api/wildfires")
    @ResponseBody
    public List<Wildfire> getAllWildfires() {
        return wildfireRepository.findAll();
    }

    // Delete a wildfire by ID
    @PostMapping("/delete/{id}")
    public String deleteWildfire(@PathVariable Long id) {
        wildfireRepository.deleteById(id);
        return "redirect:/";
    }
}
