package com.example.myprogram;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPageAdapter extends FragmentStatePagerAdapter {

    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
       switch (position){
           case 0 :
               return new FavoriteFrgment();
           case 1 :
               return new HomeFrgment();
           case 2 :
               return new SettingsFrgment();
           default:
               return new HomeFrgment();
       }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
