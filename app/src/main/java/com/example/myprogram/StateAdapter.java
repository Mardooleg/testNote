package com.example.myprogram;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

    public class StateAdapter  extends RecyclerView.Adapter<StateAdapter.ViewHolder>  {

        private final LayoutInflater inflater;
        private final List<Notatka> notatkas;

        StateAdapter(Context context, List<Notatka> notatkas) {
            this.notatkas = notatkas;
            this.inflater = LayoutInflater.from(context);
        }
        @NonNull
        @Override
        public StateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = inflater.inflate(R.layout.list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(StateAdapter.ViewHolder holder, int position) {
            Notatka notatka = notatkas.get(position);
            if(notatka.isFavorite()) {
                holder.favorite.setImageResource(R.drawable.likeheart);
            }else {
                holder.favorite.setImageResource(R.drawable.favorite);
            }
            holder.firsttitle.setText(notatka.getTitle());
            holder.firstnote.setText(notatka.getNote());

            holder.favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notatka.setFavorite(!notatka.isFavorite());
                    if(notatka.isFavorite()) {
                        holder.favorite.setImageResource(R.drawable.likeheart);
                    }else {
                        holder.favorite.setImageResource(R.drawable.favorite);
                    }
                    App.getInstance().getAppDatabase().modelDao().update(notatka);
                }
            });
//   holder.firstnote.setOnClickListener(new View.OnClickListener() {
//       @Override
//       public void onClick(View v) {
//           Intent intent1 = new Intent(this, Note.class);
//           startActivity(intent1);
//           notatka.setFirstnote(!notatka.isFavorite());
//
//           App.getInstance().getAppDatabase().modelDao().update(notatka);
//
//       }
//   });
    }
        @Override
        public int getItemCount() {
            return notatkas.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            final ImageView favorite;
            final TextView firsttitle, firstnote;

            ViewHolder(View view) {
                super(view);
                favorite = (ImageView) view.findViewById(R.id.heart);
                firstnote = (TextView) view.findViewById(R.id.firstnote);
                firsttitle = (TextView) view.findViewById(R.id.firsttitle);
            }
        }

    }