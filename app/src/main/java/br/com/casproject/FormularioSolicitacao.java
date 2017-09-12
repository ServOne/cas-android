package br.com.casproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InterruptedIOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.casproject.adapter.SubServicoAdapter;
import br.com.casproject.converter.ServicoConverter;
import br.com.casproject.converter.SolicitacaoConverter;
import br.com.casproject.converter.SubServicoConverter;
import br.com.casproject.converter.UsuarioConverter;
import br.com.casproject.modelo.Servico;
import br.com.casproject.modelo.Solicitacao;
import br.com.casproject.modelo.SubServico;
import br.com.casproject.modelo.Usuario;
import br.com.casproject.repository.SessionRepository;
import br.com.casproject.task.TaskGenerica;

public class FormularioSolicitacao extends AppCompatActivity implements TaskGenerica.TaskGenericaResultado{

    private Spinner spn_servico;
    private Spinner spn_subservico;
    private Spinner spn_impacto;
    private Spinner spn_urgencia;
    private List<SubServico> subServicosGeral = new ArrayList<>();
    private List<SubServico> subServicosSpinner = new ArrayList<>();
    private List<Servico> servicos = new ArrayList<>();
    private List<String> impactos = new ArrayList<>();
    private List<String> urgencias = new ArrayList<>();
    private int posicaoSubServicos;
    private Servico servico = null;
    private SubServico subservico = null;
    private EditText txtTitulo;
    private EditText txtDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_solicitacao);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        //getSupportActionBar().setTitle("Seu titulo aqui");     //Titulo para ser exibido na sua Action Bar em frente à seta

        this.impactos.add("Um departamento");
        this.impactos.add("Um serviço");
        this.impactos.add("Uma pessoa");

        this.urgencias.add("Crítica");
        this.urgencias.add("Alta");
        this.urgencias.add("Média");
        this.urgencias.add("Baixa");

        Intent intent = getIntent();
        this.subServicosGeral = (List<SubServico>) intent.getSerializableExtra("subServicos");
        this.servicos = (List<Servico>) intent.getSerializableExtra("servicos");
        this.posicaoSubServicos = (int) intent.getSerializableExtra("posicao");

        this.servico = this.servicos.get(getPosicaoServico());

        //Identifica o Spinner no layout
        this.spn_servico = (Spinner) findViewById(R.id.spinner_servico);
        this.spn_subservico = (Spinner) findViewById(R.id.spinner_subservico);
        this.spn_impacto = (Spinner) findViewById(R.id.spinner_impacto);
        this.spn_urgencia = (Spinner) findViewById(R.id.spinner_urgencia);
        this.txtTitulo = (EditText) findViewById(R.id.id_edittexto_titulo);
        this.txtDescricao = (EditText) findViewById(R.id.id_editText_descricao);

        ArrayAdapter spinnerArrayAdapterServico = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.servicos);
        this.spn_servico.setAdapter(spinnerArrayAdapterServico);
        this.spn_servico.setSelection(getPosicaoServico());

        gerarSubServicoSpinner();

        ArrayAdapter spinnerArrayAdapterImpacto = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.impactos);
        this.spn_impacto.setAdapter(spinnerArrayAdapterImpacto);

        ArrayAdapter spinnerArrayAdapterUrgencia = new ArrayAdapter(this, android.R.layout.simple_list_item_1 , this.urgencias);
        this.spn_urgencia.setAdapter(spinnerArrayAdapterUrgencia);

        //Método do Spinner para capturar o item selecionado
        spn_servico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                //pega nome pela posição
                servico = (Servico) parent.getSelectedItem();

                gerarSubServicoSpinner();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                try {
                    salvarSolicitacao();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case android.R.id.home:
                finish();
                break;
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void gerarSubServicoSpinner() {
        this.subServicosSpinner.clear();
        for (SubServico s : this.subServicosGeral) {
            if (Integer.parseInt(s.getService_id()) == Integer.parseInt(this.servico.getId())) {
                this.subServicosSpinner.add(s);
            }
        }

        ArrayAdapter spinnerArrayAdapterSubServico = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.subServicosSpinner);
        this.spn_subservico.setAdapter(spinnerArrayAdapterSubServico);
        this.spn_subservico.setSelection(this.subServicosSpinner.indexOf(this.subServicosGeral.get(this.posicaoSubServicos)));
    }

    public int getPosicaoServico() {
        int posicao = 0;
        for (int i = 0; i < this.servicos.size(); i++) {
            if (Integer.parseInt(this.servicos.get(i).getId()) == Integer.parseInt(this.subServicosGeral.get(this.posicaoSubServicos).getService_id())) {
                posicao = i;
            }
        }

        return posicao;
    }

    public void salvarSolicitacao() throws JSONException {

        if(this.txtTitulo == null || this.txtTitulo.getText().toString().equals("")) {
            this.txtTitulo.setError("Preencha o campo");
            return;
        }

        if(this.txtDescricao == null || this.txtDescricao.getText().toString().equals("")) {
            this.txtDescricao.setError("Preencha o campo");
            return;
        }

        this.subservico = (SubServico) this.spn_subservico.getSelectedItem();

        JSONObject jsd = new JSONObject();
        jsd.put("org_id", SessionRepository.getUsuario().getOrg_id());
        jsd.put("caller_id", SessionRepository.getUsuario().getID());
        jsd.put("title", this.txtTitulo.getText());
        jsd.put("description", this.txtDescricao.getText());
        jsd.put("request_type", this.subservico.getRequest_type());
        jsd.put("impact", String.valueOf((this.spn_impacto.getSelectedItemId() + 1)));
        jsd.put("urgency", String.valueOf((this.spn_urgencia.getSelectedItemId() + 1)));
        jsd.put("service_id", this.servico.getId());
        jsd.put("servicesubcategory_id", this.subservico.getId());

        TaskGenerica reqServer = new TaskGenerica(FormularioSolicitacao.this);
        reqServer.execute("core/create", "UserRequest", "", jsd.toString(), "");

/*
        try {

            List<Solicitacao> solicitacoes = SolicitacaoConverter.converterJson(reqServer.get());

//            Log.e("DIEGO",reqServer.get());

            MainActivity.solicitacoesAbertas.add(solicitacoes.get(0));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
*/

    }

    @Override
    public void onResultado(String resultado, String classResultado) {

        if (classResultado.equals("UserRequest")) {
            List<Solicitacao> solicitacoes = SolicitacaoConverter.converterJson(resultado);
            MainActivity.solicitacoesAbertas.add(solicitacoes.get(0));

            Toast.makeText(FormularioSolicitacao.this, "Solicitacao salva!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}
