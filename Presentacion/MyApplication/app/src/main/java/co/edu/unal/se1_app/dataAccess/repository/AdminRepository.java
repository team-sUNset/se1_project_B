package co.edu.unal.se1_app.dataAccess.repository;

import java.util.List;

import javax.annotation.Nullable;

import co.edu.unal.se1_app.dataAccess.callback.AdminCallback;
import co.edu.unal.se1_app.dataAccess.callback.AdminListCallback;
import co.edu.unal.se1_app.dataAccess.interfaces.AdminAPI;
import co.edu.unal.se1_app.dataAccess.model.Admin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminRepository {

    private Retrofit retrofit;

    public AdminRepository( ) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.12:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void getAdmins( @Nullable AdminListCallback callbacks ){
        AdminAPI adminAPI = retrofit.create(AdminAPI.class);
        Call<List<Admin>> call = adminAPI.getAdmins();
        call.enqueue(new Callback<List<Admin>>() {
            @Override
            public void onResponse(Call<List<Admin>> call, Response<List<Admin>> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                callbacks.onSuccess( response.body() );
            }

            @Override
            public void onFailure(Call<List<Admin>> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
                callbacks.onError( t );
            }
        });
    }

    public void getAdminById( Long id , @Nullable AdminCallback callbacks ){
        AdminAPI adminAPI = retrofit.create(AdminAPI.class);
        Call<Admin> call = adminAPI.getAdminById( id );
        call.enqueue(new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                callbacks.onSuccess( response.body() );
            }

            @Override
            public void onFailure(Call<Admin> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
                callbacks.onError( t );
            }
        });
    }

    public void createAdmin( Admin admin , @Nullable AdminCallback callbacks ){
        AdminAPI adminAPI = retrofit.create(AdminAPI.class);
        Call<Admin> call = adminAPI.createAdmin( admin );
        call.enqueue(new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                callbacks.onSuccess( response.body() );
            }

            @Override
            public void onFailure(Call<Admin> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
                callbacks.onError( t );
            }
        });
    }

    public void updateAdmin( Long id , Admin admin , @Nullable AdminCallback callbacks ){
        AdminAPI adminAPI = retrofit.create(AdminAPI.class);
        Call<Admin> call = adminAPI.updateAdmin( id , admin );
        call.enqueue(new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                callbacks.onSuccess( response.body() );
            }

            @Override
            public void onFailure(Call<Admin> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
                callbacks.onError( t );
            }
        });
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
