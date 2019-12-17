package co.edu.unal.se1_app.dataAccess.callback;

import androidx.annotation.NonNull;

import co.edu.unal.se1_app.dataAccess.model.Student;

public interface IntegerCallback {
    void onSuccess(@NonNull Integer integer);

    void onError(@NonNull Throwable throwable);
}