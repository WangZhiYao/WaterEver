package me.zhiyao.waterever.data.repo

import me.zhiyao.waterever.data.db.dao.ImageDao
import me.zhiyao.waterever.data.db.model.Image

/**
 *
 * @author WangZhiYao
 * @date 2020/9/12
 */
class ImageRepository(
    private val imageDao: ImageDao
) : Repository {

    suspend fun addImages(images: List<Image>) {
        imageDao.insertBatch(images)
    }
}