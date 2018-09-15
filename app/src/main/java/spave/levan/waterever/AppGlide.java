package spave.levan.waterever;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/15
 */
@GlideModule
public class AppGlide extends AppGlideModule {

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        int memoryCacheSizeBytes = 1024 * 1024 * 20;
        builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes));

        int bitmapPoolSizeBytes = 1024 * 1024 * 30;
        builder.setBitmapPool(new LruBitmapPool(bitmapPoolSizeBytes));

        int diskCacheSizeBytes = 1024 * 1024 * 100;
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, diskCacheSizeBytes));

        builder.setDefaultRequestOptions(new RequestOptions()
                .format(DecodeFormat.PREFER_RGB_565));

        builder.setLogLevel(Log.DEBUG);
    }
}
