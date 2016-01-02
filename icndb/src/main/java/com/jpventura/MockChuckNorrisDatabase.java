/*
 * Copyright 2015 JP Ventura
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jpventura;

import java.util.Arrays;

public class MockChuckNorrisDatabase implements IChuckNorrisDatabase {
    @Override
    public ResponsePage<Joke> getRandomJoke() {
        ResponsePage<Joke> responsePage = new ResponsePage();
        responsePage.type = "success";

        responsePage.value = new Joke();
        responsePage.value.id = 487L;
        responsePage.value.joke = "No statement can catch the ChuckNorrisException.";
        responsePage.value.categories = Arrays.asList("nerdy");

        return responsePage;
    }
}
