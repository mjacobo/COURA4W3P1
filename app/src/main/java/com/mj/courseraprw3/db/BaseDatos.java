package com.mj.courseraprw3.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mj.courseraprw3.pojo.pets;

import java.util.ArrayList;

/**
 * Created by leyenda1 on 01/10/2016.
 */

public class BaseDatos extends SQLiteOpenHelper {
    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBD.DATABASE_NAME, null, ConstantesBD.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateTablaPets = " CREATE TABLE " + ConstantesBD.TABLE_PETS + "( " +
                                        ConstantesBD.TABLE_PETS_ID        +  " INTEGER PRIMARY KEY,  " +
                                        ConstantesBD.TABLE_PETS_NAME       +  " TEXT, "     +
                                        ConstantesBD.TABLE_PETS_PICTURE   +  " INTEGER, "  +
                                        ConstantesBD.TABLE_PETS_TIMESTAMP +  " DATETIME DEFAULT CURRENT_TIMESTAMP " +
                                        " )";

        String queryCreateTablaLikes = " CREATE TABLE " + ConstantesBD.TABLE_PETS_LIKES + "(" +
                                       ConstantesBD.TABLE_PETS_LIKES_ID           + " INTEGER PRIMARY KEY AUTOINCREMENT,  " +
                                       ConstantesBD.TABLE_PETS_LIKES_ID_PETS      + " INTEGER, " +
                                       ConstantesBD.TABLE_PETS_LIKES_NUMERO_LIKES + " INTEGER, " +
                                       " FOREIGN KEY ( " + ConstantesBD.TABLE_PETS_LIKES_ID_PETS  + ") " +
                                       " REFERENCES " + ConstantesBD.TABLE_PETS + "( " + ConstantesBD.TABLE_PETS_ID + " ) ON DELETE CASCADE" +
                                       " )";

        db.execSQL(queryCreateTablaPets);
        db.execSQL(queryCreateTablaLikes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXIST " + ConstantesBD.TABLE_PETS_LIKES);
        db.execSQL(" DROP TABLE IF EXIST " + ConstantesBD.TABLE_PETS);
        onCreate(db);
    }

    public ArrayList<pets> getMyPetList(){
        ArrayList<pets> Pets = new ArrayList<>();

        String query = " SELECT * FROM " + ConstantesBD.TABLE_PETS + " ORDER BY " + ConstantesBD.TABLE_PETS_TIMESTAMP + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor rows = db.rawQuery(query, null);

        while (rows.moveToNext()) {
            pets currentPet = new pets();

            currentPet.setId(String.valueOf(rows.getInt(0)));
            currentPet.setNombreCompleto(rows.getString(1));
            currentPet.setURLpicture(String.valueOf(rows.getInt(2)));
            currentPet.setLikes(getLikesPet(currentPet));

            Pets.add(currentPet);
        }

        db.close();
        return Pets;
    }

    public void insertLikePet(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBD.TABLE_PETS_LIKES, null, contentValues);
        db.close();
    }

    public void insertPet(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBD.TABLE_PETS, null, contentValues);
        db.close();
    }

    public void deletePet(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ConstantesBD.TABLE_PETS, ConstantesBD.TABLE_PETS_ID + "=" + String.valueOf(id), null);
        db.close();
    }

    public void deletePetLikes(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ConstantesBD.TABLE_PETS_LIKES, ConstantesBD.TABLE_PETS_LIKES_ID_PETS + "=" + String.valueOf(id), null);
        db.close();
    }

    public int getLikesPet(pets Pet){
        int likes = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " SELECT COUNT ( " + ConstantesBD.TABLE_PETS_LIKES_NUMERO_LIKES +" )" +
                       " FROM " + ConstantesBD.TABLE_PETS_LIKES +
                       " WHERE "+ ConstantesBD.TABLE_PETS_LIKES_ID_PETS + " = " + Pet.getId();
        Cursor rows  = db.rawQuery(query, null);

        if (rows.moveToNext()){
            likes = rows.getInt(0);
        }
        db.close();

        return  likes;
    }
}
