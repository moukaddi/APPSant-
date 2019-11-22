package com.phoenix.sant;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity
{
private  dbSante db = new dbSante(this);
private RadioButton homme ,femme;
private EditText edtPoids,edtTaille,edtAge,edtid;
private Button Calculler;
private TextView txtResultat;
private ImageView img;
private Double resultat;
private String msg;
private String path;
private ArrayAdapter<CharSequence> Adapter;
   private String Taille,Resul;
   private String Poid;
   private String Age;
   private String date,id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homme=(RadioButton)findViewById(R.id.rdbHomme);
        femme=(RadioButton)findViewById(R.id.rdbFemme);
        edtPoids=(EditText)findViewById(R.id.edtPoids);
        edtTaille=(EditText)findViewById(R.id.edtTaille);
        edtAge=(EditText)findViewById(R.id.edtAge);
        edtid=(EditText)findViewById(R.id.edtid);
        Calculler=(Button)findViewById(R.id.btnCalculer);
        img=(ImageView)findViewById(R.id.ImageView);
        txtResultat=(TextView)findViewById(R.id.txtResultat);

        Bundle b = getIntent().getExtras();
        if(b!=null)
        {
            edtid.setText(b.getString("id"));
            edtTaille.setText(b.getString("taille"));
            edtPoids.setText(b.getString("poids"));
            edtAge.setText(b.getString("age"));
            String calc=b.getString("calc");
            msg=b.getString("msg");
            txtResultat.setText(calc+"\n"+msg);
            //Toast.makeText(getApplicationContext(),resultat+"",Toast.LENGTH_SHORT).show();
        }

        Calculler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if (edtPoids.getText().toString().equals("")||edtTaille.getText().toString().equals("")||edtAge.getText().toString().equals(""))
                {
                    return;
                }
                else
                {
                    Double Poids=Double.parseDouble(edtPoids.getText().toString());
                    Double Taille=Double.parseDouble(edtTaille.getText().toString())/100;
                    int Age=Integer.parseInt(edtAge.getText().toString());
                    if (homme.isChecked())
                    {
                        resultat=((1.2*(Poids/(Taille*Taille)))+(0.23*Age)-(10.83*1)-5.4);
                        if (resultat<10)
                        {
                            msg="Trop Maigre";
                            path="dead";
                        }
                        if (resultat>10 && resultat<25)
                        {
                            msg="Normal";
                            path="happiness";
                        }
                        if ( resultat>25)
                        {
                            msg="Trop de Graisse";
                            path="sad";
                        }
                    }
                    else
                        if (femme.isChecked())
                    {
                        resultat=((1.2*(Poids/(Taille*Taille)))+(0.23*Age)-(10.83*0)-5.4);
                        if (resultat<15)
                        {
                            msg="Trop Maigre";
                            path="dead";
                        }
                        if (resultat>15 && resultat<30)
                        {
                            msg="Normal";
                            path="happiness";
                        }
                        if ( resultat>30)
                        {
                            msg="Trop de Graisse";
                            path="sad";
                        }
                    }
                }
                if (homme.isChecked()==false&&femme.isChecked()==false)
                {
                    return;
                }
                DecimalFormat df = new DecimalFormat("0.00");
                Resul=df.format(resultat);
                txtResultat.setText(df.format(resultat)+"\n"+msg);
                int resId=getResources().getIdentifier(path,"drawable",getPackageName());
                img.setImageResource(resId);
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


    public void ajouterSnate(View view) {
        Taille=edtTaille.getText().toString();
        Poid=edtPoids.getText().toString();
        Age=edtAge.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        date= sdf.format(new Date());
        Boolean res =db.insererDonnee(Integer.parseInt(Taille),Integer.parseInt(Poid),Integer.parseInt(Age),Resul,msg,date);
        if (res==true)
        {
            Toast.makeText(MainActivity.this,"Donnees ajouter avec succes",Toast.LENGTH_LONG).show();
            initialiser();
            affiche();
        }
        else
        {
            Toast.makeText(MainActivity.this,"Probleme d'ajout,!",Toast.LENGTH_LONG).show();
        }
    }


    public void initialiser ()
    {
        edtPoids.setText("");
        edtTaille.setText("");
        edtAge.setText("");
    }

    public void modifierSante(View view) {
        Taille=edtTaille.getText().toString();
        Poid=edtPoids.getText().toString();
        Age=edtAge.getText().toString();
        id=edtid.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        date= sdf.format(new Date());
        final AlertDialog.Builder msgg = new AlertDialog.Builder(this);
        msgg.setTitle("Modification");
        msgg.setMessage("voulez vous vraiment le modifier ?? ");
        msgg.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean res= db.updateSante(Integer.parseInt(Taille),Integer.parseInt(Poid),Integer.parseInt(Age),Resul,msg,date,id);

                if (res==true)
                {
                    Toast.makeText(getApplicationContext(),"modification Succes",Toast.LENGTH_SHORT).show();
                    initialiser();
                    affiche();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Sante inexistence,!",Toast.LENGTH_LONG).show();
                }
            }
        });
        msgg.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"modification annuler",Toast.LENGTH_SHORT).show();
            }
        });
        msgg.show();//banding
    }

    public void afficherSante(View view) {
        affiche();
    }
    public void affiche() {
        Intent intent = new Intent(getApplicationContext(),AfficherSante.class);
        startActivity(intent);
    }
    public void SupprimerSante(View view) {
        id=edtid.getText().toString();
        AlertDialog.Builder msg = new AlertDialog.Builder(this);
        msg.setTitle("Suppression");
        msg.setMessage("voulez vous vraiment le supprimer ?? ");
        msg.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean res= db.supprimerSante(id);

                if (res==true)
                {
                    Toast.makeText(getApplicationContext(),"Suppression Succes",Toast.LENGTH_SHORT).show();                    initialiser();
                    affiche();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Sante inexistence,!",Toast.LENGTH_LONG).show();
                }
            }
        });
        msg.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Suppression anuller",Toast.LENGTH_SHORT).show();
            }
        });
        msg.show();
    }
}
