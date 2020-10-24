package com.resolvix.lib.esb.api.exception;

import com.resolvix.lib.esb.api.event.Category;
import com.resolvix.lib.esb.api.event.error.Handle;
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
        implements Handle
    {
        ERROR001(Category.ERROR, "ERROR001"),
        WARNING002(Category.WARNING, "WARNING002"),
        WARNING003(Category.WARNING, "WARNING003"),
        WARNING004(Category.WARNING, "WARNING004"),
        INFO005(Category.INFO, "INFO005");

        private Category category;

        private String code;

        private String pattern;

        static {
            ResourceBundle resourceBundle = PropertyResourceBundle.getBundle("messages");
            for (LocalError localError : LocalError.values())
                localError.pattern = resourceBundle.getString(localError.code);
        }

        LocalError(Category category, String code) {
            this.category = category;
            this.code = code;
        }

        @Override
        public Category getCategory() {
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
                hasProperty("category", equalTo(Category.ERROR)),
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
                hasProperty("category", equalTo(Category.INFO)),
                hasProperty("code", equalTo("INFO005")),
                hasProperty("elementPath", nullValue()),
                hasProperty("permittedValues", nullValue()),
                hasProperty("message", equalTo("This is the message for INFO005."))));
        throw exception;
    }
}
