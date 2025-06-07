package ntu.edu.quangnm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ntu.edu.quangnm.entity.Admin;
import ntu.edu.quangnm.repositories.AdminRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(Integer id) {
        return adminRepository.findById(id);
    }

    public Admin getAdminByAccountId(Integer id) {
    	return adminRepository.findaAdminByAccountId(id);
    }
    
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public void deleteAdmin(Integer id) {
        adminRepository.deleteById(id);
    }
}
