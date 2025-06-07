package ntu.edu.quangnm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import ntu.edu.quangnm.entity.Account;
import ntu.edu.quangnm.entity.Admin;
import ntu.edu.quangnm.entity.Scientist;
import ntu.edu.quangnm.entity.User;
import ntu.edu.quangnm.repositories.AccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private  AccountRepository accountRepository;
    
    @Autowired
	private ScientistService scientistService;
    
    @Autowired
   	private AdminService adminService;

    @Autowired
	private PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = accountRepository.findByUsername(username);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getRole().toString()=="Admin") {
        	Admin scientist= adminService.getAdminByAccountId(user.getAccountId());
        	return new 	User(user,scientist);
        }else {
        	Scientist scientist= scientistService.getScientistByAccountId(user.getAccountId());
        	return new 	User(user,scientist);
        }
    }
    
    public String loginFailed(Model model) {
    	model.addAttribute("failed","Tên đăng nhập hay mật khẩu sai");
    	return "loginTemplate";
    }
}
