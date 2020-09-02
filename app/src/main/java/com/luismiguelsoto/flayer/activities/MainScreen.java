package com.luismiguelsoto.flayer.activities;

import android.Manifest;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.luismiguelsoto.flayer.R;
import com.luismiguelsoto.flayer.fragments.BrowserFragment;
import com.luismiguelsoto.flayer.fragments.SongFinderFragment;
import com.luismiguelsoto.flayer.fragments.SongListFragment;

public class MainScreen extends AppCompatActivity {

    /**
     * Activity methods
     * */
    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentsContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    /**
     * Activity Listeners
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] requiredPermissions = { Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE };
        ActivityCompat.requestPermissions(this, requiredPermissions, 0);

        TabLayout navigation = findViewById(R.id.tabbedNavigation);

        if (navigation != null) {
            navigation.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    switch (tab.getPosition()) {
                        case 0:
                            openFragment(BrowserFragment.newInstance(null, null));
                            break;
                        case 1:
                            openFragment(SongListFragment.newInstance(null, null));
                            break;
                        case 2:
                            openFragment(SongFinderFragment.newInstance(null, null));
                            break;
                        default: break;
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });
        }

        // Set default fragment/tab
        if (navigation != null) {
            TabLayout.Tab yourMusicTab = navigation.getTabAt(1);

            if (yourMusicTab != null)
                yourMusicTab.select();
        }
    }
}