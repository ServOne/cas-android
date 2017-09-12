package br.com.casproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.casproject.adapter.SubServicoAdapter;

public class NovaSolicitacaoFragment extends Fragment {

    private RecyclerView rcvServicos;
    private View rootView;

    public static NovaSolicitacaoFragment newInstance() {
        NovaSolicitacaoFragment fragment = new NovaSolicitacaoFragment();
        return fragment;
    }

    public NovaSolicitacaoFragment() {
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
        rootView = inflater.inflate(R.layout.fragment_novasolicitacao, container, false);

        rcvServicos = (RecyclerView) rootView.findViewById(R.id.rcv_novasolicitacao);

        SubServicoAdapter adapter = new SubServicoAdapter(MainActivity.subServicos, MainActivity.servicos,
                (MainActivity) getActivity());

        // Passo o adapter para o recyclerView
        rcvServicos.setAdapter(adapter);

        // O LinearLayoutManager e um auxiliar do recyclerview, ele ajuda a direcionar os itens dentro do recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        rcvServicos.setLayoutManager(llm);

        return rootView;
    }

}