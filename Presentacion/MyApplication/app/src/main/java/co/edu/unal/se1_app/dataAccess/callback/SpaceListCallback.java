package co.edu.unal.se1_app.dataAccess.callback;

import androidx.annotation.NonNull;

import java.util.List;

import co.edu.unal.se1_app.dataAccess.model.Space;

public interface SpaceListCallback {
    void onSuccess(@NonNull List<Space> spaces);

    void onError(@NonNull Throwable throwable);
}