package co.edu.unal.se1_app.dataAccess.interfaces;

import java.util.List;

import co.edu.unal.se1_app.dataAccess.model.Space;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SpaceAPI {

    @GET("spaces")
    Call < List<Space> > getSpaces();

    @GET("spaces/{id}")
    Call < Space > getSpaceById(@Path("id") Long id);

    @POST("spaces")
    Call < Space > createSpace(@Body Space space);

    @PUT("spaces/{id}")
    Call <Space> updateSpace(@Path("id") Long id, @Body Space space);

    @DELETE("spaces/{id}")
    Call <Void> deleteSpace(@Path("id") Long id);
}
