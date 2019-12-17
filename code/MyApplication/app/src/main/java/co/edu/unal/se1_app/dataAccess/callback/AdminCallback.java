package co.edu.unal.se1_app.dataAccess.callback;

import androidx.annotation.NonNull;

import co.edu.unal.se1_app.dataAccess.model.Admin;

public interface AdminCallback {
    void onSuccess(@NonNull Admin admin);

    void onError(@NonNull Throwable throwable);
}