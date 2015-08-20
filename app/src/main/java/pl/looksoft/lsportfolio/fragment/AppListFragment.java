package pl.looksoft.lsportfolio.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.looksoft.lsportfolio.R;
import pl.looksoft.lsportfolio.api.RestClient;
import pl.looksoft.lsportfolio.fragment.base.BaseFragment;
import pl.looksoft.lsportfolio.model.AppItem;
import pl.looksoft.lsportfolio.model.BaseResponse;
import pl.looksoft.lsportfolio.model.Portfolio;
import pl.looksoft.lsportfolio.util.AppsAdapter;

/**
 * Created by Jermey on 2015-08-18.
 */
public class AppListFragment extends BaseFragment {

    @InjectView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @InjectView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    List<Portfolio> mAppList;

    public static Fragment getInstance() {
        Fragment fragment = new AppListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_applist, container, false);
        ButterKnife.inject(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setupRecyclerView();
            }
        });
        setupRecyclerView();
    }



    public void setupRecyclerView() {

        registerTask(new AsyncTask<Void, Void, Void>() {
            BaseResponse<AppItem> response;

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    response = RestClient.getInstance(getContext().getApplicationContext()).mService.getAppsList();
                } catch (Exception e) {
                    Log.e("LSPortfolio", e.toString());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                if (response != null)
                    if (response.getResponse() != null) {
                        List<Portfolio> list = new ArrayList<>();
                        list.addAll(response.getResponse().getData().getPortfolio());
                        AppsAdapter appsAdapter = new AppsAdapter(list);
                        mRecyclerView.setAdapter(appsAdapter);
                    }
            }

        }.execute());
    }
}
