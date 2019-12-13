package co.edu.unal.se1_app.businessLogic.controller;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.se1_app.dataAccess.model.Equipment;
import co.edu.unal.se1_app.dataAccess.model.Office;
import co.edu.unal.se1_app.dataAccess.repository.EquipmentRepository;
import co.edu.unal.se1_app.dataAccess.repository.OfficeRepository;

public class OfficeController {

    OfficeRepository officeRepository;

    public OfficeController() {

    }

    public Office createOffice(Office office){
        officeRepository = new OfficeRepository();
        return officeRepository.createOffice( office );
    }

    public List<Equipment> equipmentInOfice( Long officeId ){
        officeRepository = new OfficeRepository();
        EquipmentRepository equipmentRepository = new EquipmentRepository();
        if( officeRepository.getOfficeById( officeId ) == null ) return null;
        List<Equipment> allEquipment = equipmentRepository.getEquipment();
        List<Equipment> ret = new ArrayList<>();
        for( Equipment eq : allEquipment ){
            if( eq.getOfficeID() == officeId ) ret.add( eq );
        }
        return ret;
    }
}
