package me.zhiyao.waterever.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import me.zhiyao.waterever.data.db.dao.*
import me.zhiyao.waterever.data.mapper.HomeItemMapper
import me.zhiyao.waterever.data.repo.PlantRepository
import javax.inject.Singleton

/**
 *
 * @author WangZhiYao
 * @date 2020/7/18
 */
@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePlantRepository(
        plantDao: PlantDao,
        categoryDao: CategoryDao,
        tagDao: TagDao,
        growthRecordDao: GrowthRecordDao,
        plantTagRelationDao: PlantTagRelationDao
    ): PlantRepository {
        return PlantRepository(
            plantDao,
            categoryDao,
            tagDao,
            growthRecordDao,
            plantTagRelationDao,
            HomeItemMapper()
        )
    }
}