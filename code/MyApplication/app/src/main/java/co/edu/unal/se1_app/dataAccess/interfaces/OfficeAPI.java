package co.edu.unal.se1_app.dataAccess.interfaces;

import java.util.List;

import co.edu.unal.se1_app.dataAccess.model.Office;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OfficeAPI {

    @GET("offices")
    Call < List<Office> > getOffices();

    @GET("offices/{id}")
    Call < Office > getOfficeById(@Path("id") Long id);

    @POST("offices")
    Call < Office > createOffice(@Body Office office);

    @PUT("offices/{id}")
    Call <Office> updateOffice(@Path("id") Long id, @Body Office office);

    @DELETE("offices/{id}")
    Call <Void> deleteOffice(@Path("id") Long id);
}
