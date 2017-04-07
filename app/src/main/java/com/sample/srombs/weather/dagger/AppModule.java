package com.sample.srombs.weather.dagger;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by srombs on 4/6/17.
 */
@Module
public class AppModule {

    Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides @Singleton
    public Context getContext(Application app) {
        return app.getApplicationContext();
    }

    @Provides @Singleton
    public SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("weather", Context.MODE_PRIVATE);
    }

}
