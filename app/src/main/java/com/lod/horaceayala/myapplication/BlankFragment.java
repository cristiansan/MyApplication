package com.lod.horaceayala.myapplication;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    private View rootView;

    public BlankFragment() {
    }

    public BlankFragment(int color) {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        rootView.findViewById(R.id.xxx1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
//                rootView.findViewById(R.id.xxx1).setPressed(true);
//                rootView.findViewById(R.id.xxx1).setPressed(false);

                rootView.findViewById(R.id.xxx1).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity) getActivity()).btnSceneTest(v);
                    }
                }, 500);
//
            }
        });

        rootView.findViewById(R.id.xImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                // previously invisible view
                final View myView = rootView.findViewById(R.id.xxx);

//                // get the center for the clipping circle
//                int cx = (myView.getLeft() + myView.getRight()) / 2;
//                int cy = (myView.getTop() + myView.getBottom()) / 2;
//
//                // get the final radius for the clipping circle
//                int finalRadius = Math.max(myView.getWidth(), myView.getHeight());
//
//                // create the animator for this view (the start radius is zero)
//                Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
//
//                // make the view visible and start the animation
//                myView.setVisibility(View.VISIBLE);
//                anim.start();

                if (myView.getVisibility() == View.GONE) {
                    myView.setVisibility(View.VISIBLE);
                    ViewAnimationUtils.createCircularReveal(myView, myView.getWidth(), myView.getHeight(), 0, myView.getHeight() * 2).start();
                } else {
                    Animator anim = ViewAnimationUtils.createCircularReveal(myView, myView.getWidth(), myView.getHeight(), myView.getHeight() * 2, 0);
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            myView.setVisibility(View.GONE);
                        }
                    });
                    anim.start();
                }


            }
        });

        return rootView;
    }


}
