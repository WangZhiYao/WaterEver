package spave.levan.waterever.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import spave.levan.waterever.db.DBHelper;
import spave.levan.waterever.model.Plant;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/16
 */
public class PlantsWithTagAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private DBHelper mDBHelper;
    private List<Plant> mPlantList;

    public PlantsWithTagAdapter(Context context) {
        mContext = context;
        mDBHelper = new DBHelper();
        mPlantList = mDBHelper.queryAllPlantsSortByTime();
    }

    public void addPlant(Plant plant) {
        mPlantList.add(0, plant);
        notifyItemInserted(0);
    }

    public void setPlantList(List<Plant> plantList) {
        mPlantList.addAll(plantList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mPlantList.size();
    }

    class PlantsWithTagViewHolder extends RecyclerView.ViewHolder {

        public PlantsWithTagViewHolder(View itemView) {
            super(itemView);
        }
    }
}
