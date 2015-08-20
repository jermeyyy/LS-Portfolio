package pl.looksoft.lsportfolio.util;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.looksoft.lsportfolio.DetailsActivity;
import pl.looksoft.lsportfolio.R;
import pl.looksoft.lsportfolio.model.Portfolio;

/**
 * Created by Jermey on 2015-08-18.
 */
public class AppsViewHolder extends RecyclerView.ViewHolder {

    @InjectView(R.id.app_title)
    TextView title;
    @InjectView(R.id.app_image)
    ImageView image;

    public AppsViewHolder(View itemView, int viewType) {
        super(itemView);
        ButterKnife.inject(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), DetailsActivity.class).putExtra("appId", getItemId()));
            }
        });
    }

    public static void setupView(AppsViewHolder holder, Portfolio item) {
        Picasso.with(holder.image.getContext()).load(item.getIcon()).fit().into(holder.image);
        holder.title.setText(item.getName());
    }
}
