package co.edu.unal.se1back.controller;

import co.edu.unal.se1back.exception.ResourceNotFoundException;
import co.edu.unal.se1back.model.Reserve;
import co.edu.unal.se1back.repository.ReserveRepository;
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
public class ReserveController {

    @Autowired
    ReserveRepository reserveRepository;

    @GetMapping("/reserves")
    public List<Reserve> getAllReserves() {
        return reserveRepository.findAll();
    }

    @PostMapping("/reserves")
    public Reserve createReserve(@Valid @RequestBody Reserve reserve) {
        return reserveRepository.save(reserve);
    }

    @GetMapping("/reserves/{id}")
    public Reserve getReserveById(@PathVariable(value = "id") Long reserveId) {
        return reserveRepository.findById(reserveId)
                .orElseThrow(() -> new ResourceNotFoundException("Reserve", "id", reserveId));
    }

    @PutMapping("/reserves/{id}")
    public Reserve updateReserve(@PathVariable(value = "id") Long reserveId,
                           @Valid @RequestBody Reserve reserveDetails) {

        Reserve reserve = reserveRepository.findById(reserveId)
                .orElseThrow(() -> new ResourceNotFoundException("Reserve", "id", reserveId));

        reserve.setType(reserveDetails.isType());
        reserve.setStudentId(reserveDetails.getStudentId());
        reserve.setElementId(reserveDetails.getElementId());
        reserve.setStartDateTime(reserveDetails.getStartDateTime());
        reserve.setEndDateTime(reserveDetails.getEndDateTime());
        Reserve updatedReserve = reserveRepository.save(reserve);
        return updatedReserve;
    }

    @DeleteMapping("/reserves/{id}")
    public ResponseEntity<?> deleteReserve(@PathVariable(value = "id") Long reserveId) {

        Reserve reserve = reserveRepository.findById(reserveId)
                .orElseThrow(() -> new ResourceNotFoundException("Reserve", "id", reserveId));

        reserveRepository.delete(reserve);
        return ResponseEntity.ok().build();
    }
}