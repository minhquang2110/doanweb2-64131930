package ntu.edu.quangnm.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import ntu.edu.quangnm.dto.ScientistDTO;
import ntu.edu.quangnm.entity.EducationHistory;
import ntu.edu.quangnm.entity.Scientist;
import ntu.edu.quangnm.entity.User;
import ntu.edu.quangnm.mapper.ScientMapper;
import ntu.edu.quangnm.services.ScientistService;
@Controller
public class AdminController {
	
	@Autowired
    private ScientistService scientistService;
	
	@GetMapping("/admin/profile")
    public String profileAdmin(Model model,Authentication authentication) {
    	User account =  (User) authentication.getPrincipal();
    	model.addAttribute("admin",account.getAdmin());
    	return "/admin/details_admin";
    }
	
	@GetMapping("/admin/createScientist")
	public String showCreateForm(Model model) {
	    model.addAttribute("scientist", new ScientistDTO());
	    return "admin/createScientist";
	}

    // Xử lý lưu nhà khoa học mới
    @PostMapping("/admin/createScientist/save")
    public String saveScientist(@Valid ScientistDTO scientistDTO, BindingResult bindingResult, Model model) throws IOException {

        if (bindingResult.hasErrors()) {
            // Nếu có lỗi validate, trả lại form để sửa
            model.addAttribute("scientist", scientistDTO);
            return "admin/createScientist";
        }

        scientistService.saveScientist(scientistDTO);

        // Lưu thành công, redirect về danh sách nhà khoa học
        return "redirect:/admin/scientistList";
    }

    @GetMapping("/admin/scientistList")
    public String listScientists(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Scientist> scientists;

        // Nếu có từ khóa tìm kiếm
        if (keyword != null && !keyword.trim().isEmpty()) {
            // Gọi service để tìm kiếm
            scientists = scientistService.searchScientists(keyword);
            // Trả lại keyword cho view để hiển thị lại trên ô tìm kiếm
            model.addAttribute("keyword", keyword);
        } else {
            // Nếu không có từ khóa, lấy tất cả
            scientists = scientistService.getAllScientists();
        }

        model.addAttribute("scientists", scientists);
        return "admin/scientistList";
    }
}
