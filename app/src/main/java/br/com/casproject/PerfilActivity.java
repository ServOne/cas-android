package br.com.casproject;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Base64;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.casproject.repository.SessionRepository;
import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilActivity extends AppCompatActivity {

    private CircleImageView fotoPerfil;
    private TextView nomePerfil;
    private TextView emailPerfil;
    private TextView telefonePerfil;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        //getSupportActionBar().setTitle("Seu titulo aqui");     //Titulo para ser exibido na sua Action Bar em frente à seta

        fotoPerfil = (CircleImageView) findViewById(R.id.item_foto);
        nomePerfil = (TextView) findViewById(R.id.nomePerfil);
        emailPerfil = (TextView) findViewById(R.id.emailPerfil);
        telefonePerfil = (TextView) findViewById(R.id.telefonePerfil);


        byte[] decodedString = Base64.decode(SessionRepository.getUsuario().getDatapicture(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        fotoPerfil.setImageBitmap(decodedByte);

        nomePerfil.setText(SessionRepository.getUsuario().getName().toUpperCase());
        emailPerfil.setText(SessionRepository.getUsuario().getEmail());
        telefonePerfil.setText(SessionRepository.getUsuario().getPhone());
        telefonePerfil.addTextChangedListener(new PhoneNumberFormattingTextWatcher("BR"));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}
