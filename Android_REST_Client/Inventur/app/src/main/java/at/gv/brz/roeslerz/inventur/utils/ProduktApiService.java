package at.gv.brz.roeslerz.inventur.utils;

import java.util.ArrayList;

import at.gv.brz.roeslerz.inventur.models.Produkt;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ProduktApiService {

    @GET("produkte")
    Call<ArrayList<Produkt>> getProdukt();

    @POST("insertprodukt")
    Call<Produkt> insertProdukt(@Body Produkt produkt);

    @PUT("updateprodukt")
    Call<Produkt> updateProdukt(@Body Produkt produkt);

    @HTTP(method = "DELETE", path = "deleteprodukt", hasBody = true)
    Call<Produkt> deleteProdukt(@Body Produkt produkt);
}
