package com.example.merchandising2;

// Importaciones necesarias
import static com.android.volley.Response.*;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.merchandising2.R;
import com.example.merchandising2.stockVisual;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

// Definición del Fragment para el inicio de sesión
public class loginFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    RequestQueue rq;
    JsonObjectRequest jrq;
    EditText nom, pas;
    Button envio;

    // Método llamado al crear la vista del Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflar el layout del Fragment
        View vista = inflater.inflate(R.layout.fragment_login, container, false);
        // Obtener referencias de los elementos de la interfaz de usuario
        nom = (EditText) vista.findViewById(R.id.loginText);
        pas = (EditText) vista.findViewById(R.id.passText);
        envio = (Button) vista.findViewById(R.id.btEntrar);
        rq = Volley.newRequestQueue(getContext());

        // Establecer el evento clic para el botón de enviar
        envio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });

        return vista; // Devolver la vista del Fragment
    }

    // Método llamado cuando hay un error en la respuesta de la solicitud HTTP
    @Override
    public void onErrorResponse(VolleyError error) {
        // Mostrar un mensaje de error al usuario
        Toast.makeText(getContext(), "No se ha encontrado el usuario: " + error.getMessage(), Toast.LENGTH_LONG).show();
        // Registrar el error en los logs
        Log.w("response", error.toString());
        Log.e("response",error.toString());
        Log.i("response",error.toString());
    }

    // Método llamado cuando se recibe una respuesta exitosa de la solicitud HTTP
    @Override
    public void onResponse(JSONObject response) {
        // Crear un objeto usuario
        user usuario = new user();
        // Mostrar un mensaje al usuario
        Toast.makeText(getContext(), "Se ha encontrado el usuario" + nom.getText().toString(), Toast.LENGTH_SHORT).show();

        // Obtener el JSONArray "datos" de la respuesta JSON
        JSONArray jsonarray = response.optJSONArray("datos");
        JSONObject jsonObjectson = null;

        try {
            // Obtener el primer objeto JSON del JSONArray
            jsonObjectson = jsonarray.getJSONObject(0);
            // Asignar los valores del objeto JSON al objeto usuario
            usuario.setUser(jsonObjectson.optString("user"));
            usuario.setPwd(jsonObjectson.optString("pwd"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Iniciar la actividad stockVisual
        Intent intent = new Intent(getContext(), stockVisual.class);
        startActivity(intent);
    }

    // Método para iniciar sesión
    private void iniciarSesion() {
        // Construir la URL para la solicitud HTTP
        String url = "http://marceweb.eu/login.php?user=" + nom.getText().toString() + "&pwd=" + pas.getText().toString();
        // Crear la solicitud JSON con el método GET
        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        // Añadir la solicitud a la cola de solicitudes
        rq.add(jrq);
    }
}
