package ntu.edu.quangnm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ntu.edu.quangnm.entity.Scientist;
import ntu.edu.quangnm.services.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class StatisticController {

    @Autowired private ScientistService scientistService;
    @Autowired private OrganizationService organizationService;
    @Autowired private ResearchFieldService researchFieldService;

    @GetMapping("/statistics")
    public String showStatistics(Model model) {
        List<Scientist> scientists = scientistService.findAll();

        // Tổng quan
        model.addAttribute("totalScientists", scientists.size());
        model.addAttribute("totalOrganizations", organizationService.getAllOrganizations().size());
        model.addAttribute("totalResearchFields", researchFieldService.getAllResearchFields().size());

        // Học thuật

        // Giới tính (gender chart)
        Map<String, Long> genderDistribution = scientists.stream()
                .collect(Collectors.groupingBy(
                        s -> s.getGender() != null ? s.getGender() : "Khác",
                        Collectors.counting()
                ));
        model.addAttribute("genderDistribution", genderDistribution);

        // Ngạch khoa học (rank chart)
        Map<Object, Long> rankDistribution = scientists.stream()
                .collect(Collectors.groupingBy(
                        s -> s.getRank() != null ? s.getRank() : "Không rõ",
                        Collectors.counting()
                ));
        model.addAttribute("rankDistribution", rankDistribution);

        return "statistic";
    }
}
