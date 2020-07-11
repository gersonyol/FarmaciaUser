package com.example.ejerciciofarmaciauser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    private FragmentTransaction fragmentTransaction;
    private Fragment fr1, fr2;

    private String conocenos = "Somos un conjuto de instituciones y empresas comerciales encaminadas a construir una nacion mas justa para todos. En la actualidad contamos con mas de 4 empresas de caracter social y 4 de tipo comercial. ";

    private String shop1 = "Centro, Reforma";
    private String shop2 = "Sucursal, Cayala";
    private String shop3 = "Sucursal, Norte";
    private String shop4 = "Sucursal, Mixco";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        fr1 = new ConocenosFragment();
        fr2 = new UbicacionesFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFr,fr2).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFr,fr1).commit();

        this.datosFragment2();
        this.datosFragment1();

    }

    public void datosFragment1(){
        Bundle bundleDatos = new Bundle();
        bundleDatos.putString("conocenos", conocenos);
        fr1.setArguments(bundleDatos);
    }

    public void datosFragment2(){
        Bundle bundleDatos = new Bundle();
        bundleDatos.putString("tienda1", shop1);
        bundleDatos.putString("tienda2", shop2);
        bundleDatos.putString("tienda3", shop3);
        bundleDatos.putString("tienda4", shop4);
        fr2.setArguments(bundleDatos);
    }

    public void onclick(View view) {

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {

            case R.id.btnSinopsis:
                if (fr1.isAdded()){
                    fragmentTransaction.hide(fr2).show(fr1);
                }else{
                    fragmentTransaction.hide(fr2).add(R.id.contenedorFr, fr1);
                }
                break;
            case R.id.btnDirectores:
                if (fr2.isAdded()){
                    fragmentTransaction.hide(fr1).show(fr2);
                }else{
                    fragmentTransaction.hide(fr1).add(R.id.contenedorFr, fr2);
                }
                break;
            case R.id.btnRegresar:
                this.finish();
                break;

        }

        fragmentTransaction.commit();

    }
}
