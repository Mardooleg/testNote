package com.example.myprogram;

import android.app.AlertDialog;
import android.content.Intent;
import android.media.Image;
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
        private OnClickToMore onClickToMore;

        public OnClickToMore getOnClickToMore() {
            return onClickToMore;
        }

        public void setOnClickToMore(OnClickToMore onClickToMore) {
            this.onClickToMore = onClickToMore;
        }

        StateAdapter(Context context, List<Notatka> notatkas) {
            this.notatkas = notatkas;
            this.inflater = LayoutInflater.from(context);
        }
        @NonNull
        @Override
        public StateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = inflater.inflate(R.layout.list_item, parent, false);
            return new StateAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(StateAdapter.ViewHolder holder, int position) {
            Notatka notatka = notatkas.get(position);
            if (notatka.isFavorite()) {
                holder.favorite.setImageResource(R.drawable.likeheart);
            } else {
                holder.favorite.setImageResource(R.drawable.favorite);
            }
            holder.firsttitle.setText(notatka.getTitle());
            holder.firstnote.setText(notatka.getNote());

            holder.firstnote.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //if(onClickToMore != null)
//                        onClickToMore.onClick(notatka);
                    App.getInstance().getAppDatabase().modelDao().delete(notatka);

                    notatkas.remove(notatka);
                    StateAdapter.this.notifyItemRemoved(position);

                    return true;
                }
            });

            holder.favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notatka.setFavorite(!notatka.isFavorite());
                    if (notatka.isFavorite()) {
                        holder.favorite.setImageResource(R.drawable.likeheart);
//                        App.getInstance().getAppDatabase().modelDao().update(notatka1);

                    } else {
                        holder.favorite.setImageResource(R.drawable.favorite);
                    }
                    App.getInstance().getAppDatabase().modelDao().update(notatka);
                }
            });

        }

        @Override
        public int getItemCount() {
            return notatkas.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            final ImageView favorite;
            final ImageView more;

            final TextView firsttitle, firstnote;

            ViewHolder(View view) {
                super(view);
                more = (ImageView) view.findViewById(R.id.more);

                favorite = (ImageView) view.findViewById(R.id.heart);
                firstnote = (TextView) view.findViewById(R.id.firstnote);
                firsttitle = (TextView) view.findViewById(R.id.firsttitle);
            }
        }

        public interface OnClickToMore{
            public void onClick(Notatka notatka);
        }

    }