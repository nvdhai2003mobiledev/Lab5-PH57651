package com.example.demo5;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText txt1, txt2, txt3;
    TextView tvKq;
    Button btn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        tvKq = findViewById(R.id.tvKq);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(v -> {
            insetData(txt1, txt2, txt3, tvKq);
        });
    }

    private void insetData(EditText txt1, EditText txt2, EditText txt3, TextView tvKq) {
        SanPham s = new SanPham(txt1.getText().toString(),
                txt2.getText().toString(),
                txt3.getText().toString());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.24.4.150/000/2024071/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceInsertSanPham insertSanPham = retrofit.create(InterfaceInsertSanPham.class);
        Call<SvrResponseSanPham> call = insertSanPham.insertSanPham(s.getMaSP(), s.getTenSP(), s.getMoTa());
        call.enqueue(new Callback<SvrResponseSanPham>() {
            @Override
            public void onResponse(Call<SvrResponseSanPham> call, Response<SvrResponseSanPham> response) {
                SvrResponseSanPham res =  response.body();
                tvKq.setText(res.getMessage());
            }

            @Override
            public void onFailure(Call<SvrResponseSanPham> call, Throwable t) {
                tvKq.setText("Lỗi: " + t.getMessage());
            }
        });
    }
}