package co.edu.unal.se1_app.businessLogic.controller;

import co.edu.unal.se1_app.dataAccess.model.Reserve;
import co.edu.unal.se1_app.dataAccess.repository.EquipmentRepository;
import co.edu.unal.se1_app.dataAccess.repository.OfficeRepository;
import co.edu.unal.se1_app.dataAccess.repository.ReserveRepository;
import co.edu.unal.se1_app.dataAccess.repository.StudentRepository;

public class ReserveController {

    ReserveRepository reserveRepository;

    public ReserveController() {

    }

    public Reserve createReserve(Reserve reserve){
        reserveRepository = new ReserveRepository();
        StudentRepository studentRepository = new StudentRepository();
        EquipmentRepository equipmentRepository = new EquipmentRepository();
        /// Corregir segun tipo
        if( studentRepository.getStudentById( reserve.getStudentId() ) == null ||
                equipmentRepository.getEquipmentById( reserve.getElementId() ) == null ||
                !validDate( reserve.getStartDateTime() ) ||
                !validDate( reserve.getEndDateTime() ) ){
            return null;
        }

        return reserveRepository.createReserve( reserve );
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
