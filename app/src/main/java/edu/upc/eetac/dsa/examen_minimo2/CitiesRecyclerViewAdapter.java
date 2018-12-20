package edu.upc.eetac.dsa.examen_minimo2;

import android.content.Context;
        import android.support.constraint.ConstraintLayout;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import com.squareup.picasso.Picasso;

        import java.util.ArrayList;
        import java.util.List;


    public class CitiesRecyclerViewAdapter extends RecyclerView.Adapter<CitiesRecyclerViewAdapter.ViewHolder> {

    private List<Element> data;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView escut;
        TextView municipi;
        TextView id;

        public ViewHolder(View v) {
            super(v);
            escut = v.findViewById(R.id.escut);
            municipi = (TextView) v.findViewById(R.id.municipiNom_textView);
            id = (TextView) v.findViewById(R.id.id_textView);
        }
    }

    /*public CitiesRecyclerViewAdapter(List<Element> data) {
            this.data = data;
    }*/

    public void addElements(List<Element> elementList) {
            data.addAll(elementList);
            notifyDataSetChanged();
        }

        public CitiesRecyclerViewAdapter(Context context) {
            this.data = new ArrayList<>();
            this.context=context;
        }

    @Override
    public CitiesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        context = parent.getContext();
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CitiesRecyclerViewAdapter.ViewHolder holder, int position) {
        Element elementdata = ((Element)data.get(position));
        holder.municipi.setText(elementdata.getMunicipiNom());
        holder.id.setText(elementdata.getIne());
        Picasso.with(context).load(elementdata.getMunicipiEscut()).into(holder.escut);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}