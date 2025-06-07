	package ntu.edu.quangnm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import ntu.edu.quangnm.entity.Account;
import ntu.edu.quangnm.services.AccountService;

@Controller
public class UserController{
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/loginTemplate")
	public String lo() {
		return "/users/login";
	}
	
	@GetMapping("/user/loginTemplateFailed")
	public String loginFailed(Model model) {
		model.addAttribute("failed","Tên đăng nhập hoặc mật khẩu sai");
		return "/users/login";
	}
	
	

	@GetMapping("/user/logout")
	public String logout(HttpSession session) {
		if(session!=null) {
			session.removeAttribute("user");
		}
		return "/users/login";
	}

	private boolean isValidPassword(String password) {
		return password != null && password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
	}
	
	@GetMapping("/user/change-password")
	public String showChangePasswordForm() {
		return "/users/change-password"; 
	}

	@PostMapping("/user/change-password")
	public String changePassword(
			@RequestParam("username") String username,
			@RequestParam("currentPassword") String currentPassword,
			@RequestParam("newPassword") String newPassword,
			Model model
	) {
		Account account = accountService.findByUsername(username);

		if (account == null) {
			model.addAttribute("error", "❌ Tên đăng nhập không tồn tại.");
			return "/users/change-password";
		}

		if (!account.getPassword().equals(currentPassword)) {
			model.addAttribute("error", "❌ Mật khẩu hiện tại không chính xác.");
			return "/users/change-password";
		}

		if (!isValidPassword(newPassword)) {
			model.addAttribute("error", "❌ Mật khẩu mới phải có ít nhất 8 ký tự, gồm chữ hoa, chữ thường và số.");
			return "/users/change-password";
		}

		account.setPassword(newPassword);
		accountService.saveAccount(account);

		model.addAttribute("message", "✅ Đổi mật khẩu thành công.");
		return "/users/change-password";
	}
}