package br.com.casproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.casproject.adapter.SolicitacaoAdapter;

public class SolicitacaoAbertaFragment extends Fragment {

    private RecyclerView rcvSolicitacoes;


    public static SolicitacaoAbertaFragment newInstance() {

        SolicitacaoAbertaFragment fragment = new SolicitacaoAbertaFragment();
        return fragment;
    }

    public SolicitacaoAbertaFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_solicitacaoaberta, container, false);

        rcvSolicitacoes = (RecyclerView) rootView.findViewById(R.id.rcv_solicitacoesabertas);

        SolicitacaoAdapter adapter = new SolicitacaoAdapter(MainActivity.solicitacoesAbertas);

        rcvSolicitacoes.setAdapter(adapter);

        // O LinearLayoutManager e um auxiliar do recyclerview, ele ajuda a direcionar os itens dentro do recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        rcvSolicitacoes.setLayoutManager(llm);

        return rootView;
    }

}