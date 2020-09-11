package me.zhiyao.waterever.data.repo

import me.zhiyao.waterever.data.db.dao.CategoryDao
import me.zhiyao.waterever.data.db.model.Category

/**
 *
 * @author WangZhiYao
 * @date 2020/9/12
 */
class CategoryRepository(
    private val categoryDao: CategoryDao
) : Repository {

    fun getCategories() = categoryDao.queryAll()

    suspend fun addCategory(category: Category) = categoryDao.insert(category)

    suspend fun deleteCategories(categories: List<Category>) {
        categoryDao.deleteBatch(categories)
    }

    fun queryCategoryById(categoryId: Long) = categoryDao.queryById(categoryId)
}