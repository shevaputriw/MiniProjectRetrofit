package id.ac.polinema.miniprojectretrofit.api;

import java.util.List;
import java.util.Map;

import id.ac.polinema.miniprojectretrofit.DetailRekap;
import id.ac.polinema.miniprojectretrofit.model.AbsenGuru;
import id.ac.polinema.miniprojectretrofit.model.Detail;
import id.ac.polinema.miniprojectretrofit.model.Guru;
import id.ac.polinema.miniprojectretrofit.model.Rekap;
import id.ac.polinema.miniprojectretrofit.model.Siswa;
import id.ac.polinema.miniprojectretrofit.model.SiswaAdmin;
import id.ac.polinema.miniprojectretrofit.model.User;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("loginGuru")
    Call<ResponseBody> loginGuru(@Body User user);

    @GET("guru")
    Call<List<Guru>> getGuru();

    @GET("guru")
    Call<List<Guru>> getGuruByUsername(@Query("username") String username);

    @GET("absenGuru")
    Call<List<AbsenGuru>> getAbsenByUsername(@Query("username") String username);

    @POST("absenGuru")
    Call<ResponseBody> absenGuru(@Body AbsenGuru absenGuru);

    @Multipart
    @POST("guru")
    Call<ResponseBody> tambahGuru(@Part MultipartBody.Part photo, @PartMap Map<String, RequestBody> text);

    @GET("siswa")
    Call<List<Siswa>> getSiswa();

    @GET("siswa")
    Call<List<SiswaAdmin>> getAllSiswa();

    @POST("siswa")
    Call<ResponseBody> tambahSiswa(@Body Siswa siswa);

    @GET("rekap")
    Call<List<Rekap>> getRekap();

    @GET("detailRekap")
    Call<List<Detail>> getAbsenByUsernameForRekap(@Query("username") String username);
}
