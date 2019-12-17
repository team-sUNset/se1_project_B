package co.edu.unal.se1_app.businessLogic.controller;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import co.edu.unal.se1_app.dataAccess.callback.EquipmentCallback;
import co.edu.unal.se1_app.dataAccess.callback.ReserveCallback;
import co.edu.unal.se1_app.dataAccess.callback.ReserveListCallback;
import co.edu.unal.se1_app.dataAccess.callback.SpaceCallback;
import co.edu.unal.se1_app.dataAccess.callback.StudentCallback;
import co.edu.unal.se1_app.dataAccess.model.Equipment;
import co.edu.unal.se1_app.dataAccess.model.Reserve;
import co.edu.unal.se1_app.dataAccess.model.Space;
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
        if( !validDateTime( reserve.getStartDateTime() ) ){
            callbacks.onError( new Exception( "Invalid DateTime" ) );
        }
        if( reserve.isType() == false ){
            studentRepository.getStudentById(reserve.getStudentId(), new StudentCallback() {
                @Override
                public void onSuccess(@NonNull Student student) {
                    equipmentRepository.getEquipmentById(reserve.getElementId(), new EquipmentCallback() {
                        @Override
                        public void onSuccess(@NonNull Equipment equipment) {
                            if (countReserves(reserve.getElementId(), reserve.isType(),
                                    reserve.getStartDateTime()) >= equipment.getStock() ) {
                                callbacks.onError(new Exception("Not enough stock"));
                            }else {
                                reserveRepository.createReserve(reserve, new ReserveCallback() {
                                    @Override
                                    public void onSuccess(@NonNull Reserve created) {
                                        callbacks.onSuccess(created);
                                    }

                                    @Override
                                    public void onError(@NonNull Throwable throwable) {
                                        callbacks.onError(throwable);
                                    }
                                });
                            }
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
        }else{
            studentRepository.getStudentById(reserve.getStudentId(), new StudentCallback() {
                @Override
                public void onSuccess(@NonNull Student student) {
                    spaceRepository.getSpaceById(reserve.getElementId(), new SpaceCallback() {
                        @Override
                        public void onSuccess(@NonNull Space space) {
                            if (countReserves(reserve.getElementId(), reserve.isType(),
                                    reserve.getStartDateTime()) != 0) {
                                callbacks.onError(new Exception("Space not available"));
                            }else{
                                reserveRepository.createReserve(reserve, new ReserveCallback() {
                                    @Override
                                    public void onSuccess(@NonNull Reserve created) {
                                        callbacks.onSuccess(created);
                                    }

                                    @Override
                                    public void onError(@NonNull Throwable throwable) {
                                        callbacks.onError(throwable);
                                    }
                                });
                            }
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

    private boolean validDateTime( String date ){
        // Format DD/MM/YYYY HH:MM
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
        if( date.charAt(10) != ' ' ) return false;
        if( !Character.isDigit( date.charAt(11) ) ) return false;
        if( !Character.isDigit( date.charAt(12) ) ) return false;
        if( date.charAt(13) != ':' ) return false;
        if( date.charAt(14) != '0' ) return false;
        if( date.charAt(15) != '0' ) return false;
        if( !Character.isDigit( date.charAt(14) ) ) return false;
        if( !Character.isDigit( date.charAt(15) ) ) return false;
        if( Integer.parseInt( date.substring(0,2) ) < 1 ) return false;
        if( Integer.parseInt( date.substring(0,2) ) > 31 ) return false;
        if( Integer.parseInt( date.substring(3,5) ) < 1 ) return false;
        if( Integer.parseInt( date.substring(3,5) ) > 12 ) return false;
        if( Integer.parseInt( date.substring(11,13) ) < 0 ) return false;
        if( Integer.parseInt( date.substring(11,13) ) > 23 ) return false;
        return true;
    }

    private List<Reserve> list;

    private int countReserves( Long id , boolean type ,  String date ){
        int count = 0;

        reserveRepository.getReserves(new ReserveListCallback() {
            @Override
            public void onSuccess(@NonNull List<Reserve> reserves) {
                list = reserves;
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                list = new ArrayList<>();
                System.out.println("Reserve Controller: " + throwable.getMessage());
            }
        });
        for( Reserve r : list ){
            if( r.isType() == type && id.equals( r.getId() ) && date.equals( r.getStartDateTime() ) )
                count ++;
        }
        return count;
    }

    public void getReserves ( @Nullable ReserveListCallback callbacks ){
        reserveRepository = new ReserveRepository();
        reserveRepository.getReserves(new ReserveListCallback() {
            @Override
            public void onSuccess(@NonNull List<Reserve> reserves) {
                callbacks.onSuccess( reserves );
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                callbacks.onError( throwable );
            }
        });
    }

    public void deleteReserve( Long id ){
        reserveRepository = new ReserveRepository();
        reserveRepository.deleteReserve( id );
    }

    public void getReservesByStudentId( Long studnetId , @Nullable ReserveListCallback callbacks ){
        reserveRepository = new ReserveRepository();
        reserveRepository.getReserves(new ReserveListCallback() {
            @Override
            public void onSuccess(@NonNull List<Reserve> reserves) {
                List<Reserve> ret = new ArrayList<>();
                for( Reserve r : reserves ){
                    if( r.getStudentId().equals( studnetId ) ) ret.add(r);
                }
                callbacks.onSuccess( ret );
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                callbacks.onError( throwable );
            }
        });
    }

}
