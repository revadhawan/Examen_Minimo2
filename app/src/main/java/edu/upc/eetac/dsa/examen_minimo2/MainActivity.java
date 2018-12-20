package edu.upc.eetac.dsa.examen_minimo2;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private DibaAPI createAPIRest;
    private CitiesRecyclerViewAdapter recycler;
    private RecyclerView recyclerView;
    ProgressBar progressBar;

    TextView municipi;
    TextView id;
    ImageView ImageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recycler = new CitiesRecyclerViewAdapter(this);
        recyclerView.setAdapter(recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        municipi = findViewById(R.id.municipiNom_textView);
        id = findViewById(R.id.id_textView);
        ImageURL = findViewById(R.id.escut);

        Intent intent = getIntent();

        createAPIRest = DibaAPI.createAPIRest();

        getData();

    }


    private void getData() {

        Call<Cities> callCitiesList = createAPIRest.getData();

        callCitiesList.enqueue(new Callback<Cities>() {
            @Override
            public void onResponse(Call<Cities> call, Response<Cities> response) {
                if (response.isSuccessful()) {
                    Cities cities = response.body();

                    List<Element> elementList = cities.getElements();

                    for(int i = 0; i<elementList.size(); i++){
                        Log.i("Name city: " + elementList.get(i).getMunicipiNom(), response.message());
                    }

                    if(elementList.size() != 0){
                        recycler.addElements(elementList);
                    }
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

}
