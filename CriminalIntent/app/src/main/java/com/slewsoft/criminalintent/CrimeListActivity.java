package com.slewsoft.criminalintent;

import android.support.v4.app.Fragment;

/**
 * 1-1 Relationship with CrimeListFragment
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}