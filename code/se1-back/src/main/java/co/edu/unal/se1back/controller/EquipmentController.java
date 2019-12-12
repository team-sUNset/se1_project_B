package co.edu.unal.se1back.controller;

import co.edu.unal.se1back.exception.ResourceNotFoundException;
import co.edu.unal.se1back.model.Equipment;
import co.edu.unal.se1back.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by riperezro on 24/11/19.
 */
@RestController
@RequestMapping("/api")
public class EquipmentController {

    @Autowired
    EquipmentRepository equipmentRepository;

    @GetMapping("/equipment")
    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    @PostMapping("/equipment")
    public Equipment createEquipment(@Valid @RequestBody Equipment equipment) { return equipmentRepository.save(equipment); }

    @GetMapping("/equipment/{id}")
    public Equipment getEquipmentById(@PathVariable(value = "id") Long equipmentId) {
        return equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment", "id", equipmentId));
    }

    @PutMapping("/equipment/{id}")
    public Equipment updateEquipment(@PathVariable(value = "id") Long equipmentId,
                           @Valid @RequestBody Equipment equipmentDetails) {

        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment", "id", equipmentDetails));

        equipment.setName(equipmentDetails.getName());
        equipment.setOfficeID(equipmentDetails.getOfficeID());
        equipment.setStock(equipmentDetails.getStock());
        Equipment updatedEquipment = equipmentRepository.save(equipment);
        return updatedEquipment;
    }

    @DeleteMapping("/equipment/{id}")
    public ResponseEntity<?> deleteEquipment(@PathVariable(value = "id") Long equipmentId) {

        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment", "id", equipmentId));

        equipmentRepository.delete(equipment);
        return ResponseEntity.ok().build();
    }
}