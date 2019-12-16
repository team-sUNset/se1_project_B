package co.edu.unal.se1_app.businessLogic.controller;

import androidx.annotation.NonNull;

import javax.annotation.Nullable;

import co.edu.unal.se1_app.dataAccess.callback.SpaceCallback;
import co.edu.unal.se1_app.dataAccess.model.Space;
import co.edu.unal.se1_app.dataAccess.repository.SpaceRepository;

public class SpaceController {

    SpaceRepository spaceRepository;

    public SpaceController() {

    }

    public void createSpace( Space space , @Nullable SpaceCallback callbacks ){
        spaceRepository = new SpaceRepository();
        spaceRepository.createSpace(space, new SpaceCallback() {
            @Override
            public void onSuccess(@NonNull Space space) {
                callbacks.onSuccess( space );
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                callbacks.onError( throwable );
            }
        });
    }

}
