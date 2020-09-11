package me.zhiyao.waterever.data.repo

import me.zhiyao.waterever.data.db.dao.TagDao
import me.zhiyao.waterever.data.db.model.Tag

/**
 *
 * @author WangZhiYao
 * @date 2020/9/12
 */
class TagRepository(
    private val tagDao: TagDao
) : Repository {

    fun getTags() = tagDao.queryAll()

    suspend fun addTag(tag: Tag) = tagDao.insert(tag)

    suspend fun deleteTags(tags: List<Tag>) {
        tagDao.deleteBatch(tags)
    }

    fun queryTagsById(tagIds: List<Long>) = tagDao.queryByIds(tagIds)
}