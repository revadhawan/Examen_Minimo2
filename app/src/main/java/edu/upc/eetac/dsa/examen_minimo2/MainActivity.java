package edu.upc.eetac.dsa.examen_minimo2;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private DibaAPI dibaAPI;
    private RecyclerView recyclerView;
    ProgressBar progressBar;
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        createdibaAPI();
        getCities();
    }


    private void createdibaAPI(){
        Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(dibaAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        dibaAPI = retrofit.create(DibaAPI.class);
    }


    private void getCities() {
        /*DibaAPI dibaAPI = dibaAPI;*/

        showProgress(true);
        Call<Cities> callCitiesList = dibaAPI.cities("1", "11");
        callCitiesList.enqueue(new Callback<Cities>() {
            @Override
            public void onResponse(Call<Cities> call, Response<Cities> response) {
                int statusCode = response.code();
                if (response.isSuccessful()) {
                    Cities cities = response.body();
                    Object mListener;
                    recyclerView.setAdapter(new CitiesRecyclerViewAdapter(cities.getElements()));
                    showProgress(false);
                }
            }

            @Override
            public void onFailure(Call<Cities> call, Throwable t) {
                Log.e("No api connection: ", t.getMessage());


                //Show the alert dialog
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                alertDialogBuilder
                        .setTitle("Error")
                        .setMessage(t.getMessage())
                        .setCancelable(false)
                        .setPositiveButton("OK", (dialog, which) -> {
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }

    private void showProgress(boolean b) {
    }
}
