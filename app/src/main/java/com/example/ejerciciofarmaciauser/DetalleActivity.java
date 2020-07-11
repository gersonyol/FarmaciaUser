package com.example.ejerciciofarmaciauser;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ejerciciofarmaciauser.constantes.Conexion;

import org.json.JSONObject;

import java.util.ArrayList;

public class DetalleActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    private TextView otxtNombreM, otxtPrecio, otxtFecha, otxtTotal;
    private EditText itxtNombreC, itxtNit;
    private Spinner spinnerC;

    private String idM, nombreM, precioM, fechaM, cantidadM, nombreC, nit;

    private int sel, cantidadA, c;
    private double total;

    ArrayList<Integer> arrayCantidad = new ArrayList<>();

    Modificar objModificar = new Modificar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        otxtNombreM = findViewById(R.id.txtNombreMedD);
        otxtPrecio = findViewById(R.id.txtPrecioMedD);
        otxtFecha = findViewById(R.id.txtFechaMedD);
        itxtNombreC = findViewById(R.id.txtNombreC_D);
        itxtNit = findViewById(R.id.txtNitD);
        otxtTotal = findViewById(R.id.txtTotal);
        spinnerC = findViewById(R.id.spinnerId);

        this.obtenerDatos();
        this.cargarDatos();
        this.llenarArray();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrayCantidad);
        spinnerC.setAdapter(adapter);

        spinnerC.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        if(position != 0) {

                            sel = (int) parent.getItemAtPosition(position);
                            total = Double.parseDouble(precioM) * sel;
                            otxtTotal.setText(Double.valueOf(total).toString());

                        }else{
                           otxtTotal.setText("");
                        }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void obtenerDatos(){
        Bundle bundle = getIntent().getExtras();
        idM = bundle.getString("idM");
        nombreM = bundle.getString("nombre");
        cantidadM = bundle.getString("cantidad");
        precioM = bundle.getString("precio");
        fechaM = bundle.getString("fecha");
    }

    private void cargarDatos(){
        otxtNombreM.setText(nombreM);
        otxtPrecio.setText(precioM);
        otxtFecha.setText(fechaM);
    }

    private void llenarArray(){

        c = Integer.parseInt(cantidadM);

        if (c != 0) {
            for (int i = 0; i <= c; i++) {
                arrayCantidad.add(i);
                Toast.makeText(this, "Existencia total: "+c, Toast.LENGTH_SHORT).show();
            }
        }else{
            arrayCantidad.add(null);
            Toast.makeText(this, "No hay existencia", Toast.LENGTH_SHORT).show();
        }
    }

     private void mensajeCompra() {

        if (!itxtNombreC.getText().toString().isEmpty() && !itxtNit.getText().toString().isEmpty()) {

            if (sel != 0) {

                nombreC = itxtNombreC.getText().toString();
                nit = itxtNit.getText().toString();

                AlertDialog.Builder alerta = new AlertDialog.Builder(DetalleActivity.this);
                alerta.setTitle("      ¡¡Compra Exitosa!!")
                        .setMessage("Cliente: " + nombreC + "     Nit: " + nit)
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                Intent intent = new Intent(getApplicationContext(), ProductoActivity.class);
                                startActivity(intent);
                            }
                        })
                        .show();

            }else{
                Toast.makeText(this, "Elija una cantidad", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Datos no ingresados", Toast.LENGTH_SHORT).show();
        }

     }

     private void consultaModificar(){
            if (idM != null && nombreM != null && cantidadM != null && precioM != null && fechaM != null && nombreC != null && nit != null) {

                cantidadA = Integer.parseInt(cantidadM) - sel;

                this.objModificar.modificar(getApplicationContext(), idM, nombreM, cantidadA, precioM, fechaM);
            }else{
                Toast.makeText(this, "Datos no ingresados", Toast.LENGTH_SHORT).show();
            }
     }

    public void onclickD(View view) {

        switch (view.getId()){
            case R.id.btnComprarD:
                this.mensajeCompra();
                this.consultaModificar();
                break;
            case R.id.btnCancelarD:
                    this.finish();
                Intent intent = new Intent(getApplicationContext(), ProductoActivity.class);
                startActivity(intent);
                break;
        }

    }


}
