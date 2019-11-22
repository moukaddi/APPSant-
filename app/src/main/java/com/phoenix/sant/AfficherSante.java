package com.phoenix.sant;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.CharArrayReader;
import java.util.ArrayList;

public class AfficherSante extends AppCompatActivity {

     ArrayList<SanteModel> arrayList;
     ListView lstv;
     CustomAdapter customAdapter;
     dbSante db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_sante);

        lstv=(ListView)findViewById(R.id.lstv);
        db= new dbSante(this);
        //arrayList= new ArrayList<>();
        final ArrayList<SanteModel> arrayList=db.getAllSante();
        ArrayList lst = db.getAll();
        lstv.setAdapter(new CustomAdapter(this,arrayList));
        //ArrayAdapter<CharSequence> adapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,lst);
        //lstv.setAdapter(adapter);


        lstv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SanteModel sm=arrayList.get(position);
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                Bundle b = new Bundle();
                b.putString("id",sm.getId());
                b.putString("taille",sm.getTaille());
                b.putString("poids",sm.getPoids());
                b.putString("age",sm.getAge());
                b.putString("calc",sm.getCalcule());
                b.putString("msg",sm.getMsg());
                b.putString("date",sm.getDate());
                intent.putExtras(b);
               startActivity(intent);

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu men)
    {
        getMenuInflater().inflate(R.menu.menu,men);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        if (id==R.id.home)
        {
            Intent intent= new Intent(getApplicationContext(),menuMain.class);
            startActivity(intent);
        }
        if (id==R.id.afficher)
        {
            Intent intent= new Intent(getApplicationContext(),AfficherSante.class);
            startActivity(intent);
        }
        if (id==R.id.gerer)
        {
            Intent intent= new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
        if (id==R.id.close)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
