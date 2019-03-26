package at.gv.brz.roeslerz.inventur.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import at.gv.brz.roeslerz.inventur.R;
import at.gv.brz.roeslerz.inventur.models.Produkt;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProduktAdapter extends RecyclerView.Adapter<ProduktAdapter.ViewHolder>  {

    final private OnItemClickListener listener;
    private ArrayList<Produkt> produktListe;

    public ProduktAdapter(ArrayList<Produkt> produktListe, OnItemClickListener listener) {
        this.produktListe = produktListe;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Produkt produkt);
    }

    @NonNull
    @Override
    public ProduktAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProduktAdapter.ViewHolder holder, int position) {
        final Produkt produkt = produktListe.get(position);

        String produktID = produkt.getProduktId();
        holder.produktIdView.setText(produktID);

        String produktName = produkt.getProduktName();
        holder.produktNameView.setText(produktName);

       // int lid = produkt.getLid();
       // holder.produktLieferantView.setText(String.valueOf(lid));

        int produktPreis = produkt.getProduktPreis();
        holder.produktPreisView.setText(String.valueOf(produktPreis));

        int produktAnzahl = produkt.getProduktAnzahl();
        holder.produktAnzahlView.setText(String.valueOf(produktAnzahl));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(produkt);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (produktListe == null || produktListe.isEmpty()) {
            return 0;
        } else {
            return this.produktListe.size();
        }
    }

    // Create the ViewHolder class for references
    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_produktId)
        TextView produktIdView;
        @BindView(R.id.tv_produktName)
        TextView produktNameView;
        //@BindView(R.id.tv_produktLieferant)
        //TextView produktLieferantView;
        @BindView(R.id.tv_produktPreis)
        TextView produktPreisView;
        @BindView(R.id.tv_produktAnzahl)
        TextView produktAnzahlView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    // Helper method to clear or update list
    public void setProduktList(ArrayList<Produkt> produktListe) {
        this.produktListe = produktListe;
    }
}
