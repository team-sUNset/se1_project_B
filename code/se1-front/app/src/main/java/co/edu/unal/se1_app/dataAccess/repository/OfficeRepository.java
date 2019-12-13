package co.edu.unal.se1_app.dataAccess.repository;

import java.util.List;

import co.edu.unal.se1_app.dataAccess.interfaces.OfficeAPI;
import co.edu.unal.se1_app.dataAccess.model.Office;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OfficeRepository {

    private Retrofit retrofit;
    private List<Office> returnList;
    private Office returnObject;

    public OfficeRepository( ) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.12:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public List<Office> getOffices(){
        OfficeAPI officeAPI = retrofit.create(OfficeAPI.class);
        Call<List<Office>> call = officeAPI.getOffices();
        call.enqueue(new Callback<List<Office>>() {
            @Override
            public void onResponse(Call<List<Office>> call, Response<List<Office>> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                returnList = response.body();
            }

            @Override
            public void onFailure(Call<List<Office>> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
            }
        });
        return returnList;
    }

    public Office getOfficeById( Long id ){
        OfficeAPI officeAPI = retrofit.create(OfficeAPI.class);
        Call<Office> call = officeAPI.getOfficeById( id );
        call.enqueue(new Callback<Office>() {
            @Override
            public void onResponse(Call<Office> call, Response<Office> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                returnObject = response.body();
            }

            @Override
            public void onFailure(Call<Office> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
            }
        });
        return returnObject;
    }

    public Office createOffice( Office office ){
        OfficeAPI officeAPI = retrofit.create(OfficeAPI.class);
        Call<Office> call = officeAPI.createOffice( office );
        call.enqueue(new Callback<Office>() {
            @Override
            public void onResponse(Call<Office> call, Response<Office> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                returnObject = response.body();
            }

            @Override
            public void onFailure(Call<Office> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
            }
        });
        return returnObject;
    }

    public Office updateOffice( Long id , Office office ){
        OfficeAPI officeAPI = retrofit.create(OfficeAPI.class);
        Call<Office> call = officeAPI.updateOffice( id , office );
        call.enqueue(new Callback<Office>() {
            @Override
            public void onResponse(Call<Office> call, Response<Office> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                returnObject = response.body();
            }

            @Override
            public void onFailure(Call<Office> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
            }
        });
        return returnObject;
    }

    public void deleteOffice( Long id ){
        OfficeAPI officeAPI = retrofit.create(OfficeAPI.class);
        Call<Void> call = officeAPI.deleteOffice( id );
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
