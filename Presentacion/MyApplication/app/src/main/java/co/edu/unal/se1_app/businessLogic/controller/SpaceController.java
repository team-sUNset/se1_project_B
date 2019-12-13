package co.edu.unal.se1_app.businessLogic.controller;

import co.edu.unal.se1_app.dataAccess.model.Space;
import co.edu.unal.se1_app.dataAccess.repository.SpaceRepository;

public class SpaceController {

    SpaceRepository spaceRepository;

    public SpaceController() {

    }

    public Space createSpace(Space space){
        spaceRepository = new SpaceRepository();
        return spaceRepository.createSpace( space );
    }

}
