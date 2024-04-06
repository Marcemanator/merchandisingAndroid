package com.example.merchandising2;

// Importaciones necesarias
import androidx.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

// Definición de la clase Request que extiende de StringRequest
public class Request extends StringRequest {

    // URL para la solicitud de inicio de sesión
    private static final String Login_Request_Url="http://localhost/login.php";

    // Mapa para almacenar los parámetros de la solicitud
    private Map<String,String> params;

    // Constructor de la clase
    public Request(String Nombre, String Pass, Response.Listener<String>listener){
        // Llama al constructor de la superclase StringRequest
        super(Method.POST,Login_Request_Url,listener,null);
        // Inicializa el mapa de parámetros
        params=new HashMap<>();
        // Agrega los parámetros de nombre de usuario y contraseña al mapa
        params.put("Nombre",Nombre);
        params.put("Pass",Pass);
    }

    // Método para obtener los parámetros de la solicitud
    @Nullable
    @Override
    public Map<String, String> getParams() {
        return params; // Devuelve el mapa de parámetros
    }
}
