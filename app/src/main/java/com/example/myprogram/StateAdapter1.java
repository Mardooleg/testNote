package com.example.myprogram;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StateAdapter1 extends RecyclerView.Adapter<StateAdapter1.ViewHolder>  {

    private final LayoutInflater inflater;
    private final List<Notatka> notatkas;
    private StateAdapter.OnClickToMore onClickToMore;
    private Context context;

    int colorFav = R.drawable.favorite;
    int colorTitle = R.drawable.elipse2;
    int colorDec = R.drawable.elipse3;

    int colorTitle1 = R.color.greenblue1;
    int colorDec1 = R.color.greenblue2;
    int colorBottom = R.color.greenblue3;
    public StateAdapter.OnClickToMore getOnClickToMore() {
        return onClickToMore;
    }

    public void setOnClickToMore(StateAdapter.OnClickToMore onClickToMore) {
        this.onClickToMore = onClickToMore;
    }

    StateAdapter1(Context context, List<Notatka> notatkas,  int colorFav, int colorTitle, int colorDec, int colorTitle1, int colorDec1, int colorBottom) {
        this.context = context;
        this.notatkas = notatkas;
        this.inflater = LayoutInflater.from(context);

        this.colorFav = colorFav;
        this.colorTitle = colorTitle;
        this.colorDec = colorDec;
        this.colorTitle1 = colorTitle1;
        this.colorDec1 = colorDec1;
        this.colorBottom = colorBottom;

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

        holder.firsttitle.setText(notatka.getTitle());
        holder.firsttitle.setBackground(ContextCompat.getDrawable(holder.firstnote.getContext(), colorTitle));
        holder.firstnote.setText(notatka.getNote());
        holder.firstnote.setBackground(ContextCompat.getDrawable(holder.firstnote.getContext(), colorDec));


        holder.firstnote.setOnLongClickListener(new View.OnLongClickListener() {


            @Override
            public boolean onLongClick(View v) {
                notatka.setFavorite(false);
                notatkas.remove(notatka);
                App.getInstance().getAppDatabase().modelDao().update(notatka);
                StateAdapter1.this.notifyItemRemoved(position);
                Toast.makeText(context, "You removed from favorite",  Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        App.getInstance().getAppDatabase().modelDao().update(notatka);
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

    holder.firstnote.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent1 = new Intent(context, Note.class);
            intent1.putExtra("STRING_NOTE" , notatka);

            intent1.putExtra("COLOR_TITLE" , colorTitle1);
            intent1.putExtra("COLOR_DEC" ,colorDec1 );
            context.startActivity(intent1);
        }


    });
    }
    @Override
    public int getItemCount() {
        return notatkas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView favorite;
        final ImageView elipse1;

        final TextView background;

        final TextView firsttitle, firstnote;

        ViewHolder(View view) {
            super(view);

            favorite = (ImageView) view.findViewById(R.id.heart);
            elipse1 = view.findViewById(R.id.elipse4);
            firstnote = (TextView) view.findViewById(R.id.firstnote);
            firsttitle = (TextView) view.findViewById(R.id.firsttitle);
            background = (TextView) view.findViewById(R.id.background1);
        }
    }
    public interface OnClickToMore{
        public void onClick(Notatka notatka);
    }
}