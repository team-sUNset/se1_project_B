package co.edu.unal.se1_app.businessLogic.controller;

import androidx.annotation.NonNull;

import javax.annotation.Nullable;

import co.edu.unal.se1_app.dataAccess.callback.AdminCallback;
import co.edu.unal.se1_app.dataAccess.model.Admin;
import co.edu.unal.se1_app.dataAccess.repository.AdminRepository;

public class AdminController {

    AdminRepository adminRepository;

    public AdminController() {

    }

    public void createAdmin( Admin admin , @Nullable AdminCallback callbacks ){
        adminRepository = new AdminRepository();
        adminRepository.createAdmin(admin, new AdminCallback() {
            @Override
            public void onSuccess(@NonNull Admin admin) {
                callbacks.onSuccess( admin );
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                callbacks.onError( throwable );
            }
        });
    }

    public void verifyAdmin( Long id , String password , @Nullable AdminCallback callbacks ){
        adminRepository = new AdminRepository();
        adminRepository.getAdminById(id, new AdminCallback() {
            @Override
            public void onSuccess(@NonNull Admin admin) {
                if( password.equals( admin.getPassword() ) ) callbacks.onSuccess( admin );
                else callbacks.onSuccess( null );
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                callbacks.onError( throwable );
            }
        });
    }

}
