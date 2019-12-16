package co.edu.unal.se1_app.dataAccess.interfaces;

import java.util.List;

import co.edu.unal.se1_app.dataAccess.model.Reserve;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ReserveAPI {

    @GET("reserves")
    Call < List<Reserve> > getReserves();

    @GET("reserves/{id}")
    Call < Reserve > getReserveById(@Path("id") Long id);

    @POST("reserves")
    Call < Reserve > createReserve(@Body Reserve reserve);

    @PUT("reserves/{id}")
    Call <Reserve> updateReserve(@Path("id") Long id, @Body Reserve reserve);

    @DELETE("reserves/{id}")
    Call <Void> deleteReserve(@Path("id") Long id);
}
