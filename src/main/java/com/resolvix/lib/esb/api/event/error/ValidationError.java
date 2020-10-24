package com.resolvix.lib.esb.api.event.error;

import com.resolvix.lib.esb.api.event.Event;

public interface ValidationError
    extends Event
{

    /**
     * Returns the code associated with the {@link ValidationError}.
     *
     * @return the code
     */
    String getCode();

    /**
     * Returns the message associated with the {@link ValidationError}.
     *
     * @return the message
     */
    String getMessage();
}
