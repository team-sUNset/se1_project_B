package co.edu.unal.se1_app.dataAccess.callback;

import androidx.annotation.NonNull;

import java.util.List;

import co.edu.unal.se1_app.dataAccess.model.Student;

public interface StudentListCallback {
    void onSuccess(@NonNull List<Student> students);

    void onError(@NonNull Throwable throwable);
}