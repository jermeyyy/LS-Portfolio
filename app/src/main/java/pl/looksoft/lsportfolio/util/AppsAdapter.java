package pl.looksoft.lsportfolio.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pl.looksoft.lsportfolio.R;
import pl.looksoft.lsportfolio.model.Portfolio;

/**
 * Created by Jermey on 2015-08-18.
 */
public class AppsAdapter extends RecyclerView.Adapter<AppsViewHolder>{

    List<Portfolio> mList;

    public AppsAdapter(List<Portfolio> list) {
        setHasStableIds(true);
        this.mList = list;
    }

    @Override
    public AppsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.app_item, parent, false);

        return new AppsViewHolder(itemView, viewType);
    }

    @Override
    public long getItemId(int position) {
        return mList.get(position).getId();
    }

    @Override
    public void onBindViewHolder(AppsViewHolder holder, int position) {
        AppsViewHolder.setupView(holder, mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
