package com.resolvix.lib.esb.api.event;

/**
 * Defines the interface of a generic ESB event.
 *
 */
public interface EsbEvent {

    /**
     * Returns the {@link EsbEventCategory} of the {@link EsbEvent}.
     *
     * @return the category
     */
    EsbEventCategory getCategory();
}
