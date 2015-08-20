package pl.looksoft.lsportfolio.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pl.looksoft.lsportfolio.R;

/**
 * Created by karol on 2015-08-20.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryViewHolder> {
        List<String> mList;

        public GalleryAdapter(List<String> list) {
            mList = list;
        }

        @Override
        public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView;
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.gallery_item, parent, false);
            return new GalleryViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(GalleryViewHolder holder, int position) {
            holder.setupView(holder, mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

}
