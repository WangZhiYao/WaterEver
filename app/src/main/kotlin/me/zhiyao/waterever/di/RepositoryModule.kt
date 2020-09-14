package me.zhiyao.waterever.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import me.zhiyao.waterever.data.db.dao.*
import me.zhiyao.waterever.data.mapper.HomeItemMapper
import me.zhiyao.waterever.data.repo.*
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
    fun providePlantRepository(plantDao: PlantDao) =
        PlantRepository(plantDao)

    @Provides
    @Singleton
    fun provideCategoryRepository(categoryDao: CategoryDao) =
        CategoryRepository(categoryDao)

    @Provides
    @Singleton
    fun provideTagRepository(tagDao: TagDao) =
        TagRepository(tagDao)

    @Provides
    @Singleton
    fun providePlantTagRelationRepository(plantTagRelationDao: PlantTagRelationDao) =
        PlantTagRelationRepository(plantTagRelationDao)

    @Provides
    @Singleton
    fun provideGrowthRecordRepository(growthRecordDao: GrowthRecordDao) =
        GrowthRecordRepository(growthRecordDao, HomeItemMapper())

    @Provides
    @Singleton
    fun providePlantGrowthRecordRelationRepository(
        plantGrowthRecordRelationDao: PlantGrowthRecordRelationDao
    ) =
        PlantGrowthRecordRelationRepository(plantGrowthRecordRelationDao)

    @Provides
    @Singleton
    fun provideImageRepository(imageDao: ImageDao) =
        ImageRepository(imageDao)
}