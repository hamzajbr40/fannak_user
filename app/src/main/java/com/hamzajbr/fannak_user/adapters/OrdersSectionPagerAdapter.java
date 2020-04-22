package com.hamzajbr.fannak_user.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hamzajbr.fannak_user.R;
import com.hamzajbr.fannak_user.fragments.CurrentOrdersFragment;
import com.hamzajbr.fannak_user.fragments.PrevOrdersFragment;

public class OrdersSectionPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.previous, R.string.current};
    private final Context mContext;


    public OrdersSectionPagerAdapter(FragmentActivity activity, FragmentManager childFragmentManager) {
        super(childFragmentManager);
        mContext = activity;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        if (position == 0) {
            return new PrevOrdersFragment();
        } else
            return new CurrentOrdersFragment();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }
}
