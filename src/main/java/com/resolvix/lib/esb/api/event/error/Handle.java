package com.resolvix.lib.esb.api.event.error;

import com.resolvix.lib.esb.api.event.Category;

public interface Handle {

    /**
     * Returns the {@link Category} associated with the {@link Handle}.
     *
     * @return the category
     */
    Category getCategory();

    /**
     * Returns the code associated with the {@link Handle}.
     *
     * @return the code
     */
    String getCode();

    /**
     * Returns the message based on the pattern associated with the
     * {@link Handle} into which the arguments passed are interpolated.
     *
     * @param arguments the values to interpolate into the pattern
     *  associated with the {@link Handle}
     * @return the message
     */
    String getMessage(Object... arguments);
}
