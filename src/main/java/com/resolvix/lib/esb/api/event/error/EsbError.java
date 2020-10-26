package com.resolvix.lib.esb.api.event.error;

import com.resolvix.lib.esb.api.event.EsbEvent;

public interface EsbError
    extends EsbEvent
{

    /**
     * Returns the code associated with the {@link EsbError}.
     *
     * @return the code
     */
    String getCode();

    /**
     * Returns the handle associated with the {@link EsbError}.
     *
     * @return the handle
     */
    EsbErrorHandle getHandle();

    /**
     * Returns the message associated with the {@link EsbError}.
     *
     * @return the message
     */
    String getMessage();
}
