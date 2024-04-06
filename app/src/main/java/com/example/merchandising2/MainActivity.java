package com.example.merchandising2;

// Importaciones necesarias
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

// Clase principal de la actividad principal
public class MainActivity extends AppCompatActivity {

    // Método llamado al crear la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Establecer el layout de la actividad
        setContentView(R.layout.activity_main);
        // Obtener el FragmentManager
        FragmentManager fm = getSupportFragmentManager();
        // Iniciar una transacción y reemplazar el contenido del contenedor con el Fragment de inicio de sesión
        fm.beginTransaction().replace(R.id.escenario,new loginFragment()).commit();
    }
}
