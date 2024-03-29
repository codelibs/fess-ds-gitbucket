/*
 * Copyright 2012-2023 CodeLibs Project and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.codelibs.fess.ds.gitbucket;

import org.codelibs.fess.util.ComponentUtil;
import org.dbflute.utflute.lastadi.ContainerTestCase;

public class GitBucketDataStoreTest extends ContainerTestCase {
    public GitBucketDataStore dataStore;

    @Override
    protected String prepareConfigFile() {
        return "test_app.xml";
    }

    @Override
    protected boolean isSuppressTestCaseTransaction() {
        return true;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        dataStore = new GitBucketDataStore();
    }

    @Override
    public void tearDown() throws Exception {
        ComponentUtil.setFessConfig(null);
        super.tearDown();
    }

    public void test_encode() {
        String rootURL = "https://gitbucket.com/";
        String path;
        String query;

        path = "api/v3/repos/";
        query = "aaa=111";
        assertEquals("https://gitbucket.com/api/v3/repos/?aaa=111", dataStore.encode(rootURL, path, query));

        path = "api/v3/repos/AA BB";
        query = "aaa=11 11";
        assertEquals("https://gitbucket.com/api/v3/repos/AA%20BB?aaa=11%2011", dataStore.encode(rootURL, path, query));
    }
}
