package pl.looksoft.lsportfolio.fragment.ifaces;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by Jermey on 2015-08-19.
 */
public interface FragmentChangeListener {

    public static final int FLAG_RIGHT_FRAGMENT = 1;
    public static final int FLAG_NO_BACKSTACK = 2;
    public static final int FLAG_CLEAR_RIGHT = 4;
    public static final int FLAG_NO_BACKSTACK_ON_RIGHT = 8;
    public static final int FLAG_CLEAR_BACKSTACK = 16;

    void onFragmentChange(Fragment fragment);

    void onFragmentChange(Fragment fragment, int flags);

    void onFragmentChange(Fragment leftFragment, Fragment rightFragment, int flags);

    void onFragmentAttached(Fragment fragment);

    Context getContext();
}