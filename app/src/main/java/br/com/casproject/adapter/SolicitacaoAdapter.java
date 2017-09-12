package br.com.casproject.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.casproject.R;
import br.com.casproject.modelo.Solicitacao;

public class SolicitacaoAdapter extends RecyclerView.Adapter<SolicitacaoAdapter.SolicitacaoViewHolder> {


    private List<Solicitacao> solicitacaoes;

    public SolicitacaoAdapter(List<Solicitacao> solicitacaoes) {
        this.solicitacaoes = solicitacaoes;
    }

    public class SolicitacaoViewHolder extends RecyclerView.ViewHolder {
        private TextView txtRef;
        private TextView txtTitulo;
        private TextView txtDescricao;

        public SolicitacaoViewHolder(View itemView) {
            super(itemView);
            txtRef = (TextView) itemView.findViewById(R.id.item_solicitacao_ref);
            txtTitulo = (TextView) itemView.findViewById(R.id.item_solicitacao_title);
            txtDescricao = (TextView) itemView.findViewById(R.id.item_solicitacao_descrition);

        }
    }

    @Override
    public SolicitacaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Na linha abaixo passamos o Layout de cada item que será encaixado na lista
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_solicitacao, parent, false);
        // Na linha abaixo instanciamos (damos vida) o LivroViewHolder
        SolicitacaoViewHolder vh = new SolicitacaoViewHolder(view);
        // Na linha abaixo passamos o LivroViewHolder para o Adapter
        return vh;

    }

    @Override
    public void onBindViewHolder(SolicitacaoViewHolder holder, int position) {
        holder.txtRef.setText(solicitacaoes.get(position).getRef());

        String titulo;

        titulo = solicitacaoes.get(position).getTitle();

        holder.txtTitulo.setText(titulo);

        String descricao;

        descricao = solicitacaoes.get(position).getDescription();

        holder.txtDescricao.setText(descricao);
    }

    @Override
    public int getItemCount() {
        // size = comprimento (no nosso caso: a quantidade de livros da lista)
        return solicitacaoes.size();

    }

}
