package co.edu.unal.se1_app.dataAccess.repository;

import java.util.List;

import javax.annotation.Nullable;

import co.edu.unal.se1_app.dataAccess.callback.ReserveCallback;
import co.edu.unal.se1_app.dataAccess.callback.ReserveListCallback;
import co.edu.unal.se1_app.dataAccess.interfaces.ReserveAPI;
import co.edu.unal.se1_app.dataAccess.model.Reserve;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReserveRepository {

    private Retrofit retrofit;

    public ReserveRepository( ) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.12:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void getReserves( @Nullable ReserveListCallback callbacks ){
        ReserveAPI reserveAPI = retrofit.create(ReserveAPI.class);
        Call<List<Reserve>> call = reserveAPI.getReserves();
        call.enqueue(new Callback<List<Reserve>>() {
            @Override
            public void onResponse(Call<List<Reserve>> call, Response<List<Reserve>> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                callbacks.onSuccess( response.body() );
            }

            @Override
            public void onFailure(Call<List<Reserve>> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
                callbacks.onError( t );
            }
        });
    }

    public void getReserveById( Long id , @Nullable ReserveCallback callbacks ){
        ReserveAPI reserveAPI = retrofit.create(ReserveAPI.class);
        Call<Reserve> call = reserveAPI.getReserveById( id );
        call.enqueue(new Callback<Reserve>() {
            @Override
            public void onResponse(Call<Reserve> call, Response<Reserve> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                callbacks.onSuccess( response.body() );
            }

            @Override
            public void onFailure(Call<Reserve> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
                callbacks.onError( t );
            }
        });
    }

    public void createReserve( Reserve reserve , @Nullable ReserveCallback callbacks ){
        ReserveAPI reserveAPI = retrofit.create(ReserveAPI.class);
        Call<Reserve> call = reserveAPI.createReserve( reserve );
        call.enqueue(new Callback<Reserve>() {
            @Override
            public void onResponse(Call<Reserve> call, Response<Reserve> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                callbacks.onSuccess( response.body() );
            }

            @Override
            public void onFailure(Call<Reserve> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
                callbacks.onError( t );
            }
        });
    }

    public void updateReserve( Long id , Reserve reserve , @Nullable ReserveCallback callbacks ){
        ReserveAPI reserveAPI = retrofit.create(ReserveAPI.class);
        Call<Reserve> call = reserveAPI.updateReserve( id , reserve );
        call.enqueue(new Callback<Reserve>() {
            @Override
            public void onResponse(Call<Reserve> call, Response<Reserve> response) {
                if( !response.isSuccessful() ){
                    System.out.println( "Code: " + response.code() + "\n" );
                    return;
                }
                callbacks.onSuccess( response.body() );
            }

            @Override
            public void onFailure(Call<Reserve> call, Throwable t) {
                System.out.println( "Message : " + t.getMessage() + "\n" );
                callbacks.onError( t );
            }
        });
    }

    public void deleteReserve( Long id ){
        ReserveAPI reserveAPI = retrofit.create(ReserveAPI.class);
        Call<Void> call = reserveAPI.deleteReserve( id );
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
