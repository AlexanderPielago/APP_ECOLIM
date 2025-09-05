package Vista;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecolim_app.R;

public class RecordView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Est le indica a Android qué layout user
        setContentView(R.layout.recordform);
    }
}

