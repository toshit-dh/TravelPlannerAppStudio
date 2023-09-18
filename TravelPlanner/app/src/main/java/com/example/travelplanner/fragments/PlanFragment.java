package com.example.travelplanner.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.travelplanner.R;
import com.example.travelplanner.adapters.PlanTripChildFragmentAdapter;
import com.example.travelplanner.utils.OnFieldsChangeListener;

import java.util.ArrayList;
import java.util.List;

public class PlanFragment extends Fragment implements OnFieldsChangeListener{
    private Fragment currentFragment;
    private ViewPager2 viewPager;
    private Button previousPageButton;
    private Button nextPageButton;
    private int currentPage = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_plan, container, false);
        viewPager = rootView.findViewById(R.id.planviewpager);
        viewPager.setOffscreenPageLimit(1);
        previousPageButton = rootView.findViewById(R.id.backbutton);
        nextPageButton = rootView.findViewById(R.id.scroll);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new PCFDate());
        fragments.add(new PCFLocation());
        fragments.add(new PCFRoute());
        fragments.add(new PCFBook());

        com.example.travelplanner.adapters.PlanTripChildFragmentAdapter adapter = new com.example.travelplanner.adapters.PlanTripChildFragmentAdapter(this, fragments);
        viewPager.setAdapter(adapter);

        // Disable touch scrolling in the ViewPager2
        viewPager.setUserInputEnabled(false);


        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // Get the current fragment when the page changes
                currentFragment = fragments.get(position);
                onPageChanged(position);
            }
        });
        nextPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToNextPage();
            }
        });
        previousPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToPreviousPage();
            }
        });

        return rootView;
    }

    private void navigateToNextPage() {

        if (currentPage < viewPager.getAdapter().getItemCount() - 1) {
            currentPage++;
            viewPager.setCurrentItem(currentPage, true);
        }


    }
    private void navigateToPreviousPage() {

        if (currentPage > 0) {
            currentPage--;
            viewPager.setCurrentItem(currentPage, true);
        }
    }
    public void updateNextButtonState(boolean filled) {
        Log.d("PlanFragment", "updateNextButtonState called with filled: " + filled);
        Toast.makeText(getActivity(), "bus", Toast.LENGTH_SHORT).show();
        nextPageButton.setEnabled(filled);


    }
    private void updateBackButtonVisibility(){
        if (currentPage == 0) {
            previousPageButton.setVisibility(View.INVISIBLE);
        } else {
            previousPageButton.setVisibility(View.VISIBLE);
        }
    }
    private void onPageChanged(int newPosition) {
        currentPage = newPosition;
        updateBackButtonVisibility();
    }

    @Override
    public void onFieldsChanged(boolean filled) {
        updateNextButtonState(filled);
    }
//    if (currentFragment instanceof PCFDate) {
//        // Check if all fields in PCFDate are filled
//        boolean isFilled = ((PCFDate) currentFragment).isSelected();
//        if(!isFilled){
//            nextPageButton.setError("Fill Out All Fields");
//        }
//        nextPageButton.setEnabled(isFilled);
//    } else if (currentFragment instanceof PCFLocation) {
//        // Check if all fields in PCFLocation are filled
//        boolean isFilled = ((PCFLocation) currentFragment).isSelected();
//        nextPageButton.setEnabled(isFilled);
//    } else {
//        // Handle the case where the current fragment is not one of the known fragments
//        nextPageButton.setEnabled(false);
//    }







}
