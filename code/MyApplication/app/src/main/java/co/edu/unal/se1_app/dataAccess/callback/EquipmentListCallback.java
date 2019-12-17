package co.edu.unal.se1_app.dataAccess.callback;

import androidx.annotation.NonNull;

import java.util.List;

import co.edu.unal.se1_app.dataAccess.model.Equipment;

public interface EquipmentListCallback {
    void onSuccess(@NonNull List<Equipment> equipment);

    void onError(@NonNull Throwable throwable);
}