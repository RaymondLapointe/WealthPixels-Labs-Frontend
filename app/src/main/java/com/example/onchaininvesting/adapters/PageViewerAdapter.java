package com.example.onchaininvesting.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.onchaininvesting.fragments.ProfileFragment;
import com.example.onchaininvesting.fragments.TransactionFragment;
import com.example.onchaininvesting.fragments.VaultFragment;

public class PageViewerAdapter extends FragmentPagerAdapter {

    public PageViewerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new VaultFragment();
            case 1:
                return new TransactionFragment();
            case 2:
                return new ProfileFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}