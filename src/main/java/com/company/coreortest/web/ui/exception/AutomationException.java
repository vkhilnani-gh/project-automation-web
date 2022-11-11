package com.company.coreortest.web.ui.exception;

public class AutomationException
        extends RuntimeException {
    /**
     * Cosntructor
     * @param failureMessage Textual description of the problem.
      */

    public AutomationException(String failureMessage) {
        super(failureMessage);
    }

    /**
     * Cosntructor
     * @param originalException The original Exception.
     */

    public AutomationException(Exception originalException) {
        super(originalException);
    }
}
