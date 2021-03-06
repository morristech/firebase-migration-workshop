/*
 * Copyright (C) 2015 Karumi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.malmstein.workshops.firebase.di;


import android.content.Context;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.malmstein.workshops.firebase.analytics.AnalyticsTracking;
import com.malmstein.workshops.firebase.analytics.FirebaseTracking;
import com.malmstein.workshops.firebase.crash.CrashTracker;
import com.malmstein.workshops.firebase.crash.FirebaseCrashTracker;
import com.malmstein.workshops.firebase.model.RemoteConfigRepository;
import com.malmstein.workshops.firebase.model.SuperHeroesRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    @Singleton
    public SuperHeroesRepository provideSuperHeroesRepository() {
        return new SuperHeroesRepository();
    }

    @Provides
    @Singleton
    public AnalyticsTracking provideAnalytics(Context context) {
        return new FirebaseTracking(context);
    }

    @Provides
    @Singleton
    public CrashTracker provideCrashReporting() {
        return new FirebaseCrashTracker();
    }

    @Provides
    @Singleton
    public RemoteConfigRepository provideRemoteConfigRepository() {
        return new RemoteConfigRepository(FirebaseRemoteConfig.getInstance());
    }
}
