package com.lsuncar.notepad.core.results;

public class DataResult<T> extends Result {
    private final T data;

    public DataResult(T data, Boolean success) {
        super(success);
        this.data = data;
    }

    public DataResult(T data, Boolean success, String message) {
        super(success, message);
        this.data = data;
    }

    public T getData() {
        return this.data;
    }
}
