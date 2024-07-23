package com.example.demo5.update;

import com.example.demo5.SvrResponseSanPham;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceUpdate {
    @FormUrlEncoded
    @POST("update.php")
    Call<SvrResponseSanPham> updateSanPham(
            @Field("MaSP") String MaSp,
             @Field("TenSP") String TenSP,
            @Field("MoTa") String MoTa
    );
}