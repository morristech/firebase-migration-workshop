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

package com.malmstein.workshops.firebase.usecase;

import android.os.Handler;
import android.os.Looper;

import com.malmstein.workshops.firebase.model.SuperHero;
import com.malmstein.workshops.firebase.model.SuperHeroesRepository;

import java.util.List;

import javax.inject.Inject;

public class GetSuperHeroes {

    private final SuperHeroesRepository repository;

    @Inject
    public GetSuperHeroes(SuperHeroesRepository repository) {
        this.repository = repository;
    }

    public void getAll(final Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                loadSuperHeroes(callback);
            }
        }).start();
    }

    private void loadSuperHeroes(final Callback callback) {
        final List<SuperHero> superHeroes = repository.getAll();
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                callback.onSuperHeroesLoaded(superHeroes);
            }
        });
    }

    public interface Callback {

        void onSuperHeroesLoaded(List<SuperHero> superHeroes);
    }
}
