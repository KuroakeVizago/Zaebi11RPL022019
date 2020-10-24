package com.example.zaebi11rpl022019;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ListDataFavourite extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    private RecyclerView recyclerView;
    private DataAdapterFavourite adapter;
    private List<ModelMovieRealm> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_favorite);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_favorite);
        arrayList = new ArrayList<>();
        // Setup Realm
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);
        arrayList = realmHelper.getAllMovie();
        adapter = new DataAdapterFavourite(arrayList, new DataAdapterFavourite.Callback() {
            @Override
            public void onClick(int position) {
                Intent move = new Intent(getApplicationContext(), detail_favourite.class);
                move.putExtra("judul",arrayList.get(position).getJudul());
                move.putExtra("path",arrayList.get(position).getPath());
                move.putExtra("date",arrayList.get(position).getReleaseDate());
                move.putExtra("deskripsi",arrayList.get(position).getDescription());

                startActivity(move);
            }

            @Override
            public void test() {

            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListDataFavourite.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }


}