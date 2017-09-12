package br.com.casproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.casproject.adapter.SolicitacaoAdapter;
import br.com.casproject.converter.SolicitacaoConverter;
import br.com.casproject.modelo.Solicitacao;
import br.com.casproject.repository.SessionRepository;
import br.com.casproject.task.TaskGenerica;

public class SolicitacaoFechadaFragment extends Fragment {

    private RecyclerView rcvSolicitacoes;

    public static SolicitacaoFechadaFragment newInstance() {
        SolicitacaoFechadaFragment fragment = new SolicitacaoFechadaFragment();
        return fragment;
    }

    public SolicitacaoFechadaFragment() {
        // Deve existir um construtor vazio
        // na classe que estende um Fragment
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_solicitacaofechada, container, false);

        rcvSolicitacoes = (RecyclerView) rootView.findViewById(R.id.rcv_solicitacoesfechadas);

            SolicitacaoAdapter adapter = new SolicitacaoAdapter(MainActivity.solicitacoesFechadas);

            // Passo o adapter para o recyclerView
            rcvSolicitacoes.setAdapter(adapter);

            // O LinearLayoutManager e um auxiliar do recyclerview, ele ajuda a direcionar os itens dentro do recyclerview
            LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

            rcvSolicitacoes.setLayoutManager(llm);

        return rootView;
    }

}