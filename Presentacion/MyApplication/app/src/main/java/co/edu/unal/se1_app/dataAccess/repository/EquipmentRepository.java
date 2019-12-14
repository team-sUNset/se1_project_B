package co.edu.unal.se1_app.dataAccess.repository;

import java.util.List;

import javax.annotation.Nullable;

import co.edu.unal.se1_app.dataAccess.callback.EquipmentCallback;
import co.edu.unal.se1_app.dataAccess.callback.EquipmentListCallback;
import co.edu.unal.se1_app.dataAccess.interfaces.EquipmentAPI;
import co.edu.unal.se1_app.dataAccess.model.Equipment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EquipmentRepository {

    private Retrofit retrofit;

    public EquipmentRepository( ) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.12:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void getEquipment( @Nullable EquipmentListCallback callbacks ){
        EquipmentAPI equipmentAPI = retrofit.create(EquipmentAPI.class);
        Call<List<Equipment>> call = equipmentAPI.getEquipment();
        call.enqueue(new Callback<List<Equipment>>() {
            @Override
            public void onResponse(Call<List<Equipment>> call, Response<List<Equipment>> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                callbacks.onSuccess( response.body() );
            }

            @Override
            public void onFailure(Call<List<Equipment>> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
                callbacks.onError( t );
            }
        });
    }

    public void getEquipmentById( Long id , @Nullable EquipmentCallback callbacks ){
        EquipmentAPI equipmentAPI = retrofit.create(EquipmentAPI.class);
        Call<Equipment> call = equipmentAPI.getEquipmentById( id );
        call.enqueue(new Callback<Equipment>() {
            @Override
            public void onResponse(Call<Equipment> call, Response<Equipment> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                callbacks.onSuccess( response.body() );
            }

            @Override
            public void onFailure(Call<Equipment> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
                callbacks.onError( t );
            }
        });
    }

    public void createEquipment( Equipment equipment , @Nullable EquipmentCallback callbacks ){
        EquipmentAPI equipmentAPI = retrofit.create(EquipmentAPI.class);
        Call<Equipment> call = equipmentAPI.createEquipment( equipment );
        call.enqueue(new Callback<Equipment>() {
            @Override
            public void onResponse(Call<Equipment> call, Response<Equipment> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                callbacks.onSuccess( response.body() );
            }

            @Override
            public void onFailure(Call<Equipment> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
                callbacks.onError( t );
            }
        });
    }

    public void updateEquipment( Long id , Equipment equipment , @Nullable EquipmentCallback callbacks ){
        EquipmentAPI equipmentAPI = retrofit.create(EquipmentAPI.class);
        Call<Equipment> call = equipmentAPI.updateEquipment( id , equipment );
        call.enqueue(new Callback<Equipment>() {
            @Override
            public void onResponse(Call<Equipment> call, Response<Equipment> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                callbacks.onSuccess( response.body() );
            }

            @Override
            public void onFailure(Call<Equipment> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
                callbacks.onError( t );
            }
        });
    }

    public void deleteEquipment( Long id ){
        EquipmentAPI equipmentAPI = retrofit.create(EquipmentAPI.class);
        Call<Void> call = equipmentAPI.deleteEquipment( id );
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
