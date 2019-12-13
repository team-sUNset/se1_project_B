package co.edu.unal.se1_app.dataAccess.interfaces;

import java.util.List;

import co.edu.unal.se1_app.dataAccess.model.Equipment;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EquipmentAPI {

    @GET("equipment")
    Call < List<Equipment> > getEquipment();

    @GET("equipment/{id}")
    Call < Equipment > getEquipmentById(@Path("id") Long id);

    @POST("equipment")
    Call < Equipment > createEquipment(@Body Equipment equipment);

    @PUT("equipment/{id}")
    Call <Equipment> updateEquipment(@Path("id") Long id, @Body Equipment equipment);

    @DELETE("equipment/{id}")
    Call <Void> deleteEquipment(@Path("id") Long id);
}
