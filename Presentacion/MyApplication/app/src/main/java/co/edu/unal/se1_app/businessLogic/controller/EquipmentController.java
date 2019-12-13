package co.edu.unal.se1_app.businessLogic.controller;

import co.edu.unal.se1_app.dataAccess.model.Equipment;
import co.edu.unal.se1_app.dataAccess.repository.EquipmentRepository;

public class EquipmentController {

    EquipmentRepository equipmentRepository;

    public EquipmentController() {

    }

    public Equipment createEquipment(Equipment equipment){
        equipmentRepository = new EquipmentRepository();
        return equipmentRepository.createEquipment( equipment );
    }

    public boolean reduceStock(Long id){
        equipmentRepository = new EquipmentRepository();
        Equipment equipment = equipmentRepository.getEquipmentById( id );
        if( equipment == null || equipment.getStock() <= 0 ) return false;
        equipment.setStock( equipment.getStock() - 1 );
        equipmentRepository.updateEquipment( id , equipment );
        return true;
    }

    public boolean addStock(Long id){
        equipmentRepository = new EquipmentRepository();
        Equipment equipment = equipmentRepository.getEquipmentById( id );
        if( equipment == null ) return false;
        equipment.setStock( equipment.getStock() - 1 );
        equipmentRepository.updateEquipment( id , equipment );
        return true;
    }

}
