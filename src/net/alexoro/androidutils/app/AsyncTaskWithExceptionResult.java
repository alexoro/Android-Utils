package net.alexoro.androidutils.ui;

/**
 * User: UAS
 * Date: 05.06.13
 * Time: 1:18
 */
class AsyncTaskWithExceptionResult<Result> {

    public Result result;
    public Exception throwable;

    public AsyncTaskWithExceptionResult(Result result) {
        this.result = result;
    }

    public AsyncTaskWithExceptionResult(Exception throwable) {
        this.throwable = throwable;
    }

    public boolean hasError() {
        return throwable != null;
    }

}