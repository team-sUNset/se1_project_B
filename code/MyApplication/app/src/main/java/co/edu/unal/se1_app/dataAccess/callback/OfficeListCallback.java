package co.edu.unal.se1_app.dataAccess.callback;

import androidx.annotation.NonNull;

import java.util.List;

import co.edu.unal.se1_app.dataAccess.model.Office;

public interface OfficeListCallback {
    void onSuccess(@NonNull List<Office> offices);

    void onError(@NonNull Throwable throwable);
}