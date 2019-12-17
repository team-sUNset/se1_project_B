package co.edu.unal.se1_app.dataAccess.callback;

import androidx.annotation.NonNull;

import co.edu.unal.se1_app.dataAccess.model.Equipment;

public interface EquipmentCallback {
    void onSuccess(@NonNull Equipment equipment);

    void onError(@NonNull Throwable throwable);
}