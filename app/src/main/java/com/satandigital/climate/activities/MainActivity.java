package com.satandigital.climate.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.satandigital.climate.R;
import com.satandigital.climate.fragments.ForecastFragment;

/**
 * Project : Climate
 * Created by Sanat Dutta on 4/23/2016.
 */
public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        attachFragment(savedInstanceState);
    }

    private void attachFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }

        if (id == R.id.action_map) {
            openPreferredLocationInMap();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openPreferredLocationInMap() {
        SharedPreferences sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String location = sharedPrefs.getString(
                getString(R.string.pref_location_key),
                getString(R.string.pref_location_default));

        Uri geoLocation = Uri.parse("geo:0,0?").buildUpon()
                .appendQueryParameter("q", location)
                .build();

        Intent mIntent = new Intent(Intent.ACTION_VIEW);
        mIntent.setData(geoLocation);

        if (mIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mIntent);
        } else {
            Log.i(TAG, "No receiving apps installed!");
            Toast.makeText(MainActivity.this, "No Map apps installed", Toast.LENGTH_SHORT).show();
        }
    }
}
