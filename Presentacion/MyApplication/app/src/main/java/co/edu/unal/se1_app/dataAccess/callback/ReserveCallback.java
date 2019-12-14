package co.edu.unal.se1_app.dataAccess.callback;

import androidx.annotation.NonNull;

import co.edu.unal.se1_app.dataAccess.model.Reserve;

public interface ReserveCallback {
    void onSuccess(@NonNull Reserve Reserve);

    void onError(@NonNull Throwable throwable);
}