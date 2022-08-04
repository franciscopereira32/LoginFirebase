package android.application.loginfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class CadastroActivity extends AppCompatActivity {

    private EditText edNome;
    private EditText edEmail;
    private EditText edSenha;
    private Switch swProfessor;
    private Button btCadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edNome = findViewById(R.id.edNome);
        edEmail = findViewById(R.id.edEmail);
        edSenha = findViewById(R.id.edSenha);
        swProfessor = findViewById(R.id.swProfessor);
        btCadastrar = findViewById(R.id.btCadastrar);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarDados();
            }
        });
    }

    private void recuperarDados() {

    }
}