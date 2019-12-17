package co.edu.unal.se1back.controller;

import co.edu.unal.se1back.exception.ResourceNotFoundException;
import co.edu.unal.se1back.model.Space;
import co.edu.unal.se1back.repository.SpaceRepository;
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
public class SpaceController {

    @Autowired
    SpaceRepository spaceRepository;

    @GetMapping("/spaces")
    public List<Space> getAllSpaces() {
        return spaceRepository.findAll();
    }

    @PostMapping("/spaces")
    public Space createSpace(@Valid @RequestBody Space space) { return spaceRepository.save(space); }

    @GetMapping("/spaces/{id}")
    public Space getSpaceById(@PathVariable(value = "id") Long spaceId) {
        return spaceRepository.findById(spaceId)
                .orElseThrow(() -> new ResourceNotFoundException("Space", "id", spaceId));
    }

    @PutMapping("/spaces/{id}")
    public Space updateSpace(@PathVariable(value = "id") Long spaceId,
                           @Valid @RequestBody Space spaceDetails) {

        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(() -> new ResourceNotFoundException("Space", "id", spaceId));

        space.setName(spaceDetails.getName());
        Space updatedSpace = spaceRepository.save(space);
        return updatedSpace;
    }

    @DeleteMapping("/spaces/{id}")
    public ResponseEntity<?> deleteSpace(@PathVariable(value = "id") Long spaceId) {

        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(() -> new ResourceNotFoundException("Space", "id", spaceId));

        spaceRepository.delete(space);
        return ResponseEntity.ok().build();
    }
}