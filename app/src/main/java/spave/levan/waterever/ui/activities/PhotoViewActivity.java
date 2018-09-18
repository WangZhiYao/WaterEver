package spave.levan.waterever.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import spave.levan.waterever.Constants;
import spave.levan.waterever.R;
import spave.levan.waterever.ui.adapters.PhotoViewAdapter;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/18
 */
public class PhotoViewActivity extends BaseActivity {

    @BindView(R.id.photoView_RecyclerView)
    RecyclerView mRecyclerView;

    private int mPhotoPosition;
    private List<String> mPhotoPathList;
    private PhotoViewAdapter mPhotoViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        ButterKnife.bind(this);

        initData();
        initView();
    }

    private void initData() {

        Intent intent = getIntent();

        mPhotoPathList = intent.getStringArrayListExtra(Constants.EXTRA_PHOTO_PATH_LIST);
        mPhotoPosition = intent.getIntExtra(Constants.EXTRA_PHOTO_POSITION, 0);

        if (mPhotoPathList == null || mPhotoPathList.isEmpty()) {
            Toast.makeText(this, R.string.photo_view_wrong_photo_path, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void initView() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(String.format("%s/%s", mPhotoPosition + 1, mPhotoPathList.size()));
        }

        mPhotoViewAdapter = new PhotoViewAdapter(this);
        mPhotoViewAdapter.setPhotoPathList(mPhotoPathList);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(mPhotoViewAdapter);
        mRecyclerView.scrollToPosition(mPhotoPosition);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (actionBar != null) {
                    if (mPhotoPosition + 1 <= mPhotoPathList.size()) {
                        actionBar.setTitle(String.format("%s/%s", mPhotoPosition + 1,
                                mPhotoPathList.size()));
                    }
                }
            }
        });

        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper() {
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
                mPhotoPosition = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
                return mPhotoPosition;
            }
        };
        pagerSnapHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_photo_view, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_Delete:
                //mPhotoViewAdapter.remove(mPhotoPosition);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
