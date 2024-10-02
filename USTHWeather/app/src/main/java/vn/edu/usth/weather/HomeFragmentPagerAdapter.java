package vn.edu.usth.weather;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vn.edu.usth.weather.WeatherAndForecastFragment1;
import vn.edu.usth.weather.WeatherAndForecastFragment2;
import vn.edu.usth.weather.WeatherAndForecastFragment3;

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private final String[] titles = new String[]{"Hanoi", "Paris", "Tokyo"};

    public HomeFragmentPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    private FragmentManager getSupportFragmentManager() {
        return null;
    }

    @Override
    public int getCount() {
        return 3; // number of pages for the ViewPager
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // Returns an instance of Fragment corresponding to the specified page
        switch (position) {
            case 0:
                return new WeatherAndForecastFragment1();
            case 1:
                return new WeatherAndForecastFragment2();
            case 2:
                return new WeatherAndForecastFragment3();
            default:
                return new Fragment(); // failsafe, or you can return a specific EmptyFragment if defined
        }
    }
public void setupViewPager(ViewPager viewPager){
        viewPager.setOffscreenPageLimit(3);
            viewPager.setAdapter(this);
}
    @Override
    public CharSequence getPageTitle(int position) {
        // Returns the tab title corresponding to the specified page
        return titles[position];
    }
}
