package com.resolvix.lib.esb.api.event.error;

import com.resolvix.lib.esb.api.event.EsbEventCategory;

public interface EsbErrorHandle {

    /**
     * Returns the {@link EsbEventCategory} associated with the {@link EsbErrorHandle}.
     *
     * @return the category
     */
    EsbEventCategory getCategory();

    /**
     * Returns the code associated with the {@link EsbErrorHandle}.
     *
     * @return the code
     */
    String getCode();

    /**
     * Returns the message based on the pattern associated with the
     * {@link EsbErrorHandle} into which the arguments passed are interpolated.
     *
     * @param arguments the values to interpolate into the pattern
     *  associated with the {@link EsbErrorHandle}
     * @return the message
     */
    String getMessage(Object... arguments);
}
