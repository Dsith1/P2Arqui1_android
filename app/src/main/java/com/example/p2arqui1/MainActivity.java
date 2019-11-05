package com.example.p2arqui1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    int correlativo=0;
    EditText txtentrada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtentrada=(EditText)findViewById(R.id.txtTexto);

        Button btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String entrada=txtentrada.getText().toString();
                Envio(entrada);
            }
        });
    }



    private void Envio(String cadena){
        Retrofit retrofit = new Builder()
                .baseUrl(Servicio.ruta_api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicio postApi= retrofit.create(Servicio.class);
        Call<RequestBody> call = postApi.uploadFile(cadena,correlativo);


        call.enqueue(new Callback<RequestBody>() {
            @Override
            public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                response.body();
                Log.d("good", "good");
                Log.d("goog",response.raw().toString());

            }
            @Override
            public void onFailure(Call<RequestBody> call, Throwable t) {
                Log.d("fail", "fail");
                Log.d("fail",t.getMessage());
            }
        });

    }

}
