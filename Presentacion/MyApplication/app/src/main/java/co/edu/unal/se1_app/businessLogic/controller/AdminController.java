package co.edu.unal.se1_app.businessLogic.controller;

import co.edu.unal.se1_app.dataAccess.model.Admin;
import co.edu.unal.se1_app.dataAccess.repository.AdminRepository;

public class AdminController {

    AdminRepository adminRepository;

    public AdminController() {

    }

    public Admin createAdmin(Admin admin){
        adminRepository = new AdminRepository();
        return adminRepository.createAdmin( admin );
    }

    public boolean verifyAdmin( Long id , String password ){
        adminRepository = new AdminRepository();
        Admin admin = adminRepository.getAdminById( id );
        if( admin == null ) return false;
        return password.equals( admin.getPassword() );
    }
}
