package android.application.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.application.loginfirebase.modelo.Usuario;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//import com.example.sistema.modelo.Usuario;
//import com.google.firebase.auth.FirebaseUser;


public class CadastroActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etEmail;
    private EditText etSenha;
    private Switch swProfessor;
    private Button btCadastrar;
    private FirebaseAuth mAuth;
    private Usuario u;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        swProfessor = findViewById(R.id.swProfessor);
        btCadastrar = findViewById(R.id.btCadastrar);
        mAuth = FirebaseAuth.getInstance();

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarDados();
                criarLogin();
            }
        });
    }

    private void criarLogin() {
        mAuth.createUserWithEmailAndPassword(u.getEmail(),u.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            u.setId(user.getUid());
                            u.salvarDados();
                            startActivity(new Intent(CadastroActivity.this,PrincipalActivity.class));
                        }else{
                            Toast.makeText(CadastroActivity.this, "Erro ao criar login", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void recuperarDados() {
        if(etNome.getText().toString()==""||etEmail.getText().toString()==""||etSenha.getText().toString()==""){
            Toast.makeText(this, "Você de prencher todos os dados", Toast.LENGTH_LONG);
        }else{
            u = new Usuario();
            u.setNome(etNome.getText().toString());
            u.setEmail(etEmail.getText().toString());
            u.setSenha(etSenha.getText().toString());
            u.setProfessor(swProfessor.getShowText());
            if(swProfessor.isChecked()){
                u.setProfessor(true);
            }else{
                //u.set.Proessor(false);
            }
        }
    }
}