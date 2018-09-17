package spave.levan.waterever.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import spave.levan.waterever.GlideApp;
import spave.levan.waterever.R;
import spave.levan.waterever.model.Plant;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/17
 */
public class PlantListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Plant> mPlantList;

    public PlantListAdapter(Context context) {
        mContext = context;
        mPlantList = new ArrayList<>();
    }

    public void setPlantList(List<Plant> plantList) {
        mPlantList.clear();
        mPlantList.addAll(plantList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlantViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_plant, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position > mPlantList.size() - 1) {
            return;
        }

        Plant plant = mPlantList.get(position);
        if (plant != null) {
            ((PlantViewHolder) holder).mPlantName.setText(plant.getName());
            GlideApp.with(mContext)
                    .load(plant.getCover())
                    .centerCrop()
                    .into(((PlantViewHolder) holder).mPlantCover);
        }
    }

    @Override
    public int getItemCount() {
        return mPlantList.size();
    }

    class PlantViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_PlantName)
        TextView mPlantName;
        @BindView(R.id.item_PlantCover)
        ImageView mPlantCover;

        PlantViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
