package com.example.onchaininvesting.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.onchaininvesting.R;
import com.example.onchaininvesting.adapters.PageViewerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewPager);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        PageViewerAdapter adapter = new PageViewerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_vault:
                    viewPager.setCurrentItem(0);
                    break;

                case R.id.menu_transaction:
                    viewPager.setCurrentItem(1);
                    break;

                case R.id.menu_profile:
                    viewPager.setCurrentItem(2);
                    break;
            }
            return true;
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.menu_vault);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.menu_transaction);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.menu_profile);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
