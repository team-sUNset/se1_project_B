package co.edu.unal.se1_app.dataAccess.repository;

import java.util.List;

import co.edu.unal.se1_app.dataAccess.interfaces.EquipmentAPI;
import co.edu.unal.se1_app.dataAccess.model.Equipment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EquipmentRepository {

    private Retrofit retrofit;
    private List<Equipment> returnList;
    private Equipment returnObject;

    public EquipmentRepository( ) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.12:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public List<Equipment> getEquipment(){
        returnList = null;
        EquipmentAPI equipmentAPI = retrofit.create(EquipmentAPI.class);
        Call<List<Equipment>> call = equipmentAPI.getEquipment();
        call.enqueue(new Callback<List<Equipment>>() {
            @Override
            public void onResponse(Call<List<Equipment>> call, Response<List<Equipment>> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                returnList = response.body();
            }

            @Override
            public void onFailure(Call<List<Equipment>> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
            }
        });
        return returnList;
    }

    public Equipment getEquipmentById( Long id ){
        returnObject = null;
        EquipmentAPI equipmentAPI = retrofit.create(EquipmentAPI.class);
        Call<Equipment> call = equipmentAPI.getEquipmentById( id );
        call.enqueue(new Callback<Equipment>() {
            @Override
            public void onResponse(Call<Equipment> call, Response<Equipment> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                returnObject = response.body();
            }

            @Override
            public void onFailure(Call<Equipment> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
            }
        });
        return returnObject;
    }

    public Equipment createEquipment( Equipment equipment ){
        returnObject = null;
        EquipmentAPI equipmentAPI = retrofit.create(EquipmentAPI.class);
        Call<Equipment> call = equipmentAPI.createEquipment( equipment );
        call.enqueue(new Callback<Equipment>() {
            @Override
            public void onResponse(Call<Equipment> call, Response<Equipment> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                returnObject = response.body();
            }

            @Override
            public void onFailure(Call<Equipment> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
            }
        });
        return returnObject;
    }

    public Equipment updateEquipment( Long id , Equipment equipment ){
        returnObject = null;
        EquipmentAPI equipmentAPI = retrofit.create(EquipmentAPI.class);
        Call<Equipment> call = equipmentAPI.updateEquipment( id , equipment );
        call.enqueue(new Callback<Equipment>() {
            @Override
            public void onResponse(Call<Equipment> call, Response<Equipment> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                returnObject = response.body();
            }

            @Override
            public void onFailure(Call<Equipment> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
            }
        });
        return returnObject;
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
