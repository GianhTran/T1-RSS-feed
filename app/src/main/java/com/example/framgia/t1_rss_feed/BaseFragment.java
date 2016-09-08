package com.example.framgia.t1_rss_feed;

import android.app.Fragment;
import android.app.FragmentManager;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 23/08/2016.
 */
public class BaseFragment extends Fragment {
    protected void replaceFragment(int containerViewId, Fragment fragment) {
        getActivity().getFragmentManager().beginTransaction()
            .addToBackStack(Constants.FRAGMENT_TAG)
            .replace(containerViewId, fragment)
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .commitAllowingStateLoss();
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        if (fragmentManager == null) return;
        fragmentManager.beginTransaction()
            .addToBackStack(Constants.FRAGMENT_TAG)
            .add(containerViewId, fragment)
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .commitAllowingStateLoss();
    }

    protected void removeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        if (fragmentManager == null) return;
        fragmentManager.beginTransaction()
            .addToBackStack(Constants.FRAGMENT_TAG)
            .remove(fragment)
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .commitAllowingStateLoss();
    }
}
