package com.example.zaebi11rpl022019;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListData extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private List<Model> DataArrayList; //kit add kan ke adapter
    private ImageView tambah_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        //addData();
        addDataOnline();
    }

    void addData() {
        DataArrayList = new ArrayList<>();
        Model data1 = new Model();
        data1.setOriginal_title("Judul Film");
        data1.setPoster_path("https://image.tmdb.org/t/p/w500/k68nPLbIST6NP96JmTxmZijEvCA.jpg");
        data1.setAdult(false);
        data1.setOverview("Deskripsi Film disini");
        data1.setVote_count(100);
        data1.setRelease_date("01-01-2020");
        DataArrayList.add(data1);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListData.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //get data online


    }

    void addDataOnline()
    {

        AndroidNetworking.get("https://api.themoviedb.org/3/movie/now_playing?api_key=75aaab083dad21f3018226d8863c2e0f")
            .setTag("test")
            .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("hasiljson", "onResponse: " + response.toString());

                        DataArrayList = new ArrayList<>();
                        Model jsonObjectModel;
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            Log.d("hasiljson2", "onResponse: " + jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                jsonObjectModel = new Model();

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                jsonObjectModel.setOriginal_title(jsonObject.getString("original_title"));
                                jsonObjectModel.setRelease_date(jsonObject.getString("release_date"));
                                jsonObjectModel.setOverview(jsonObject.getString("overview"));
                                jsonObjectModel.setAdult(jsonObject.getBoolean("adult"));
                                jsonObjectModel.setPoster_path("https://image.tmdb.org/t/p/w500" + jsonObject.getString("poster_path"));
                                jsonObjectModel.setVote_count(jsonObject.getInt("vote_count"));

                                DataArrayList.add(jsonObjectModel);

                            }
                            adapter = new DataAdapter(DataArrayList);

                            adapter.setOnItemClickListener(new DataAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    Model movie = DataArrayList.get(position);
                                    Intent intent = new Intent(getApplicationContext(), MovieDetail.class);
                                    intent.putExtra("id",movie.id);
                                    intent.putExtra("judul",movie.original_title);
                                    intent.putExtra("date",movie.release_date);
                                    intent.putExtra("deskripsi",movie.overview);
                                    intent.putExtra("path",movie.poster_path);
                                    startActivity(intent);
                                    Toast.makeText(ListData.this, ""+position, Toast.LENGTH_SHORT).show();
                                    Log.d("Test", "clicked");
                                }
                            });

                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListData.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {

                        Log.d("error", "OnError Error Code : " + anError.getErrorCode());
                        Log.d("error", "OnError Error Body : " + anError.getErrorBody());
                        Log.d("error", "OnError Error Detail : " + anError.getErrorDetail());

                    }
                });
    }

}
