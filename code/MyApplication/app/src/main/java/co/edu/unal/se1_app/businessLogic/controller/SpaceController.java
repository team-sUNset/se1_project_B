package co.edu.unal.se1_app.businessLogic.controller;

import androidx.annotation.NonNull;

import java.util.List;

import javax.annotation.Nullable;

import co.edu.unal.se1_app.dataAccess.callback.SpaceCallback;
import co.edu.unal.se1_app.dataAccess.callback.SpaceListCallback;
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

    public void getSpaces ( @Nullable SpaceListCallback callbacks ){
        spaceRepository = new SpaceRepository();
        spaceRepository.getSpaces(new SpaceListCallback() {
            @Override
            public void onSuccess(@NonNull List<Space> spaces) {
                callbacks.onSuccess( spaces );
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                callbacks.onError( throwable );
            }
        });
    }

    public void getSpaceByID ( Long id , @Nullable SpaceCallback callbacks ){
        spaceRepository = new SpaceRepository();
        spaceRepository.getSpaceById(id, new SpaceCallback() {
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

    public void deleteSpace( Long id ){
        spaceRepository = new SpaceRepository();
        spaceRepository.deleteSpace( id );
    }

}
