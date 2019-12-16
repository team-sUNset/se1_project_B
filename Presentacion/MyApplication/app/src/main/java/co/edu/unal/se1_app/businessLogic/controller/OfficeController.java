package co.edu.unal.se1_app.businessLogic.controller;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import co.edu.unal.se1_app.dataAccess.callback.EquipmentListCallback;
import co.edu.unal.se1_app.dataAccess.callback.OfficeCallback;
import co.edu.unal.se1_app.dataAccess.model.Equipment;
import co.edu.unal.se1_app.dataAccess.model.Office;
import co.edu.unal.se1_app.dataAccess.repository.EquipmentRepository;
import co.edu.unal.se1_app.dataAccess.repository.OfficeRepository;

public class OfficeController {

    OfficeRepository officeRepository;

    public OfficeController() {

    }

    public void createOffice( Office office , @Nullable OfficeCallback callbacks ){
        officeRepository = new OfficeRepository();
        officeRepository.createOffice(office, new OfficeCallback() {
            @Override
            public void onSuccess(@NonNull Office office) {
                callbacks.onSuccess( office );
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                callbacks.onError( throwable );
            }
        });
    }

    public void equipmentInOfice( Long officeId , @Nullable EquipmentListCallback callbacks ){
        officeRepository = new OfficeRepository();
        EquipmentRepository equipmentRepository = new EquipmentRepository();

        officeRepository.getOfficeById( officeId , new OfficeCallback() {
            @Override
            public void onSuccess(@NonNull Office office) {
                equipmentRepository.getEquipment(new EquipmentListCallback() {
                    @Override
                    public void onSuccess(@NonNull List<Equipment> allEquipment) {
                        List<Equipment> ret = new ArrayList<>();
                        for( Equipment eq : allEquipment ){
                            if( eq.getOfficeID() == officeId ) ret.add( eq );
                        }
                        callbacks.onSuccess( ret );
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {

                    }
                });
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                callbacks.onError( throwable );
            }
        });
    }
}
