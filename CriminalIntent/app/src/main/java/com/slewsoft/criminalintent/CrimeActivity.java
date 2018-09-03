package com.slewsoft.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * 1-1 Relationship with CrimeActivity
 */
public class CrimeActivity extends SingleFragmentActivity {
    private static final String EXTRA_CRIME_ID = "EXTRA_CRIME_ID";

    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimeActivity.class);

        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }
}
