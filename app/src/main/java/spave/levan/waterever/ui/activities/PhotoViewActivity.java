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

import java.util.ArrayList;
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

    private static final String TAG = "PhotoViewActivity";

    @BindView(R.id.photoView_RecyclerView)
    RecyclerView mRecyclerView;

    private ActionBar mActionBar;

    private int mPhotoPosition;
    private List<String> mPhotoPathList;
    private PhotoViewAdapter mPhotoViewAdapter;
    private LinearLayoutManager mLayoutManager;

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
            showToast(R.string.photo_view_wrong_photo_path);
            finish();
        }
    }

    private void initView() {
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setHomeButtonEnabled(true);
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setTitle(String.format("%s/%s", mPhotoPosition + 1, mPhotoPathList.size()));
        }

        mPhotoViewAdapter = new PhotoViewAdapter(R.layout.item_photo_view);
        mPhotoViewAdapter.addData(mPhotoPathList);

        mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mPhotoViewAdapter);
        mRecyclerView.scrollToPosition(mPhotoPosition);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mPhotoPosition = mLayoutManager.findFirstVisibleItemPosition();
                if (mActionBar != null) {
                    if (mPhotoPosition + 1 <= mPhotoViewAdapter.getData().size()) {
                        mActionBar.setTitle(String.format("%s/%s", mPhotoPosition + 1,
                                mPhotoViewAdapter.getData().size()));
                    }
                }
            }
        });

        new PagerSnapHelper().attachToRecyclerView(mRecyclerView);
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

                mPhotoViewAdapter.remove(mPhotoPosition);

                setResult(RESULT_OK, new Intent()
                        .putStringArrayListExtra(Constants.EXTRA_PHOTO_PATH_LIST,
                                new ArrayList<>(mPhotoViewAdapter.getData())));

                if (mPhotoViewAdapter.getData().isEmpty()) {
                    finish();
                }

                if (mActionBar != null) {
                    if (mPhotoPosition + 1 <= mPhotoViewAdapter.getData().size()) {
                        mActionBar.setTitle(String.format("%s/%s", mPhotoPosition + 1,
                                mPhotoViewAdapter.getData().size()));
                    }
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
