package com.resolvix.lib.esb.api.exception;

import com.resolvix.lib.esb.api.event.EsbEventCategory;
import com.resolvix.lib.esb.api.event.error.EsbErrorHandle;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.text.MessageFormat;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class EsbIllegalArgumentExceptionUT {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private enum LocalError
        implements EsbErrorHandle
    {
        ERROR001(EsbEventCategory.ERROR, "ERROR001"),
        WARNING002(EsbEventCategory.WARNING, "WARNING002"),
        WARNING003(EsbEventCategory.WARNING, "WARNING003"),
        WARNING004(EsbEventCategory.WARNING, "WARNING004"),
        INFO005(EsbEventCategory.INFO, "INFO005");

        private EsbEventCategory category;

        private String code;

        private String pattern;

        static {
            ResourceBundle resourceBundle = PropertyResourceBundle.getBundle("messages");
            for (LocalError localError : LocalError.values())
                localError.pattern = resourceBundle.getString(localError.code);
        }

        LocalError(EsbEventCategory category, String code) {
            this.category = category;
            this.code = code;
        }

        @Override
        public EsbEventCategory getCategory() {
            return category;
        }

        @Override
        public String getCode() {
            return code;
        }

        @Override
        public String getMessage(Object... arguments) {
            return MessageFormat.format(pattern, arguments);
        }
    }

    @Before
    public void before() {

    }

    @Test
    public void esbIllegalArgumentExceptionGivenHandleElementPathAndArguments() {
        expectedException.expect(EsbIllegalArgumentException.class);
        EsbIllegalArgumentException exception
            = new EsbIllegalArgumentException(LocalError.ERROR001, "dataElement", new String[] { "permittedValue1", "permittedValue2" });
        assertThat(
            exception, allOf(
                hasProperty("category", equalTo(EsbEventCategory.ERROR)),
                hasProperty("code", equalTo("ERROR001")),
                hasProperty("elementPath", equalTo("dataElement")),
                hasProperty("permittedValues", arrayContaining("permittedValue1", "permittedValue2")),
                hasProperty("message", equalTo("This is the message for ERR001, this is the first parameter dataElement and this is the second permittedValue1, permittedValue2."))));
        throw exception;
    }

    @Test
    public void esbIllegalArgumentExceptionGivenHandle() {
        expectedException.expect(EsbIllegalArgumentException.class);
        EsbIllegalArgumentException exception
            = new EsbIllegalArgumentException(LocalError.INFO005);
        assertThat(
            exception, allOf(
                hasProperty("category", equalTo(EsbEventCategory.INFO)),
                hasProperty("code", equalTo("INFO005")),
                hasProperty("elementPath", nullValue()),
                hasProperty("permittedValues", nullValue()),
                hasProperty("message", equalTo("This is the message for INFO005."))));
        throw exception;
    }
}
