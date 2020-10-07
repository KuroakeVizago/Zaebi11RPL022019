package com.example.zaebi11rpl022019;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MovieDetail extends AppCompatActivity {

    Realm realm;
    RealmHelper realmHelper;
    ModelMovieRealm movieModel;

    Bundle extras;
    String title;
    String date;
    String deskripsi;
    String path;
    String id;

    TextView tvjudul;
    ImageView ivposter;
    TextView tvdesc;
    Button btnbookmark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        extras = getIntent().getExtras();
        tvjudul = (TextView)findViewById(R.id.tv_Judul);
        tvdesc = (TextView)findViewById(R.id.txt_Description);
        ivposter = (ImageView) findViewById(R.id.iv_Poster);
        btnbookmark = (Button) findViewById(R.id.btn_Bookmark);

        if (extras != null) {
            title = extras.getString("judul");
            id = extras.getString("id");
            date = extras.getString("date");
            deskripsi = extras.getString("deskripsi");
            path = extras.getString("path");
            tvjudul.setText(title);
            tvdesc.setText(deskripsi);
            Glide.with(MovieDetail.this)
                    .load(path)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivposter);
            // and get whatever type user account id is
        }

        //Set up Realm
        Realm.init(MovieDetail.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);


        btnbookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieModel = new ModelMovieRealm();
                movieModel.setDescription(deskripsi);
                movieModel.setJudul(title);
                movieModel.setPath(path);
                movieModel.setReleaseDate(date);

                realmHelper = new RealmHelper(realm);
                realmHelper.save(movieModel);

            }
        });

    }
}
