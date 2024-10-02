package vn.edu.usth.weather;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class WeatherActivity extends AppCompatActivity {
    public static final String TAG = "Weather";
    public static final String NETWORK_RESPONSE = "KEY";
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.i(TAG, "ON_CREATE");

        // Initialize and play media
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        mediaPlayer.start();

        // Handler and Thread example
        final Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message mess) {
                String content = mess.getData().getString(NETWORK_RESPONSE);
                Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
            }
        };

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Bundle bundle = new Bundle();
            bundle.putString(NETWORK_RESPONSE, "Request for Network?");
            Message mess = new Message();
            mess.setData(bundle);
            handler.sendMessage(mess);
        });
        thread.start();

        // Setup the toolbar
        Toolbar toolbar = findViewById(R.id.weather_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(item -> {
            int itemMenuId = item.getItemId();
            if (itemMenuId == R.id.ic_refresh) {
                Toast.makeText(this, "Refreshing process...", Toast.LENGTH_SHORT).show();
                return true;
            } else if (itemMenuId == R.id.ic_more) {
                Intent intent = new Intent(this, PreActivity.class);
                startActivity(intent);
                return true;
            } else {
                Toast.makeText(this, "Menu item not found", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Setup ViewPager2 and TabLayout
        ViewPager2 pager = findViewById(R.id.pager2);
        PagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        pager.setOffscreenPageLimit(3);


        TabLayout tabLayout = findViewById(R.id.tab_layout);
        String[] titles = new String[]{"Hanoi, Vietnam", "Paris, France", "Tokyo, Japan"};
        new TabLayoutMediator(tabLayout, pager, (tab, position) -> tab.setText(titles[position])).attach();

        // AsyncTask example
        @SuppressLint("StaticFieldLeak")
        AsyncTask<String, Integer, Bitmap> task = new AsyncTask<String, Integer, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... strings) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null; // Replace with actual bitmap processing logic
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                // Handle bitmap (if needed)
            }
        };

        task.execute(); // Start the AsyncTask
    }

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
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause(); // Pause music when the activity is not in the foreground
        }
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
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
