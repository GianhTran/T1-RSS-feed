package com.example.framgia.t1_rss_feed;

import android.app.Fragment;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 23/08/2016.
 */
public class BaseFragment extends Fragment {
    protected void replaceFragment(int containerViewId, Fragment fragment) {
        getActivity().getFragmentManager().beginTransaction()
            .addToBackStack(null)
            .replace(containerViewId, fragment)
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .commitAllowingStateLoss();
    }
}
