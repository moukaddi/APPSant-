package com.phoenix.sant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;


public class dbSante extends SQLiteOpenHelper {
    public  static final String nomdb="data.db";
    public dbSante(Context context) {
        super(context, nomdb, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table sante(id Integer PRIMARY KEY AUTOINCREMENT,taille Integer,poid Integer,age Integer,calcule real ,message Text,date Text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS sante");
        onCreate(db);
    }
    //insertion:
    public boolean insererDonnee (Integer taille , Integer poid, Integer age, String calcule, String message, String date)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();


        contentValues.put("taille",taille);
        contentValues.put("poid",poid);
        contentValues.put("age",age);
        contentValues.put("calcule",calcule);
        contentValues.put("message",message);
        contentValues.put("date",date);
        Long resultat =db.insert("sante",null,contentValues);
        if (resultat==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    //affichage:
    public ArrayList<SanteModel> getAllSante(){
        ArrayList <SanteModel> liste = new ArrayList();
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from sante",null);
        ((Cursor)res).moveToFirst();
        while (res.isAfterLast()==false)
        {
            SanteModel sm = new SanteModel();
            sm.setId( res.getString(0));
            sm.setTaille(res.getString(1));
            sm.setPoids( res.getString(2));
            sm.setAge(res.getString(3));
            sm.setCalcule( res.getString(4));
            sm.setMsg( res.getString(5));
            sm.setDate( res.getString(6));

            liste.add(sm);
            res.moveToNext();
        }
        return liste;
    }
    //affichage:
    public ArrayList getAll(){
        ArrayList liste = new ArrayList();
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from sante",null);
        ((Cursor)res).moveToFirst();
        while (res.isAfterLast()==false)
        {
            String id= res.getString(0);
            String taile= res.getString(1);
            String Poid= res.getString(2);
            String Age= res.getString(3);
            String calcl= res.getString(4);
            String msg= res.getString(5);
            String date= res.getString(6);
            liste.add(id+" "+taile+" "+Poid+" "+Age+" "+calcl+"\n "+msg+" "+date);
            res.moveToNext();
        }
        return liste;
    }
    //Modifier
    public boolean updateSante(Integer taille , Integer poid, Integer age, String calcule, String message, String date,String id)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("taille",taille);
        contentValues.put("poid",poid);
        contentValues.put("age",age);
        contentValues.put("calcule",calcule);
        contentValues.put("message",message);
        contentValues.put("date",date);
        Integer i = db.update("Sante",contentValues,"id=?",new String[]{id});
        if (i==0)
        {
            return false;
        }
        else
        {
            return  true;
        }
    }

    //Supprimer
    public Boolean supprimerSante (String id)
    {
        SQLiteDatabase db = getWritableDatabase();
        Integer i=db.delete("sante","id=?",new String[]{id});
        if (i==0)
        {
            return false;
        }
        else
        {
            return  true;
        }
    }
}
