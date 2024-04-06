package com.example.merchandising2;

// Importaciones necesarias
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class adapter extends ArrayAdapter<productos> {

    // Declaración de variables miembro
    Context context;
    List<productos> arrayproductos;
    TextView productosText;
    TextView totalViewTxt;
    TextView ventasTxt;
    ArrayList<Integer> totales = new ArrayList<>();
    RequestQueue requestQueue;
    int total = 0;
    int ventas = 0;

    // Constructor de la clase
    public adapter(@NonNull Context context, List<productos> arrayproductos, TextView productosText, TextView totalViewTxt,TextView ventasTxt) {
        super(context, R.layout.list_productos, arrayproductos);
        this.context = context;
        this.arrayproductos = arrayproductos;
        this.productosText = productosText;
        this.totalViewTxt = totalViewTxt;
        this.requestQueue= Volley.newRequestQueue(context);
    }

    // Método getView para la visualización de los elementos en la lista
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_productos, parent, false);
        }

        // Obtención del producto en la posición dada
        productos producto = arrayproductos.get(position);

        // Obtención de los elementos de la interfaz de usuario
        Button ins = convertView.findViewById(R.id.insertarBt);
        Button venta = convertView.findViewById(R.id.ventaBt2);
        TextView textNom = convertView.findViewById(R.id.txt_Nomen);
        TextView textTip = convertView.findViewById(R.id.txt_Tipo);
        TextView textCant = convertView.findViewById(R.id.txt_Cant);
        TextView textPrec = convertView.findViewById(R.id.txt_Precio);

        // Asignación de los valores del producto a los elementos de la interfaz de usuario
        textNom.setText(producto.getNomenclatura());
        textTip.setText(producto.getTipo());
        textCant.setText(producto.getCantidad());
        textPrec.setText(producto.getPrecio());

        // Manejo del evento click en el botón de inserción
        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtención de los valores de nombre, tipo, precio y cantidad
                String nomen = textNom.getText().toString();
                String tipo = textTip.getText().toString();
                String precio = textPrec.getText().toString();
                String cantidad = textCant.getText().toString();

                // Concatenación de los valores y establecimiento del texto en productosText
                String productosTextValue = nomen + " " + tipo + " " + precio + "\n";
                productosText.setText(productosTextValue);

                // Actualización de la cantidad y el precio total
                producto.setNomenclatura(nomen);
                int cantid = Integer.parseInt(cantidad);
                cantid = cantid - 1;
                cantidad = Integer.toString(cantid);
                textCant.setText(cantidad);
                producto.setCantidad(cantidad);
                actualizarCantBd(producto);
                if (!precio.isEmpty() && precio.matches("\\d+")) {
                    int prec = Integer.parseInt(precio);
                    total += prec;
                    totalViewTxt.setText(String.valueOf(total));
                }
            }
        });

        return convertView;
    }

    // Método para actualizar la cantidad en la base de datos
    private void actualizarCantBd(productos producto) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://marceweb.eu/updateCantidad.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Respuesta exitosa
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Error en la respuesta
                        Log.e("Volley", "Error: " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Nomenclatura", producto.getNomenclatura());
                params.put("Cantidad", producto.getCantidad());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
