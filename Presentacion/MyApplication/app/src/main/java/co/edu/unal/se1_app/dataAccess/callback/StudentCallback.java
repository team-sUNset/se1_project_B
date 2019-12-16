package co.edu.unal.se1_app.dataAccess.callback;

import androidx.annotation.NonNull;

import co.edu.unal.se1_app.dataAccess.model.Student;

public interface StudentCallback {
    void onSuccess(@NonNull Student student);

    void onError(@NonNull Throwable throwable);
}