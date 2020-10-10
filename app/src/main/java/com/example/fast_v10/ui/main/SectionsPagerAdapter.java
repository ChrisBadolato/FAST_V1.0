 package com.example.fast_v10.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.fast_v10.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment fragment = null;
        switch(position){
            case 0:
                fragment = new mainMenu();
                return fragment;
            case 1:
                fragment = new masterDevice();
                return fragment;
            case 2:
                fragment = new deviceOne();
                return fragment;
            case 3:
                fragment = new deviceTwo();
                return fragment;
            case 4:
                fragment = new deviceThree();
                return fragment;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "Main Menu";
            case 1:
                return "Master Device";
            case 2:
                return "Device One";
            case 3:
                return "Device Two";
            case 4:
                return "Device Three";
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 5;
    }
}