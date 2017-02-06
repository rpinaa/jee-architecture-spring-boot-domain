package org.example.seed.filter;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ricardo Pina Arellano on 16/01/2017.
 */
@ControllerAdvice
public class ExceptionRESTFilter {

    @ExceptionHandler(RuntimeException.class)
    void serviceException(final HttpServletRequest request, final HttpServletResponse response, final RuntimeException exception) throws IOException {

        request.setAttribute(DefaultErrorAttributes.class.getName(), null);
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }
}
