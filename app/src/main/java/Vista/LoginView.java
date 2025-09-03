package Vista;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.ecolim_app.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import DataBase.AppDatabase;
import Dao.UsuarioDao;
import Model.UsuarioModel;

public class LoginView extends AppCompatActivity {

    private TextInputEditText etUsername, etPassword;
    private MaterialButton btnLogin;
    private UsuarioDao usuarioDao;
    private ExecutorService executorService;

    private static final String TAG = "LoginView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginform);

        etUsername = findViewById(R.id.username_edit_text);
        etPassword = findViewById(R.id.password_edit_text);
        btnLogin   = findViewById(R.id.login_button);

        // Inicializa DB y DAO
        AppDatabase db = Room.databaseBuilder(
                        getApplicationContext(),
                        AppDatabase.class,
                        "ECOLIM.db")
                .createFromAsset("ECOLIM.db")   // usa tu BD pre-cargada
                .fallbackToDestructiveMigration()
                .build();

        usuarioDao = db.usuarioDao();
        executorService = Executors.newSingleThreadExecutor();

        btnLogin.setOnClickListener(v -> {
            String user = Objects.requireNonNull(etUsername.getText()).toString().trim();
            String pass = Objects.requireNonNull(etPassword.getText()).toString().trim();

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "⚠️ Complete los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            executorService.execute(() -> {
                try {
                    UsuarioModel u = usuarioDao.findByUsuario(user);

                    runOnUiThread(() -> {
                        if (u == null) {
                            Toast.makeText(this, "❌ Usuario no encontrado", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        String stored = safe(u.getPassword());       // lo que está guardado
                        String inputHash = hashPassword(pass);       // hash del input

                        // Logs de depuración (no dejes en producción)
                        Log.d(TAG, "stored=" + stored);
                        Log.d(TAG, "inputHash=" + inputHash);

                        boolean loginOk = false;

                        // 1. Comparar texto plano
                        if (stored.equals(pass)) {
                            loginOk = true;
                        }

                        // 2. Comparar como hash
                        else if (stored.equalsIgnoreCase(inputHash)) {
                            loginOk = true;
                        }

                        if (loginOk) {
                            Toast.makeText(this, "✅ Bienvenido " + u.getUsuario(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginView.this, MenuView.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(this, "❌ Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    Log.e(TAG, "Error en login", e);
                    runOnUiThread(() ->
                            Toast.makeText(this, "Ocurrió un error al validar", Toast.LENGTH_SHORT).show()
                    );
                }
            });
        });
    }

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
            return hexString.toString(); // en minúsculas
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String safe(String s) {
        return s == null ? "" : s.trim();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executorService != null) executorService.shutdown();
    }
}
