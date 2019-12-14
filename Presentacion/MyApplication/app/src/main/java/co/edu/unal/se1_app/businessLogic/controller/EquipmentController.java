package co.edu.unal.se1_app.businessLogic.controller;

import androidx.annotation.NonNull;

import javax.annotation.Nullable;

import co.edu.unal.se1_app.dataAccess.callback.EquipmentCallback;
import co.edu.unal.se1_app.dataAccess.model.Equipment;
import co.edu.unal.se1_app.dataAccess.repository.EquipmentRepository;

public class EquipmentController {

    EquipmentRepository equipmentRepository;

    public EquipmentController() {

    }

    public void createEquipment( Equipment equipment , @Nullable EquipmentCallback callbacks ){
        equipmentRepository = new EquipmentRepository();
        equipmentRepository.createEquipment(equipment, new EquipmentCallback() {
            @Override
            public void onSuccess(@NonNull Equipment equipment) {
                callbacks.onSuccess( equipment );
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                callbacks.onError( throwable );
            }
        });
    }

    public void reduceStock( Long id , @Nullable EquipmentCallback callbacks ){
        equipmentRepository = new EquipmentRepository();
        equipmentRepository.getEquipmentById(id, new EquipmentCallback() {
            @Override
            public void onSuccess(@NonNull Equipment equipment) {
                if( equipment == null || equipment.getStock() <= 0 ) callbacks.onSuccess( null );
                equipment.setStock( equipment.getStock() - 1 );
                equipmentRepository.updateEquipment( id, equipment, new EquipmentCallback() {
                    @Override
                    public void onSuccess(@NonNull Equipment updated) {
                        callbacks.onSuccess( updated );
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {
                        callbacks.onError( throwable );
                    }
                });
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                callbacks.onError( throwable );
            }
        });
    }

    public void addStock( Long id , @Nullable EquipmentCallback callbacks ){
        equipmentRepository = new EquipmentRepository();
        equipmentRepository.getEquipmentById(id, new EquipmentCallback() {
            @Override
            public void onSuccess(@NonNull Equipment equipment) {
                if( equipment == null ) callbacks.onSuccess( null );
                equipment.setStock( equipment.getStock() + 1 );
                equipmentRepository.updateEquipment( id, equipment, new EquipmentCallback() {
                    @Override
                    public void onSuccess(@NonNull Equipment updated) {
                        callbacks.onSuccess( updated );
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {
                        callbacks.onError( throwable );
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
