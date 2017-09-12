package br.com.casproject;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.casproject.converter.UsuarioConverter;
import br.com.casproject.modelo.Usuario;
import br.com.casproject.repository.SessionRepository;
import br.com.casproject.task.TaskGenerica;

public class LoginActivity extends AppCompatActivity implements TaskGenerica.TaskGenericaResultado{

    private EditText mUsuario;
    private EditText mSenha;
    private Button btnLogin;
    private TaskGenerica reqServer;
    private Boolean btnClicado = false;
    private SessionRepository session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsuario = (EditText) findViewById(R.id.usuario_login);
        mSenha = (EditText) findViewById(R.id.senha_login);
        btnLogin = (Button) findViewById(R.id.btn_login);

        mUsuario.setText("admin");
        mSenha.setText("servone");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                if (!btnClicado) {
                    btnClicado = true;

                    session = new SessionRepository(mUsuario.getText().toString(), mSenha.getText().toString());
                    reqServer = new TaskGenerica(LoginActivity.this);
                    reqServer.execute("core/get", "Person", "SELECT Person AS p JOIN UserLocal AS u ON u.contactid = p.id " +
                            " WHERE u.login = " + "\"" + mUsuario.getText().toString() + "\"", "", "");

                    /*try {
                        Usuario usuario = UsuarioConverter.converterJson(reqServer.get());

                        if (usuario == null) {
                            btnClicado = false;
                            dialog.dismiss();
                            session.setNomeusuario(null);
                            session.setSenhausuario(null);
                            session.setUsuario(null);
                            Toast.makeText(LoginActivity.this, "Usuário não encontrado!", Toast.LENGTH_SHORT).show();
                        } else {
                            session.setUsuario(usuario);
                            Intent intentVaiProMainActivity = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intentVaiProMainActivity);
                            finish();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }*/
                }
            }
        });

    }

    @Override
    public void onResultado(String resultado, String classResultado) {

        if (classResultado.equals("Person")) {
            Usuario usuario = UsuarioConverter.converterJson(resultado);

            if (usuario == null) {
                btnClicado = false;
                session.setNomeusuario(null);
                session.setSenhausuario(null);
                session.setUsuario(null);
                Toast.makeText(LoginActivity.this, "Usuário não encontrado!", Toast.LENGTH_SHORT).show();
            } else {
                session.setUsuario(usuario);
                Intent intentVaiProMainActivity = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intentVaiProMainActivity);
                finish();
            }

        }
    }
}

