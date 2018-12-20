package edu.upc.eetac.dsa.examen_minimo2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

class CitiesRecyclerViewAdapter extends RecyclerView.Adapter {

    public TextView text;

    private ImageView escutImageView;
    private TextView nametxt;
    private LinearLayout linearLayout;


    public ViewHolder(View v) {
        super(v);
        text = (TextView) v.findViewById(android.R.id.text1);
        escutImageView=v.findViewById(R.id.imageuser);
        nametxt=v.findViewById(R.id.usernaetxt);
        linearLayout =v.findViewById(R.id.linearLayout);
    }

    public CitiesRecyclerViewAdapter(List<Element> elements) {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
