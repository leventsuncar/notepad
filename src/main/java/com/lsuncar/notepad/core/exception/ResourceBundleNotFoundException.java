package com.lsuncar.notepad.core.exception;

public class ResourceBundleNotFoundException extends Exception {
    public ResourceBundleNotFoundException() {
        super();
    }

    public ResourceBundleNotFoundException(String message) {
        super(message);
    }

    public ResourceBundleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceBundleNotFoundException(Throwable cause) {
        super(cause);
    }
}
