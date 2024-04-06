package com.example.merchandising2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class stockVisual extends AppCompatActivity {

    // Declaración de variables miembro
    ListView listView;
    adapter adapter;
    RequestQueue requestQueue;
    Button ventaBt2;
    TextView productosText, totalViewTxt, ventasTxt;
    public static ArrayList<productos> productosArrayList = new ArrayList<>();
    public static ArrayList<productos> prodarray = new ArrayList<>();
    String url = "http://marceweb.eu/listardatos.php";
    int total = 0;
    int venta = 0;
    productos productos;

    // Método llamado al crear la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_visual);

        // Inicialización de vistas y adaptador
        ventaBt2 = findViewById(R.id.ventaBt2);
        listView = findViewById(R.id.lista);
        productosText = findViewById(R.id.productosText);
        totalViewTxt = findViewById(R.id.totalViewTxt);
        ventaBt2 = findViewById(R.id.ventaBt2);
        ventasTxt = findViewById(R.id.ventasTxt);
        adapter = new adapter(this, productosArrayList, productosText, totalViewTxt, ventasTxt);
        listView.setAdapter(adapter);

        // Llamada al método para listar los datos
        listarDatos();

        // Manejo del evento clic para el botón de venta
        ventaBt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reiniciar el total y actualizar el contador de ventas
                totalViewTxt.setText("0");
                productosText.setText("");

                venta++;
                String vent = Integer.toString(venta);
                ventasTxt.setText(vent);
            }
        });
    }

    // Método para listar los datos desde el servidor
    private void listarDatos() {
        // Crear una solicitud HTTP para obtener los datos
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Limpiar la lista de productos
                productosArrayList.clear();
                try {
                    // Procesar la respuesta JSON
                    JSONObject jsonObject = new JSONObject(response);
                    String exito = jsonObject.getString("exito");
                    JSONArray jsonArray = jsonObject.getJSONArray("datos");
                    // Verificar si la solicitud fue exitosa
                    if (exito.equals("1")) {
                        // Recorrer los datos obtenidos y agregarlos a la lista de productos
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String nom = object.getString("Nomenclatura");
                            String tip = object.getString("Tipo");
                            String cant = object.getString("Cantidad");
                            String pre = object.getString("Precio");
                            productos = new productos(nom, tip, cant, pre);
                            productosArrayList.add(productos);
                            adapter.notifyDataSetChanged();
                        }
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }, null);
        // Agregar la solicitud a la cola de solicitudes
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(request);
    }
}

