package net.alexoro.androidutils.ui;

import android.os.AsyncTask;

/**
 * User: UAS
 * Date: 05.06.13
 * Time: 1:18
 */
abstract public class AsyncTaskWithException<Params, Progress, Result>
        extends AsyncTask<Params, Progress, AsyncTaskWithExceptionResult<Result>> {

    @Override
    final protected AsyncTaskWithExceptionResult<Result> doInBackground(Params... params) {
        try {
            Result r = doInBackgroundImpl(params);
            return new AsyncTaskWithExceptionResult<Result>(r);
        } catch (Exception ex) {
            return new AsyncTaskWithExceptionResult<Result>(ex);
        }
    }

    @Override
    final protected void onPostExecute(AsyncTaskWithExceptionResult<Result> r) {
        if (r.hasError()) {
            onErrorExecute(r.throwable);
        } else {
            onSuccessExecute(r.result);
        }
    }

    abstract protected Result doInBackgroundImpl(Params... params);

    protected void onSuccessExecute(Result result) {

    }

    protected void onErrorExecute(Exception exception) {

    }

}