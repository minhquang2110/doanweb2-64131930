package ntu.edu.quangnm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ntu.edu.quangnm.entity.*;
import ntu.edu.quangnm.repositories.DegreeRepository;
import ntu.edu.quangnm.repositories.ResearchFieldRepository;
import ntu.edu.quangnm.repositories.TitleRepository;
import ntu.edu.quangnm.services.ScientistService;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Controller
public class ScientistController {

    @Autowired private ScientistService scientistService;
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private DegreeRepository degreeRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private ResearchFieldRepository researchFieldRepository;
    

    @GetMapping({"/scientists/filter", "/scientists/list"})
    public String listScientists(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer degreeId,
            @RequestParam(required = false) Integer titleId,
            @RequestParam(required = false) Integer researchFieldId,
            Model model
    ) {
        Set<Integer> researchFieldIds = new HashSet<>();
        if (researchFieldId != null) {
            researchFieldIds.add(researchFieldId);
            List<ResearchField> children = researchFieldRepository.findByParentFieldId(researchFieldId);
            for (ResearchField rf : children) {
                researchFieldIds.add(rf.getId());
            }
        }

        List<Scientist> scientists = scientistService.filterScientists(keyword, degreeId, titleId, researchFieldId);

        model.addAttribute("scientists", scientists);
        model.addAttribute("keyword", keyword);
        model.addAttribute("degreeId", degreeId);
        model.addAttribute("titleId", titleId);
        model.addAttribute("researchFieldId", researchFieldId);

        model.addAttribute("degrees", degreeRepository.findAll());
        model.addAttribute("titles", titleRepository.findAll());
        model.addAttribute("researchfields", researchFieldRepository.findAll());
        model.addAttribute("scientistCount", scientists.size());

        return "scientistsList";
    }

    @GetMapping("/scientists/profile")
    public String profileScientist(Model model, Authentication authentication, HttpServletRequest request) {
        User account = (User) authentication.getPrincipal();
        Scientist s = account.getScientist();
        Integer id = s.getId();

        model.addAttribute("scientist", s);
        model.addAttribute("organization", s.getOrganization());
        model.addAttribute("requestURI", request.getRequestURI());

        return "scientist/details_scientist";
    }

    @GetMapping("/scientists/edit/{id}")
    public String editScientistForm(Model model, Authentication authentication,@PathVariable("id") Integer id) {
        Scientist scientist = scientistService.findById(id);
        model.addAttribute("scientist", scientist);
        return "scientist/edit_scientist";
    }

    @PostMapping("/scientists/edit/{id}")
    public String updateScientist(@PathVariable("id") Integer id,@ModelAttribute Scientist updatedScientist,
    		Authentication authentication, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        Scientist existing = scientistService.findById(id);

        existing.setFullName(updatedScientist.getFullName());
        existing.setGender(updatedScientist.getGender());
        existing.setBirthYear(updatedScientist.getBirthYear());
        existing.setAddress(updatedScientist.getAddress());
        existing.setPhoneNumber(updatedScientist.getPhoneNumber());
        existing.setEmail(updatedScientist.getEmail());
        existing.setEmail(updatedScientist.getEmail());
        if (imageFile != null && !imageFile.isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(imageFile.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            String imageUrl = (String) uploadResult.get("secure_url");
            existing.setImage(imageUrl);
        }
        scientistService.save(existing);
       return "redirect:/scientists/edit/" + id;
    }
//    
    @GetMapping("/scientists/edit/education/{id}")
    public String editEducationForm(@PathVariable("id") Integer id, Model model, Authentication authentication) {
        User account = (User) authentication.getPrincipal();
        
        return "scientist/edit_education";
    }

    @GetMapping("/scientists/edit/education/new")
    public String newEducationForm(Model model) {
        model.addAttribute("educationHistory", new EducationHistory());
        return "scientist/edit_education";
    }

    @PostMapping("/scientists/edit/education")
    public String saveEducation(@ModelAttribute EducationHistory educationHistory, Authentication authentication) {
        User account = (User) authentication.getPrincipal();
        educationHistory.setScientist(account.getScientist());
        return "redirect:/scientists/edit?success=education";
    }
//    
    @GetMapping("/scientists/edit/work/{id}")
    public String editWorkForm(@PathVariable("id") Integer id, Model model, Authentication authentication) {
        User account = (User) authentication.getPrincipal();
        return "scientist/edit_work";
    }

    @GetMapping("scientists/details/{id}")
    public String showScientistDetails(@PathVariable Integer id, Model model, HttpServletRequest request) {
        Optional<Scientist> scientistOpt = scientistService.getScientistById(id);
        if (scientistOpt.isEmpty()) {
            return "redirect:/scientists?error=notfound";
        }
        Scientist s = scientistOpt.get();
        model.addAttribute("scientist", s);
        model.addAttribute("organization", s.getOrganization());
        model.addAttribute("requestURI", request.getRequestURI());
        return "scientist/details_scientist"; 
    }



    @GetMapping("/scientists/scientistList")
    public String showScientists(Model model) {
        model.addAttribute("scientists", scientistService.findAll());
        return "admin/scientistList";
    }
}