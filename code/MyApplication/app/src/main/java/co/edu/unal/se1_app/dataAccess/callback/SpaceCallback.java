package co.edu.unal.se1_app.dataAccess.callback;

import androidx.annotation.NonNull;

import co.edu.unal.se1_app.dataAccess.model.Space;

public interface SpaceCallback {
    void onSuccess(@NonNull Space space);

    void onError(@NonNull Throwable throwable);
}