package pl.looksoft.lsportfolio.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.looksoft.lsportfolio.R;

/**
 * Created by karol on 2015-08-20.
 */
public class GalleryViewHolder extends RecyclerView.ViewHolder {

    @InjectView(R.id.image)
    ImageView image;

    public GalleryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }

    public void setupView(GalleryViewHolder holder, String url) {
        Picasso.with(holder.image.getContext()).load(url).into(holder.image);
    }

}
