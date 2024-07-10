package com.example.newsapp.newsapp.di

import android.app.Application
import androidx.room.Room
import com.example.newsapp.newsapp.data.local.NewsDao
import com.example.newsapp.newsapp.data.local.NewsDataBase
import com.example.newsapp.newsapp.data.local.NewsTypeConverter
import com.example.newsapp.newsapp.data.manager.LocalUserManagerImp
import com.example.newsapp.newsapp.data.remote.dto.NewsApi
import com.example.newsapp.newsapp.data.repository.NewsRepositoryImp
import com.example.newsapp.newsapp.domain.manager.LocalUserManager
import com.example.newsapp.newsapp.domain.repository.NewsRepository
import com.example.newsapp.newsapp.domain.usecases.appEntry.AppEntryUseCases
import com.example.newsapp.newsapp.domain.usecases.appEntry.ReadAppEntry
import com.example.newsapp.newsapp.domain.usecases.appEntry.SaveAppEntry
import com.example.newsapp.newsapp.domain.usecases.news.remote.GetNews
import com.example.newsapp.newsapp.domain.usecases.news.remote.GetSearchNews
import com.example.newsapp.newsapp.domain.usecases.news.remote.GetSources
import com.example.newsapp.newsapp.domain.usecases.news.NewsUseCases
import com.example.newsapp.newsapp.domain.usecases.news.localdb.DeleteArticle
import com.example.newsapp.newsapp.domain.usecases.news.localdb.SelectArticle
import com.example.newsapp.newsapp.domain.usecases.news.localdb.SelectArticles
import com.example.newsapp.newsapp.domain.usecases.news.localdb.UpsertArticle
import com.example.newsapp.newsapp.domain.usecases.news.remote.GetCategory
import com.example.newsapp.newsapp.util.Constants.BASE_URL
import com.example.newsapp.newsapp.util.Constants.NEWS_DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManager = LocalUserManagerImp(context = application)


    @Provides
    @Singleton
    fun provideAppEntryUseCase(
        localUserManager: LocalUserManager
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager = localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(NewsApi::class.java)
    }


    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi, newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImp(
        newsApi, newsDao
    )


    @Provides
    @Singleton
    fun provideNewUseCases(
        newsRepository: NewsRepository,
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository = newsRepository),
            getSearchNews = GetSearchNews(newsRepository = newsRepository),
            getSources = GetSources(newsRepository = newsRepository),
            getCategory = GetCategory(newsRepository = newsRepository),

//            DB
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository),
        )
    }


    @Provides
    @Singleton
    fun provideNewsDataBase(
        application: Application
    ): NewsDataBase {
        return Room.databaseBuilder(
            context = application, klass = NewsDataBase::class.java, name = NEWS_DB
        ).addTypeConverter(NewsTypeConverter()).fallbackToDestructiveMigration().build()
    }


    @Provides
    @Singleton
    fun provideNewsDao(
        newsDataBase: NewsDataBase
    ): NewsDao = newsDataBase.newsDao


}