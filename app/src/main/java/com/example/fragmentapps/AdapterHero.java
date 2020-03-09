package com.example.fragmentapps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterHero extends RecyclerView.Adapter<AdapterHero.ListViewHolder> {

    private ArrayList<ModelHero> listHero;
    private OnItemClickCallback onItemClickCallback;

    public AdapterHero(ArrayList<ModelHero> list) {
        this.listHero = list;
    }

    public ArrayList<ModelHero> getListHero() {
        return listHero;
    }

    public void setListHero(ArrayList<ModelHero> listHero) {
        this.listHero = listHero;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.heroes_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        ModelHero hero = listHero.get(position);
        Glide.with(holder.itemView.getContext())
                .load(hero.getHeroImage())
                .apply(new RequestOptions().override(300, 300))
                .into(holder.heroImage);

        holder.heroName.setText(hero.getHeroName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listHero.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listHero.size();
    }


    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView heroImage;
        TextView heroName, heroDesc;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            heroImage   = itemView.findViewById(R.id.hero_image);
            heroName    = itemView.findViewById(R.id.hero_name);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(ModelHero hero);
    }
}