package io.java.learning;

import jakarta.servlet.http.HttpServletRequest;
import org.apereo.cas.client.util.AbstractCasFilter;
import org.apereo.cas.client.validation.Assertion;

public class CasUtils {
    public static String getLoginNameFromCas(HttpServletRequest request) {
        Assertion assertion = (Assertion) request.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
        if (assertion != null) {
            return assertion.getPrincipal().getName();
        }
        return null;
    }
}
