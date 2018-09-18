package spave.levan.waterever.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import spave.levan.waterever.GlideApp;
import spave.levan.waterever.R;
import spave.levan.waterever.utils.StringUtils;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/18
 */
public class PhotoViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<String> mPhotoPathList;

    public PhotoViewAdapter(Context context) {
        mContext = context;
        mPhotoPathList = new ArrayList<>();
    }

    public void setPhotoPathList(List<String> photoPathList) {
        mPhotoPathList.clear();
        mPhotoPathList.addAll(photoPathList);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        mPhotoPathList.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhotoViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_photo_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String photoPath = mPhotoPathList.get(position);
        if (!StringUtils.isNullOrEmpty(photoPath)) {
            GlideApp.with(mContext)
                    .load(photoPath)
                    .centerCrop()
                    .transition(new DrawableTransitionOptions().crossFade())
                    .into(((PhotoViewHolder) holder).mPhotoView);
        }
    }

    @Override
    public int getItemCount() {
        return mPhotoPathList.size();
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_PhotoView)
        PhotoView mPhotoView;

        PhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
