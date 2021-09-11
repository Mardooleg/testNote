package com.example.myprogram;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Mywidget extends AppWidgetProvider  {

    private RecyclerView recyclerViewNotesw;

    final String LOG_TAG = "myLogs";
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d(LOG_TAG, "onEnabled");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d(LOG_TAG, "onUpdate " + Arrays.toString(appWidgetIds));
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.d(LOG_TAG, "onDeleted " + Arrays.toString(appWidgetIds));
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d(LOG_TAG, "onDisabled");


        int color1 =  R.drawable.favorite_greenblue;//"No name defined" is the default value.
        int color2 = R.drawable.elipse2_greenblue; //0 is the default value.
        int color3 =  R.drawable.elipse3_greenblue;//0 is the default value.
        int color4 =  R.color.greenblue2; //0 is the default value.
        int color5 = R.color.greenblue1; //0 is the default value.
        int color6 =  R.color.greenblue3; //0 is the default value.

        List<Notatka> notatkas = App.getInstance().getAppDatabase().modelDao().getAll("");
        Collections.reverse(notatkas);
        StateAdapter stateAdapter = new StateAdapter(context, notatkas, color1, color2, color3, color4,  color5, color6);
//        stateAdapter.setOnClickToMore((StateAdapter.OnClickToMore) context);

        recyclerViewNotesw = recyclerViewNotesw.findViewById(R.id.wv);
        recyclerViewNotesw.setAdapter(stateAdapter);
        recyclerViewNotesw.setLayoutManager(new GridLayoutManager(context, 1));
    }

}
