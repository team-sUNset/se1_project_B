package co.edu.unal.se1_app.businessLogic.controller;

import androidx.annotation.NonNull;

import javax.annotation.Nullable;

import co.edu.unal.se1_app.dataAccess.callback.EquipmentCallback;
import co.edu.unal.se1_app.dataAccess.callback.ReserveCallback;
import co.edu.unal.se1_app.dataAccess.callback.StudentCallback;
import co.edu.unal.se1_app.dataAccess.model.Equipment;
import co.edu.unal.se1_app.dataAccess.model.Reserve;
import co.edu.unal.se1_app.dataAccess.model.Student;
import co.edu.unal.se1_app.dataAccess.repository.EquipmentRepository;
import co.edu.unal.se1_app.dataAccess.repository.ReserveRepository;
import co.edu.unal.se1_app.dataAccess.repository.SpaceRepository;
import co.edu.unal.se1_app.dataAccess.repository.StudentRepository;

public class ReserveController {

    ReserveRepository reserveRepository;

    public ReserveController() {

    }

    public void createReserve( Reserve reserve , @Nullable ReserveCallback callbacks ){
        reserveRepository = new ReserveRepository();
        StudentRepository studentRepository = new StudentRepository();
        EquipmentRepository equipmentRepository = new EquipmentRepository();
        SpaceRepository spaceRepository = new SpaceRepository();
        /// Type 0 = Equipment
        /// Type 1 = Space
        if( !validDate( reserve.getStartDateTime() ) || !validDate( reserve.getEndDateTime() ) ){
            callbacks.onError( new Exception( "Invalid Date" ) );
        }
        if( reserve.isType() == false ){
            studentRepository.getStudentById(reserve.getStudentId(), new StudentCallback() {
                @Override
                public void onSuccess(@NonNull Student student) {
                    equipmentRepository.getEquipmentById(reserve.getElementId(), new EquipmentCallback() {
                        @Override
                        public void onSuccess(@NonNull Equipment equipment) {
                            reserveRepository.createReserve(reserve, new ReserveCallback() {
                                @Override
                                public void onSuccess(@NonNull Reserve created ) {
                                    callbacks.onSuccess( created );
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

                @Override
                public void onError(@NonNull Throwable throwable) {
                    callbacks.onError( throwable );
                }
            });
        }
    }

    private boolean validDate( String date ){
        // Format DD/MM/YYYY
        if( date.length() != 10 ) return false;
        if( !Character.isDigit( date.charAt(0) ) ) return false;
        if( !Character.isDigit( date.charAt(1) ) ) return false;
        if( date.charAt(2) != '/' ) return false;
        if( !Character.isDigit( date.charAt(3) ) ) return false;
        if( !Character.isDigit( date.charAt(4) ) ) return false;
        if( date.charAt(5) != '/' ) return false;
        if( !Character.isDigit( date.charAt(6) ) ) return false;
        if( !Character.isDigit( date.charAt(7) ) ) return false;
        if( !Character.isDigit( date.charAt(8) ) ) return false;
        if( !Character.isDigit( date.charAt(9) ) ) return false;
        return true;
    }


}
