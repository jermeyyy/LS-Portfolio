package pl.looksoft.lsportfolio;

import android.content.Intent;
import android.drm.DrmStore;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import pl.looksoft.lsportfolio.api.RestClient;
import pl.looksoft.lsportfolio.base.BaseActivity;
import pl.looksoft.lsportfolio.model.AppDetail;
import pl.looksoft.lsportfolio.model.BaseResponse;
import pl.looksoft.lsportfolio.model.Link;
import pl.looksoft.lsportfolio.util.GalleryAdapter;

/**
 * Created by Jermey on 2015-08-18.
 */
public class DetailsActivity extends BaseActivity{

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.app_logo)
    ImageView appLogo;
    @InjectView(R.id.app_name)
    TextView appName;
    @InjectView(R.id.description)
    TextView appDescription;
    @InjectView(R.id.gallery)
    RecyclerView gallery;
    @InjectView(R.id.store_links)
    LinearLayout storeLinksContainer;

    List<Link> storeLinks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.inject(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon);

        gallery.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        registerTask(new AsyncTask<Void, BaseResponse<AppDetail>, BaseResponse<AppDetail>>() {
            @Override
            protected BaseResponse<AppDetail> doInBackground(Void[] params) {
                try {
                    return RestClient.getInstance(getApplicationContext()).mService.getAppDetails(getIntent().getLongExtra("appId", 0));
                } catch (Exception e) {
                    Log.e("LSPortfolio", e.toString());
                }
                return null;
            }

            @Override
            protected void onPostExecute(BaseResponse<AppDetail> response) {
                super.onPostExecute(response);
                if(response != null) {
                    Picasso.with(DetailsActivity.this).load(response.getData().getIcon()).into(appLogo);
                    appName.setText(response.getData().getName());
                    appDescription.setText(response.getData().getDescription());
                    gallery.setAdapter(new GalleryAdapter(response.getData().getGallery()));
                    for(Link item : response.getData().getLink()) {
                        View v = getLayoutInflater().inflate(R.layout.store_item, storeLinksContainer, false);
                        v.setTag(item.getUrl());
                        int defaultPadding = getResources().getDimensionPixelSize(R.dimen.default_padding);
                        v.setPadding(defaultPadding,defaultPadding,defaultPadding,defaultPadding);
                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                openStore((String) v.getTag());
                            }
                        });
                        storeLinksContainer.addView(v);
                        Picasso.with(v.getContext()).load(item.getImage()).into((ImageView) v);
                    }
                }
            }
        }.execute());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openStore(String url) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception e) {
            Log.e("LSPortfolio", e.toString());
        }
    }

}
