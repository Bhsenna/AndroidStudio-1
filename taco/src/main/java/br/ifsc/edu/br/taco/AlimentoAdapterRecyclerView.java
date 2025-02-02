package br.ifsc.edu.br.taco;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AlimentoAdapterRecyclerView extends RecyclerView.Adapter<AlimentoAdapterRecyclerView.AlimentoVH> {
    Context mContext;
    int mResource;
    List<Alimento> mListAlimento;
    private OnItemClickListener onItemClickListener;

    public AlimentoAdapterRecyclerView(Context context, int resource, List<Alimento> objs) {
        mContext = context;
        mResource = resource;
        mListAlimento = objs;
    }

    public interface OnItemClickListener {
        void onItemClick(Alimento alimento);
    }

    @NonNull
    @Override
    public AlimentoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(mContext, mResource, null);
        return new AlimentoVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AlimentoVH holder, int position) {
        Alimento a = (Alimento) mListAlimento.get(position);
        holder.textViewNome.setText(a.nome_alimento);
        holder.textViewCategoria.setText(a.categoria);

        holder.itemView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(a);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListAlimento.size();
    }

    public void setOnClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class AlimentoVH extends RecyclerView.ViewHolder {
        TextView textViewNome;
        TextView textViewCategoria;
        public AlimentoVH(@NonNull View itemView) {
            super(itemView);
            textViewNome = itemView.findViewById(R.id.nome_alimento);
            textViewCategoria = itemView.findViewById(R.id.categoria);
        }
    }
}
