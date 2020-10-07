package com.example.zaebi11rpl022019;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ModelMovieRealm extends RealmObject {

   @PrimaryKey
   private Integer id;
   private String judul;
   private String description;
   private String releaseDate;
   private String path;

   public Integer getId()
   {
       return id;
   }

   public void setId(Integer id)
   {
       this.id = id;
   }

   public String getJudul()
   {
       return judul;
   }

   public void setJudul(String judul)
   {
        this.judul = judul;
   }

   public String getDescription()
   {
       return description;
   }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getReleaseDate()
    {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }
}
