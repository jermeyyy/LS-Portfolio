package pl.looksoft.lsportfolio.base;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.LinkedList;
import java.util.List;

import pl.looksoft.lsportfolio.R;
import pl.looksoft.lsportfolio.fragment.ifaces.FragmentChangeListener;

/**
 * Created by Jermey on 2015-08-19.
 */
public abstract class BaseActivity extends AppCompatActivity implements FragmentChangeListener {

    protected Application app;
    private List<AsyncTask> tasks = new LinkedList<>();

    protected void registerTask(AsyncTask task) {
        tasks.add(task);
    }

    @Override
    protected void onStop() {
        super.onStop();
        for (AsyncTask task : tasks)
            task.cancel(true);
        tasks.clear();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = getApplication();
    }


    @Override
    public void onFragmentChange(Fragment fragment) {
        onFragmentChange(fragment, 0);
    }

    @Override
    public void onFragmentChange(Fragment fragment, int flags) {
        String backStackName = null;
        boolean addToBackStack = true;

        if ((flags & FragmentChangeListener.FLAG_CLEAR_BACKSTACK) != 0) {
            FragmentManager manager = getSupportFragmentManager();
            if (manager.getBackStackEntryCount() > 0) {
                FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
                manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        }
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.container, fragment);
        backStackName = "up";

        if ((flags & FragmentChangeListener.FLAG_NO_BACKSTACK) != 0) {
            addToBackStack = false;
        }

        if (addToBackStack) {
            transaction.addToBackStack(backStackName);
        }
        try {
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onFragmentChange(Fragment leftFragment, Fragment rightFragment, int flags) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onFragmentAttached(Fragment fragment) {

    }

    @Override
    public Context getContext() {
        return this;
    }

}
