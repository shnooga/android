package com.slewsoft.tabxperim;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class TabbedFragments extends AppCompatActivity {
    private static final String TAG="MainActivity";

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_fragments);

        Log.d(TAG, "onCreate: Starting");

        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());

        adapter.addFragment(new PreSiteFragment(), "Presite");
        adapter.addFragment(new Tab2Fragment(), "Job Detail");
        adapter.addFragment(new SelectImageFragment(), "Select/Save Image");
        adapter.addFragment(new RetrieveImageFragment(), "Retrieve Image");
        viewPager.setAdapter(adapter);
    }

}
