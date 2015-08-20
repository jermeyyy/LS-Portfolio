package pl.looksoft.lsportfolio.fragment.base;

import android.app.Activity;
import android.app.Application;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.LinkedList;
import java.util.List;

import pl.looksoft.lsportfolio.fragment.ifaces.FragmentChangeListener;

/**
 * Created by Jermey on 2015-08-19.
 */
public class BaseFragment extends Fragment {

    protected Application app;
    private List<AsyncTask> tasks = new LinkedList<>();

    protected FragmentChangeListener fragmentChangeListener;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Activity activity = getActivity();
        if(activity instanceof FragmentChangeListener)
            fragmentChangeListener = (FragmentChangeListener) activity;
        else
            throw new RuntimeException("Activity must implement FragmentChangeListener");
        if(getArguments() == null || !getArguments().getBoolean("doNotReportAttached"))
            fragmentChangeListener.onFragmentAttached(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        app = activity.getApplication();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentChangeListener = null;
        app = null;
    }

    protected void registerTask(AsyncTask task) {
        tasks.add(task);
    }

    @Override
    public void onStop() {
        stopTasks();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        stopTasks();
        super.onDestroyView();
    }

    private void stopTasks() {
        for(AsyncTask task : tasks)
            task.cancel(true);
        tasks.clear();
    }

}