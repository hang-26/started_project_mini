package com.example.singinapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import fradment.LoginFragment;
import fradment.RegisterFragment;

public class LoginAndRegisterViewAdapter extends FragmentPagerAdapter {
    public LoginAndRegisterViewAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public LoginAndRegisterViewAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
    @NonNull
    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0:
                return new LoginFragment();
            case 1:
                return new RegisterFragment();

        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Login";
            case 1:
                return "Register";

        }
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
