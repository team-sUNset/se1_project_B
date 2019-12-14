package co.edu.unal.se1_app.dataAccess.repository;

import java.util.List;

import javax.annotation.Nullable;

import co.edu.unal.se1_app.dataAccess.callback.SpaceCallback;
import co.edu.unal.se1_app.dataAccess.callback.SpaceListCallback;
import co.edu.unal.se1_app.dataAccess.interfaces.SpaceAPI;
import co.edu.unal.se1_app.dataAccess.model.Space;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpaceRepository {

    private Retrofit retrofit;

    public SpaceRepository( ) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.12:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void getSpaces( @Nullable SpaceListCallback callbacks ){
        SpaceAPI spaceAPI = retrofit.create(SpaceAPI.class);
        Call<List<Space>> call = spaceAPI.getSpaces();
        call.enqueue(new Callback<List<Space>>() {
            @Override
            public void onResponse(Call<List<Space>> call, Response<List<Space>> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                callbacks.onSuccess( response.body() );
            }

            @Override
            public void onFailure(Call<List<Space>> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
                callbacks.onError( t );
            }
        });
    }

    public void getSpaceById( Long id , @Nullable SpaceCallback callbacks ){
        SpaceAPI spaceAPI = retrofit.create(SpaceAPI.class);
        Call<Space> call = spaceAPI.getSpaceById( id );
        call.enqueue(new Callback<Space>() {
            @Override
            public void onResponse(Call<Space> call, Response<Space> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                callbacks.onSuccess( response.body() );
            }

            @Override
            public void onFailure(Call<Space> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
                callbacks.onError( t );
            }
        });
    }

    public void createSpace( Space space , @Nullable SpaceCallback callbacks ){
        SpaceAPI spaceAPI = retrofit.create(SpaceAPI.class);
        Call<Space> call = spaceAPI.createSpace( space );
        call.enqueue(new Callback<Space>() {
            @Override
            public void onResponse(Call<Space> call, Response<Space> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                callbacks.onSuccess( response.body() );
            }

            @Override
            public void onFailure(Call<Space> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
                callbacks.onError( t );
            }
        });
    }

    public void updateSpace( Long id , Space space , @Nullable SpaceCallback callbacks ){
        SpaceAPI spaceAPI = retrofit.create(SpaceAPI.class);
        Call<Space> call = spaceAPI.updateSpace( id , space );
        call.enqueue(new Callback<Space>() {
            @Override
            public void onResponse(Call<Space> call, Response<Space> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                callbacks.onSuccess( response.body() );
            }

            @Override
            public void onFailure(Call<Space> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
                callbacks.onError( t );
            }
        });
    }

    public void deleteSpace( Long id ){
        SpaceAPI spaceAPI = retrofit.create(SpaceAPI.class);
        Call<Void> call = spaceAPI.deleteSpace( id );
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println( "Code: " + response.code() + "\n" );
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
            }
        });
        return;
    }

}
