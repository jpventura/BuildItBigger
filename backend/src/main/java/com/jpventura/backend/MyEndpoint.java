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
package com.jpventura.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.jpventura.ChuckNorrisDatabase;
import com.jpventura.IChuckNorrisDatabase;

@Api(
        name = "jokes",
        version = "v1",
        audiences = {
                Constants.WEB_CLIENT_ID,
                "280863524374-dls5eufh0ad9s8a72pcmdmes2n6uo19g.apps.googleusercontent.com",
                "280863524374-ta1iojofh9mkltd6aooo9efm8kkf37t0.apps.googleusercontent.com",
                Constants.ANDROID_CLIENT_ID
        },
        clientIds = {
                Constants.ANDROID_CLIENT_ID,
                Constants.API_EXPLORER_CLIENT_ID,
                Constants.WEB_CLIENT_ID,
                "280863524374-dls5eufh0ad9s8a72pcmdmes2n6uo19g.apps.googleusercontent.com",
                "280863524374-ta1iojofh9mkltd6aooo9efm8kkf37t0.apps.googleusercontent.com"
        },
        scopes = {
                Constants.API_EMAIL_SCOPE
        },
        namespace = @ApiNamespace(
                ownerDomain = Constants.OWNER_DOMAIN,
                ownerName = Constants.OWNER_DOMAIN,
                packagePath = ""
        )
)
public class MyEndpoint {
    private IChuckNorrisDatabase mChuckNorrisDatabase;

    public MyEndpoint() {
        mChuckNorrisDatabase = ChuckNorrisDatabase.getInstance();
    }

    @ApiMethod(name = "getJoke", path = "jokes", httpMethod = ApiMethod.HttpMethod.GET)
    public JokeBean getJoke() {
        return new JokeBean(mChuckNorrisDatabase.getRandomJoke().value);
    }
}
