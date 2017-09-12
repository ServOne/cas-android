package br.com.casproject.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import br.com.casproject.FormularioSolicitacao;
import br.com.casproject.MainActivity;
import br.com.casproject.R;
import br.com.casproject.modelo.Servico;
import br.com.casproject.modelo.SubServico;


public class SubServicoAdapter extends RecyclerView.Adapter<SubServicoAdapter.SubServicoViewHolder> {


    private List<SubServico> subServicos;
    private List<Servico> servicos;
    private MainActivity activity;

    public SubServicoAdapter(List<SubServico> subServicos, List<Servico> servicos, MainActivity activity) {
        this.subServicos = subServicos;
        this.servicos = servicos;
        this.activity = activity;
    }

    public class SubServicoViewHolder extends RecyclerView.ViewHolder {
        private TextView txtServico;
        private TextView txtNome;
        private TextView txtDescricao;
        private Button btnSolicitacaoNovo;
        private int posicao;

        public SubServicoViewHolder(View itemView) {
            super(itemView);
            txtServico = (TextView) itemView.findViewById(R.id.item_servico_nome);
            txtNome = (TextView) itemView.findViewById(R.id.item_subservico_nome);
            txtDescricao = (TextView) itemView.findViewById(R.id.item_subservico_descrition);

            btnSolicitacaoNovo = (Button) itemView.findViewById(R.id.btn_solicitacao_novo);

        }
    }

    @Override
    public SubServicoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Na linha abaixo passamos o Layout de cada item que será encaixado na lista
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_servico, parent, false);
        // Na linha abaixo instanciamos (damos vida) o LivroViewHolder
        SubServicoViewHolder vh = new SubServicoViewHolder(view);
        // Na linha abaixo passamos o LivroViewHolder para o Adapter
        return vh;

    }

    @Override
    public void onBindViewHolder(final SubServicoViewHolder holder, int position) {

        String nomeservico;

        if (subServicos.get(position).getService_name().length() >= 80) {
            nomeservico = subServicos.get(position).getService_name().substring(1, 77) + "...";
        } else {
            nomeservico = subServicos.get(position).getService_name();
        }

        holder.txtServico.setText(nomeservico);

        String nomesubservico;

        if (subServicos.get(position).getName().length() >= 80) {
            nomesubservico = subServicos.get(position).getName().substring(1, 77) + "...";
        } else {
            nomesubservico = subServicos.get(position).getName();
        }

        holder.txtNome.setText(nomesubservico);

        String descricao;

        if (subServicos.get(position).getDescription().length() >= 80) {
            descricao = subServicos.get(position).getDescription().substring(1, 77) + "...";
        } else {
            descricao = subServicos.get(position).getDescription();
        }

        holder.txtDescricao.setText(descricao);

        holder.posicao = position;

        holder.btnSolicitacaoNovo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentVaiProFormulario = new Intent(activity, FormularioSolicitacao.class);
                intentVaiProFormulario.putExtra("subServicos", (Serializable) subServicos);
                intentVaiProFormulario.putExtra("servicos", (Serializable) servicos);
                intentVaiProFormulario.putExtra("posicao", holder.posicao);
                activity.startActivity(intentVaiProFormulario);
            }
        });

    }

    @Override
    public int getItemCount() {
        // size = comprimento (no nosso caso: a quantidade de livros da lista)
        return subServicos.size();

    }

}
