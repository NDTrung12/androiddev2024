package vn.edu.usth.weather;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class WeatherPagerAdapter extends FragmentPagerAdapter {

    public WeatherPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // Return a new instance of WeatherAndForecastFragment for each page
        return new WeatherAndForecastFragment();
    }

    @Override
    public int getCount() {
        // Return 3 pages (3 instances of WeatherAndForecastFragment)
        return 3;
    }
}
