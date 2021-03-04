package com.example.myprogram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StateAdapter1 extends RecyclerView.Adapter<StateAdapter1.ViewHolder>  {

    private final LayoutInflater inflater;
    private final List<Notatka> notatkas;

    StateAdapter1(Context context, List<Notatka> notatkas) {
        this.notatkas = notatkas;
        this.inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public StateAdapter1.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StateAdapter1.ViewHolder holder, int position) {
        Notatka notatka = notatkas.get(position);
            holder.favorite.setImageResource(R.drawable.likeheart);

//            App.getInstance().getAppDatabase().modelDao().save(notatka);
        holder.firsttitle.setText(notatka.getTitle());
        holder.firstnote.setText(notatka.getNote());

        holder.firstnote.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //if(onClickToMore != null)
//                        onClickToMore.onClick(notatka);
                App.getInstance().getAppDatabase().modelDao().delete(notatka);

                notatkas.remove(notatka);
                StateAdapter1.this.notifyItemRemoved(position);

                return true;
            }
        });

        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notatka.setFavorite(false);
                notatkas.remove(notatka);
                App.getInstance().getAppDatabase().modelDao().update(notatka);
                StateAdapter1.this.notifyItemRemoved(position);
            }


        });
                App.getInstance().getAppDatabase().modelDao().update(notatka);
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

            favorite = (ImageView) view.findViewById(R.id.heart);
            more = (ImageView) view.findViewById(R.id.more);

            firstnote = (TextView) view.findViewById(R.id.firstnote);
            firsttitle = (TextView) view.findViewById(R.id.firsttitle);
        }
    }

}