package Vista;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecolim_app.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;
import java.util.concurrent.Executors;

import DataBase.AppDatabase;
import DataBase.DatabaseClient;
import Model.UsuarioModel;

public class LoginView extends AppCompatActivity {

    private static final String TAG = "LoginView";

    private TextInputEditText etUsername, etPassword;
    private AppDatabase db;
    private MaterialButton btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 🔹 ELIMINAR DB ANTIGUA (solo para pruebas)
        deleteDatabase("db_ECOLIM");
        Log.d(TAG, "Base de datos antigua eliminada (si existía)");

        setContentView(R.layout.loginform);

        etUsername = findViewById(R.id.username_edit_text);
        etPassword = findViewById(R.id.password_edit_text);
        btnLogin = findViewById(R.id.login_button);

        // Deshabilitar botón hasta que DB esté lista
        btnLogin.setEnabled(false);

        // Obtener instancia de la base de datos
        db = DatabaseClient.getInstance(this).getAppDatabase();
        Log.d(TAG, "✅ Base de datos creada correctamente");

        // Crear usuario admin si no existe
        // Crear usuario admin si no existe
        Executors.newSingleThreadExecutor().execute(() -> {
            UsuarioModel admin = db.usuarioDao().obtenerPorNombre("admin");
            if (admin == null) {
                UsuarioModel nuevo = new UsuarioModel();
                nuevo.usuario = "admin";
                nuevo.password = hashPassword("1234");
                db.usuarioDao().insertarUsuario(nuevo);
                android.util.Log.d("LoginView", "Usuario 'admin' insertado correctamente");
            } else {
                android.util.Log.d("LoginView", "Usuario 'admin' ya existe");
            }

            // Listar todos los usuarios existentes en la DB
            List<UsuarioModel> usuarios = db.usuarioDao().obtenerTodosUsuarios();
            for (UsuarioModel u : usuarios) {
                android.util.Log.d("LoginView", "Usuario en DB: " + u.usuario + " | ID: " + u.id);
            }

            runOnUiThread(() -> {
                btnLogin.setEnabled(true);
                Toast.makeText(this, "✅ Base de datos lista", Toast.LENGTH_SHORT).show();
            });
        });


        // Botón login
        btnLogin.setOnClickListener(v -> {
            String user = etUsername.getText() != null ? etUsername.getText().toString().trim() : "";
            String pass = etPassword.getText() != null ? etPassword.getText().toString().trim() : "";

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "⚠️ Complete los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            Executors.newSingleThreadExecutor().execute(() -> {
                UsuarioModel u = db.usuarioDao().obtenerPorNombre(user);

                runOnUiThread(() -> {
                    if (u == null) {
                        Toast.makeText(this, "❌ Usuario no encontrado", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Intento de login fallido: usuario '" + user + "' no encontrado");
                    } else if (u.password.equalsIgnoreCase(hashPassword(pass))) {
                        Toast.makeText(this, "✅ Bienvenido " + u.usuario, Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Login exitoso para usuario: " + u.usuario);
                        startActivity(new Intent(LoginView.this, MenuView.class));
                        finish();
                    } else {
                        Toast.makeText(this, "❌ Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Login fallido para usuario: " + u.usuario + " (contraseña incorrecta)");
                    }
                });
            });
        });
    }

    // Hash SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
