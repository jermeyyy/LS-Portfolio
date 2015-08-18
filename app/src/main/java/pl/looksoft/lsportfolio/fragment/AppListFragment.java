package pl.looksoft.lsportfolio.fragment;

import android.accounts.OperationCanceledException;
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

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.looksoft.lsportfolio.R;
import pl.looksoft.lsportfolio.api.RestClient;
import pl.looksoft.lsportfolio.model.AppItem;
import pl.looksoft.lsportfolio.model.BaseResponse;
import pl.looksoft.lsportfolio.model.Portfolio;
import pl.looksoft.lsportfolio.util.AppsAdapter;

/**
 * Created by Jermey on 2015-08-18.
 */
public class AppListFragment extends Fragment {

    @InjectView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @InjectView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    List<Portfolio> mAppList;
    private OkHttpClient mHttpClient;

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
//        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                setupRecyclerView();
//            }
//        });
        setupRecyclerView();
    }

    private Response request(Request.Builder reqBuilder) throws IOException, OperationCanceledException {

        mHttpClient = new OkHttpClient();
        Request req = reqBuilder.build();

        return mHttpClient.newCall(req).execute();
    }



    public void setupRecyclerView() {

        AsyncTask<Void, Void, BaseResponse<AppItem>> task = new AsyncTask<Void, Void, BaseResponse<AppItem>>() {

            @Override
            protected BaseResponse<AppItem> doInBackground(Void... params) {
                BaseResponse<AppItem> response = null;
                try {
                    Request.Builder rb = new Request.Builder();
                    rb.url("http://looksoft.pl/api/main")
                            .get();
                    Response res = request(rb);
                    String body = res.body().string();
                    Log.d("RESBODY", res.body().string());
                } catch (Exception e) {
                    Log.e("LSPortfolio", e.toString());
                }
                try{
                    response = RestClient.getInstance(getActivity()).mService.getApps();
                } catch (Exception e) {
                    Log.e("LSPortfolio", e.toString());
                }
                return response;
            }

            @Override
            protected void onPostExecute(BaseResponse<AppItem> result) {
                super.onPostExecute(result);
                if(result != null) {
                    List<Portfolio> list = new ArrayList<>();
                    list.addAll(result.getResponse().getData().getPortfolio());
                    AppsAdapter appsAdapter = new AppsAdapter(list);
                    mRecyclerView.setAdapter(appsAdapter);
                }
            }

        };
        task.execute();
    }
}
