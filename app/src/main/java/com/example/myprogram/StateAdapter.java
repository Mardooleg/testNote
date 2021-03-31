package com.example.myprogram;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.Image;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.view.View.VISIBLE;

public class StateAdapter  extends RecyclerView.Adapter<StateAdapter.ViewHolder>  {

        private final LayoutInflater inflater;
        private final List<Notatka> notatkas;
        private OnClickToMore onClickToMore;
        private Context context;

        int colorFav = R.drawable.favorite;
        int colorTitle = R.drawable.elipse2;
        int colorDec = R.drawable.elipse3;

        int colorTitle1 = R.color.greenblue1;
        int colorDec1 = R.color.greenblue2;
    int colorBottom = R.color.greenblue3;
        public OnClickToMore getOnClickToMore() {
            return onClickToMore;
        }

        public void setOnClickToMore(OnClickToMore onClickToMore) {
            this.onClickToMore = onClickToMore;
        }

        StateAdapter(Context context, List<Notatka> notatkas, int colorFav, int colorTitle, int colorDec, int colorTitle1, int colorDec1, int colorBottom) {
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
                holder.favorite.setImageResource(colorFav);
            }
            holder.firsttitle.setText(notatka.getTitle());
            holder.firsttitle.setBackground(ContextCompat.getDrawable(holder.firstnote.getContext(), colorTitle));
            holder.firstnote.setText(notatka.getNote());
            holder.firstnote.setBackground(ContextCompat.getDrawable(holder.firstnote.getContext(), colorDec));
            holder.firstnote.setOnClickListener(new DoubleClickListener() {
                @Override
                public void onDoubleClick() {
                    notatka.setFavorite(!notatka.isFavorite());
                    if(notatka.isFavorite()){
                        holder.favorite.setImageResource(R.drawable.likeheart);
                    }else{
                        holder.favorite.setImageResource(colorFav);

                    }
                    App.getInstance().getAppDatabase().modelDao().update(notatka);
                }


                @Override
                public void onSingleClick() {
                    Intent intent1 = new Intent(context, Note.class);
                    intent1.putExtra("STRING_NOTE" , notatka);

                    intent1.putExtra("COLOR_TITLE" , colorTitle1);
                    intent1.putExtra("COLOR_DEC" ,colorDec1 );
                    context.startActivity(intent1);
                }
            });

            holder.favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

            holder.firstnote.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    if(onClickToMore != null)
                        onClickToMore.onClick(notatka);
                    App.getInstance().getAppDatabase().modelDao().update(notatka);
                    return true;
                }
            });
                        holder.favorite.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                notatka.setFavorite(!notatka.isFavorite());
                                if(notatka.isFavorite()){
                                    holder.favorite.setImageResource(R.drawable.likeheart);
                                }else{
                                    holder.favorite.setImageResource(colorFav);

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
            final ImageView elipse1;
            final TextView background1;
            final TextView firsttitle, firstnote ;

            ViewHolder(View view) {
                super(view);

                favorite = (ImageView) view.findViewById(R.id.heart);
                firstnote = (TextView) view.findViewById(R.id.firstnote);
                firsttitle = (TextView) view.findViewById(R.id.firsttitle);
                elipse1 = (ImageView) view.findViewById(R.id.elipse4);
                background1 = (TextView) view.findViewById(R.id.background1);

            }
        }

        public interface OnClickToMore{
            public void onClick(Notatka notatka);
        }

    }