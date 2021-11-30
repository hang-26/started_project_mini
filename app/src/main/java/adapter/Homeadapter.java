package adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import authen_screen.fradment.FriendFragment;
import authen_screen.fradment.HomeFragment;

public class Homeadapter extends FragmentPagerAdapter {

    private final int NUMBER_PAGE = 2;
    public Homeadapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public Homeadapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new
                        HomeFragment();

            case 1:
                return new FriendFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUMBER_PAGE;
    }
}
