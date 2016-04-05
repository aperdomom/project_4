package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.v4.util.Pair;
import android.test.AndroidTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class EndpointAsyncTaskTest extends AndroidTestCase {
    CountDownLatch signal;
    String mResult;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        signal = new CountDownLatch(1);
    }

    public void testEndpointTask() throws Throwable {
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask() {
            @Override
            public void onPostExecute(String result) {
                mResult = result;
                signal.countDown();
            }
        };
        endpointsAsyncTask.execute(new Pair<Context, String>(getContext(), ""));
        signal.await(30, TimeUnit.SECONDS);
        assertNotNull(mResult);
        assertFalse(mResult.isEmpty());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        signal.countDown();
    }
}