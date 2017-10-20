package com.example.fauziw97.taxapp;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fauziw97.taxapp.Adapter.SpeciesAdapter;
import com.example.fauziw97.taxapp.Model.SpeciesModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Fauziw97 on 9/12/17.
 */

public class FragmentFrog extends Fragment {

    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
    private RecyclerView.Adapter mAdapter;
    private GridLayoutManager mGridLayoutManager;
    private List<SpeciesModel> mSpeciesModels;

    @BindView(R.id.recycler_view_frog) RecyclerView mRecyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frog, container, false);
        ButterKnife.bind(this,view);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new FragmentFrog.GridSpacingItemDecoration(2, 30, true, 0));
        mGridLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);


        mSpeciesModels = new ArrayList<>();
        retrieveData();


    }

    private void retrieveData() {

        DatabaseReference frogData = mRef.child("Amfirep");
        Query frogList = frogData.orderByChild("Jenis").equalTo("Amphibi");
        frogList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    SpeciesModel listSpeciesModel = new SpeciesModel(
                            snapshot.child("NamaSpesies").getValue(String.class),
                            snapshot.child("StatusKonservasi").getValue(String.class));
                    //snapshot.child("img").getValue(String.class));


                    mSpeciesModels.add(listSpeciesModel);

                    mAdapter = new SpeciesAdapter(mSpeciesModels, getActivity());
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;
        private int headerNum;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge, int headerNum) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
            this.headerNum = headerNum;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view) - headerNum; // item position

            if (position >= 0) {
                int column = position % spanCount; // item column

                if (includeEdge) {
                    outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                    outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                    if (position < spanCount) { // top edge
                        outRect.top = spacing;
                    }
                    outRect.bottom = spacing; // item bottom
                } else {
                    outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                    outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                    if (position >= spanCount) {
                        outRect.top = spacing; // item top
                    }
                }
            } else {
                outRect.left = 0;
                outRect.right = 0;
                outRect.top = 0;
                outRect.bottom = 0;
            }
        }
    }
}