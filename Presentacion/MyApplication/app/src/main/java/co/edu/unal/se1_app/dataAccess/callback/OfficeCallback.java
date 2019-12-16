package co.edu.unal.se1_app.dataAccess.callback;

import androidx.annotation.NonNull;

import co.edu.unal.se1_app.dataAccess.model.Office;

public interface OfficeCallback {
    void onSuccess(@NonNull Office office);

    void onError(@NonNull Throwable throwable);
}