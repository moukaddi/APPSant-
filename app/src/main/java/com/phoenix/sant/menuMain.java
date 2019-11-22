package com.phoenix.sant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class menuMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);
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
