package co.edu.unal.se1back.controller;

import co.edu.unal.se1back.exception.ResourceNotFoundException;
import co.edu.unal.se1back.model.Office;
import co.edu.unal.se1back.repository.OfficeRepository;
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
public class OfficeController {

    @Autowired
    OfficeRepository officeRepository;

    @GetMapping("/offices")
    public List<Office> getAllOffices() {
        return officeRepository.findAll();
    }

    @PostMapping("/offices")
    public Office createOffice(@Valid @RequestBody Office office) {
        return officeRepository.save(office);
    }

    @GetMapping("/offices/{id}")
    public Office getOfficeById(@PathVariable(value = "id") Long officeId) {
        return officeRepository.findById(officeId)
                .orElseThrow(() -> new ResourceNotFoundException("Office", "id", officeId));
    }

    @PutMapping("/offices/{id}")
    public Office updateOffice(@PathVariable(value = "id") Long officeId,
                           @Valid @RequestBody Office officeDetails) {

        Office office = officeRepository.findById(officeId)
                .orElseThrow(() -> new ResourceNotFoundException("Office", "id", officeId));

        office.setName(officeDetails.getName());
        Office updatedOffice = officeRepository.save(office);
        return updatedOffice;
    }

    @DeleteMapping("/offices/{id}")
    public ResponseEntity<?> deleteOffice(@PathVariable(value = "id") Long officeId) {

        Office office = officeRepository.findById(officeId)
                .orElseThrow(() -> new ResourceNotFoundException("Office", "id", officeId));

        officeRepository.delete(office);
        return ResponseEntity.ok().build();
    }
}