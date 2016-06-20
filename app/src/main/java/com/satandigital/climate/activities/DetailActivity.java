package com.satandigital.climate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.satandigital.climate.R;
import com.satandigital.climate.fragments.DetailActivityFragment;

/**
 * Project : Climate
 * Created by Sanat Dutta on 6/10/2016.
 */
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        attachFragment(savedInstanceState);
    }

    private void attachFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailActivityFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(DetailActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
