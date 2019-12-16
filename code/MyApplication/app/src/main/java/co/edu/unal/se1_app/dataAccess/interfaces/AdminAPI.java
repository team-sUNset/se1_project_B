package co.edu.unal.se1_app.dataAccess.interfaces;

import java.util.List;

import co.edu.unal.se1_app.dataAccess.model.Admin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AdminAPI {

    @GET("admins")
    Call < List<Admin> > getAdmins();

    @GET("admins/{id}")
    Call < Admin > getAdminById(@Path("id") Long id);

    @POST("admins")
    Call < Admin > createAdmin(@Body Admin admin);

    @PUT("admins/{id}")
    Call <Admin> updateAdmin(@Path("id") Long id, @Body Admin admin);

    @DELETE("admins/{id}")
    Call <Void> deleteAdmin(@Path("id") Long id);
}
