package com.lsuncar.notepad.core.results;

public class ErrorResult extends Result {
    public ErrorResult(boolean success) {
        super(false);
    }

    public ErrorResult(String message) {
        super(false, message);
    }
}
