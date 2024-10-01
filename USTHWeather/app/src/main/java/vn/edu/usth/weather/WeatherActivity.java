package vn.edu.usth.weather.activity;

import android.os.Build;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Toast;
import android.util.Log;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.media.MediaPlayer;
import android.widget.Toolbar;

import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import vn.edu.usth.weather.adapter.HomeFragmentPagerAdapter;
import vn.edu.usth.weather.R;

public class WeatherActivity extends AppCompatActivity {
    public static final String TAG = "Weather";
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        Log.i(TAG,"ON_CREATE");
        mediaPlayer = MediaPlayer.create(this,R.raw.music);
        mediaPlayer.start();
        Toolbar toolbar = findViewById(R.id.weather_toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.inflateMenu(R.menu.weather_menu);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle(R.string.app_name);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setOnMenuItemClickListener(item -> {
                int itemMenuId =item.getItemId();
                if(itemMenuId == R.id.ic_refresh){
                    Toast.makeText(this,"Refreshing process...",Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemMenuId == R.id.ic_more) {
                    Intent intent = new Intent(this,PreActivity.class);
                    startActivity(intent);
                    return true;
                }else{
                    Toast.makeText(this,"Not found menu item",Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }
        private void initViewPager() {
            ViewPager2 pager = findViewById(R.id.pager2);
            //HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(), getLifecycle());
            HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(this);
            pager.setAdapter(adapter);
            TabLayout tablayout = findViewById(R.id.tab_layout);
            tablayout.setupWithViewPager(pager);
            String[] titles = new String[]{"Hanoi, Vietnam", "Paris, France", "Tokyo, Japan"};
            TabLayoutMediator layoutMediator = new TabLayoutMediator(tablayout, pager,
                    ((tab, position) -> {
                        tab.setText(titles[position]);
                    }));
            layoutMediator.attach();

    }}

        @Override
        protected void onStart() {
            super.onStart();
            Log.i(TAG, "ON_START");
        }
        @Override
        protected void onResume() {
            super.onResume();
            Log.i(TAG, "ON_RESUME");
        }
        @Override
        protected void onPause() {
            super.onPause();
            Log.i(TAG, "ON_PAUSE");
        }
        @Override
        protected void onStop() {
            super.onStop();
            Log.i(TAG, "ON_STOP");
        }
        @Override
        protected void onDestroy() {
            super.onDestroy();
            Log.i(TAG, "ON_DESTROY");
    }
}