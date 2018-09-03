package com.slewsoft.criminalintent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CrimeListFragment extends Fragment {
    private RecyclerView crimeRecyclerView;
    private CrimeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
       View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
       crimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
       crimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       updateUI();
       return view;
    }

    private void updateUI() {
        CrimeLab lab = CrimeLab.get(getActivity());
        List<Crime> crimes = lab.getCrimes();

        adapter = new CrimeAdapter(crimes);
        crimeRecyclerView.setAdapter(adapter);
    }

    private class CrimeHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView dateTextView;
        private Crime crime;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));
            titleTextView = itemView.findViewById(R.id.crime_title);
            dateTextView = itemView.findViewById(R.id.crime_date);
        }

        public void bind(Crime c) {
            this.crime = c;
            titleTextView.setText(crime.getTitle());
            dateTextView.setText(crime.getDate().toString());
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> crimes;

        public CrimeAdapter(List<Crime> crimes) { this.crimes = crimes; }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int i) {
            Crime crime = crimes.get(i);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() { return crimes.size(); }
    }
}
