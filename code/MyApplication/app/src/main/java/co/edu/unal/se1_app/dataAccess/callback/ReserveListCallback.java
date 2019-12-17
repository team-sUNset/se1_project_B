package co.edu.unal.se1_app.dataAccess.callback;

import androidx.annotation.NonNull;

import java.util.List;

import co.edu.unal.se1_app.dataAccess.model.Reserve;

public interface ReserveListCallback {
    void onSuccess(@NonNull List<Reserve> reserves);

    void onError(@NonNull Throwable throwable);
}