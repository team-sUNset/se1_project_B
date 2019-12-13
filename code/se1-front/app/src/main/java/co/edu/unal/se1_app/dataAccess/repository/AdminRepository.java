package co.edu.unal.se1_app.dataAccess.repository;

import java.util.List;

import co.edu.unal.se1_app.dataAccess.interfaces.AdminAPI;
import co.edu.unal.se1_app.dataAccess.model.Admin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminRepository {

    private Retrofit retrofit;
    private List<Admin> returnList;
    private Admin returnObject;

    public AdminRepository( ) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.12:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public List<Admin> getAdmins(){
        returnList = null;
        AdminAPI adminAPI = retrofit.create(AdminAPI.class);
        Call<List<Admin>> call = adminAPI.getAdmins();
        call.enqueue(new Callback<List<Admin>>() {
            @Override
            public void onResponse(Call<List<Admin>> call, Response<List<Admin>> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                returnList = response.body();
            }

            @Override
            public void onFailure(Call<List<Admin>> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
            }
        });
        return returnList;
    }

    public Admin getAdminById( Long id ){
        returnObject = null;
        AdminAPI adminAPI = retrofit.create(AdminAPI.class);
        Call<Admin> call = adminAPI.getAdminById( id );
        call.enqueue(new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                returnObject = response.body();
            }

            @Override
            public void onFailure(Call<Admin> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
            }
        });
        return returnObject;
    }

    public Admin createAdmin( Admin admin ){
        returnObject = null;
        AdminAPI adminAPI = retrofit.create(AdminAPI.class);
        Call<Admin> call = adminAPI.createAdmin( admin );
        call.enqueue(new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                returnObject = response.body();
            }

            @Override
            public void onFailure(Call<Admin> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
            }
        });
        return returnObject;
    }

    public Admin updateAdmin( Long id , Admin admin ){
        returnObject = null;
        AdminAPI adminAPI = retrofit.create(AdminAPI.class);
        Call<Admin> call = adminAPI.updateAdmin( id , admin );
        call.enqueue(new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                returnObject = response.body();
            }

            @Override
            public void onFailure(Call<Admin> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
            }
        });
        return returnObject;
    }

    public void deleteAdmin( Long id ){
        AdminAPI adminAPI = retrofit.create(AdminAPI.class);
        Call<Void> call = adminAPI.deleteAdmin( id );
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
