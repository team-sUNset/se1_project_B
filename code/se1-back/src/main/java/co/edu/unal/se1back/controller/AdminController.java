package co.edu.unal.se1back.controller;

import co.edu.unal.se1back.exception.ResourceNotFoundException;
import co.edu.unal.se1back.model.Admin;
import co.edu.unal.se1back.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by riperezro on 24/11/19.
 */
@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    AdminRepository adminRepository;

    @GetMapping("/admins")
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @PostMapping("/admins")
    public Admin createAdmin(@Valid @RequestBody Admin admin) {
        return adminRepository.save(admin);
    }

    @GetMapping("/admins/{id}")
    public Admin getAdminById(@PathVariable(value = "id") Long adminId) {
        return adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "id", adminId));
    }

    @PutMapping("/admins/{id}")
    public Admin updateAdmin(@PathVariable(value = "id") Long adminId,
                           @Valid @RequestBody Admin adminDetails) {

        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "id", adminId));

        admin.setPassword(adminDetails.getPassword());
        admin.setFirstName(adminDetails.getEmail());
        admin.setLastName(adminDetails.getLastName());
        admin.setEmail(adminDetails.getEmail());
        Admin updatedAdmin = adminRepository.save(admin);
        return updatedAdmin;
    }

    @DeleteMapping("/admins/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable(value = "id") Long adminId) {

        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "id", adminId));

        adminRepository.delete(admin);
        return ResponseEntity.ok().build();
    }
}