package com.resolvix.lib.esb.api.exception;

import com.resolvix.lib.esb.api.event.Category;
import com.resolvix.lib.esb.api.event.error.Handle;
import com.resolvix.lib.esb.api.event.error.ValidationError;

public class EsbIllegalArgumentException
    extends IllegalArgumentException
    implements ValidationError
{
    private static final String EMPTY_STRING = new String();

    private Handle handle;

    private String elementPath;

    private Object[] permittedValues;

    public EsbIllegalArgumentException() {
        super();
    }

    public EsbIllegalArgumentException(String s) {
        super(s);
    }

    private static String toString(Object[] objects) {
        if (objects == null)
            return EMPTY_STRING;

        String[] strings = new String[objects.length];
        for (int i = 0; i < objects.length; i++)
            if (objects[i] != null)
                strings[i] = objects[i].toString();

        return String.join(", ", strings);
    }

    public EsbIllegalArgumentException(
        Handle handle, String elementPath, Object[] permittedValues) {
        super(handle.getMessage(elementPath, toString(permittedValues)));
        this.handle = handle;
        this.elementPath = elementPath;
        this.permittedValues = permittedValues;
    }

    public EsbIllegalArgumentException(Handle handle) {
        this(handle, null, null);
    }

    @Override
    public Category getCategory() {
        return handle.getCategory();
    }

    @Override
    public String getCode() {
        return handle.getCode();
    }

    public String getElementPath() {
        return elementPath;
    }

    public Object[] getPermittedValues() {
        return permittedValues;
    }
}
