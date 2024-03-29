package co.edu.unal.se1_app.businessLogic.controller;

import androidx.annotation.NonNull;

import java.util.List;

import javax.annotation.Nullable;

import co.edu.unal.se1_app.dataAccess.callback.EquipmentCallback;
import co.edu.unal.se1_app.dataAccess.callback.EquipmentListCallback;
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

    public void getEquipmentById( Long id , @Nullable EquipmentCallback callbacks ){
        equipmentRepository = new EquipmentRepository();
        equipmentRepository.getEquipmentById(id, new EquipmentCallback() {
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

    public void setStock( Long id , int newStock , @Nullable EquipmentCallback callbacks ){
        equipmentRepository = new EquipmentRepository();
        equipmentRepository.getEquipmentById(id, new EquipmentCallback() {
            @Override
            public void onSuccess(@NonNull Equipment equipment) {
                if( equipment == null ) callbacks.onSuccess( null );
                equipment.setStock( newStock );
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

    public void getEquipment( @Nullable EquipmentListCallback callbacks ){
        equipmentRepository = new EquipmentRepository();
        equipmentRepository.getEquipment(new EquipmentListCallback() {
            @Override
            public void onSuccess(@NonNull List<Equipment> equipment) {
                callbacks.onSuccess( equipment );
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                callbacks.onError( throwable );
            }
        });
    }

    public void deleteEquipment ( Long id ){
        equipmentRepository = new EquipmentRepository();
        equipmentRepository.deleteEquipment( id );
    }

}
