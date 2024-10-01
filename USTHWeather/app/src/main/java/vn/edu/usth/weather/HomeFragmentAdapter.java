package vn.edu.usth.weather.adapter;
import android.app.AlertDialog;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import vn.edu.usth.weather.fragment.weather_forcast_fragment.WeatherAndForecastFragment;
import vn.edu.usth.weather.fragment.weather_forcast_fragment.WeatherAndForecastFragment2;
import vn.edu.usth.weather.fragment.weather_forcast_fragment.WeatherAndForecastFragment3;

import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragmentPagerAdapter extends FragmentStateAdapter {
    public HomeFragmentPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);

     HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(this);
        AlertDialog.Builder pager = null;
        pager.setAdapter(adapter);
        TabLayout tablayout = findViewById(R.id.tab_layout);
        String[] titles = new String[]{"Hà Nội","Pari","Tokyo"};
        TabLayoutMediator.TabConfigurationStrategy position;
        TabLayoutMediator layoutMediator = new TabLayoutMediator(tablayout,pager,(tab,position))-> {tab.setText(titles[position]);}
        }

    public HomeFragmentPagerAdapter(@NonNull FragmentManager fm,@NonNull Lifecycle lf) {
        super(fm,lf);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
