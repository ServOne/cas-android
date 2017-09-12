package br.com.casproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.casproject.adapter.TabsPagerAdapter;
import br.com.casproject.converter.ServicoConverter;
import br.com.casproject.converter.SolicitacaoConverter;
import br.com.casproject.converter.SubServicoConverter;
import br.com.casproject.modelo.Servico;
import br.com.casproject.modelo.Solicitacao;
import br.com.casproject.modelo.SubServico;
import br.com.casproject.repository.SessionRepository;
import br.com.casproject.task.TaskGenerica;


public class MainActivity extends AppCompatActivity {

    static List<Solicitacao> solicitacoesAbertas = new ArrayList<Solicitacao>();
    static List<Solicitacao> solicitacoesFechadas = new ArrayList<Solicitacao>();
    static List<SubServico> subServicos = new ArrayList<SubServico>();
    static List<Servico> servicos = new ArrayList<Servico>();
    private SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tabs
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        TabsPagerAdapter adapter = new TabsPagerAdapter(getSupportFragmentManager());

        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe_lista_solicitacoes);

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                buscaDadosWebService();
                if(swipe.isRefreshing()) swipe.setRefreshing(false);
            }
        });

        if(solicitacoesAbertas.size() == 0 && solicitacoesFechadas.size() ==0 && subServicos.size() ==0  && servicos.size() == 0)
            buscaDadosWebService();

    }



    public void buscaDadosWebService() {
        TaskGenerica reqServer = new TaskGenerica();
        TaskGenerica reqServer2 = new TaskGenerica();
        TaskGenerica reqServer3 = new TaskGenerica();

        reqServer.execute("core/get", "UserRequest", "SELECT UserRequest " +
                " WHERE caller_id = " + "\"" + SessionRepository.getUsuario().getID() + "\"", "", "");

        reqServer2.execute("core/get", "ServiceSubcategory", "SELECT ServiceSubcategory AS sc " +
                "   JOIN Service AS s ON sc.service_id = s.id " +
                "   JOIN lnkContactToService AS l1 ON l1.service_id=s.id " +
                " WHERE sc.request_type = " + "\"" + "service_request" + "\"" + " AND l1.contact_id = " +
                "\"" + SessionRepository.getUsuario().getID() + "\"", "", "id,name,description,service_id,service_name,request_type");

        reqServer3.execute("core/get", "Service", "SELECT Service AS s " +
                " JOIN lnkContactToService AS l1 ON l1.service_id=s.id " +
                " WHERE l1.contact_id = " + "\"" + SessionRepository.getUsuario().getID() + "\"", "", "id,name");

        try {


            List<Solicitacao> solicitacoes = SolicitacaoConverter.converterJson(reqServer.get());

            Log.e("DIEGO               ",reqServer.get());

            this.getSolicitacoesAbertas(solicitacoes);
            getSolicitacoesFechadas(solicitacoes);

            this.subServicos = SubServicoConverter.converterJson(reqServer2.get());

            this.servicos = ServicoConverter.converterJson(reqServer3.get());



        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_perfil) {
            Intent intentVaiProPerfilActivity = new Intent(MainActivity.this, PerfilActivity.class);
            startActivity(intentVaiProPerfilActivity);
            return (true);
        } else if (item.getItemId() == R.id.menu_logout) {
            startActivity(new Intent(this, LoginActivity.class));

            SessionRepository.setNomeusuario(null);
            SessionRepository.setSenhausuario(null);
            SessionRepository.setUsuario(null);

            finish();
            return (true);
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void getSolicitacoesAbertas(List<Solicitacao> solicitacoes) {
        solicitacoesAbertas.clear();
        for (Solicitacao s : solicitacoes) {
            if (!s.getStatus().equals("closed") && !s.getStatus().equals("resolved")) {
                solicitacoesAbertas.add(s);
            }
        }
    }

    public void getSolicitacoesFechadas(List<Solicitacao> solicitacoes) {
        solicitacoesFechadas.clear();
        for (Solicitacao s : solicitacoes) {
            if (s.getStatus().equals("closed") || s.getStatus().equals("resolved")) {
                solicitacoesFechadas.add(s);
            }
        }
    }
}