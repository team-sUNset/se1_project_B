package co.edu.unal.se1_app.dataAccess.callback;

import androidx.annotation.NonNull;

import java.util.List;

import co.edu.unal.se1_app.dataAccess.model.Admin;

public interface AdminListCallback {
    void onSuccess(@NonNull List<Admin> admins);

    void onError(@NonNull Throwable throwable);
}