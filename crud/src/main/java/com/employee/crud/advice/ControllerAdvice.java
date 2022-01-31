package com.employee.crud.advice;



import com.employee.crud.exception.ResourceCreationException;
import com.employee.crud.exception.ResourceDeleteException;
import com.employee.crud.exception.ResourceNotFoundException;
import com.employee.crud.exception.ResourceUpdateException;
import com.employee.crud.model.response.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerAdvice {

    private final MessageSource messageSource;

    @Autowired
    public ControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseWrapper processValidationError(MethodArgumentNotValidException ex, WebRequest request,
                                                  HttpServletRequest req) {

        BindingResult result = ex.getBindingResult();
        List<ObjectError> allErrors = result.getAllErrors();
        List<String> details = processAllErrors(allErrors);
        return new ResponseWrapper(false, "Bad Request", details, "Invalid data", resolvePathFromWebRequest(request), HttpStatus.BAD_REQUEST.value());
    }

    /**
     * Utility Method to generate localized message for a list of field errors
     *
     * @param allErrors the field errors
     * @return the list
     */
    private List<String> processAllErrors(List<ObjectError> allErrors) {
        return allErrors.stream().map(this::resolveLocalizedErrorMessage).collect(Collectors.toList());
    }

    /**
     * Resolve localized error message. Utility method to generate a localized error
     * message
     *
     * @param objectError the field error
     * @return the string
     */
    private String resolveLocalizedErrorMessage(ObjectError objectError) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(objectError, currentLocale);
        return localizedErrorMessage;
    }

    private String resolvePathFromWebRequest(WebRequest request) {
        try {
            return ((ServletWebRequest) request).getRequest().getAttribute("javax.servlet.forward.request_uri").toString();
        } catch (Exception ex) {
            return null;
        }
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseWrapper handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request,
                                                           HttpServletRequest req) {
        return new ResponseWrapper(false, ex.getMessage(), ex.getClass().getName(), resolvePathFromWebRequest(request), HttpStatus.NOT_FOUND.value());
    }


    @ExceptionHandler(value = ResourceCreationException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseWrapper handleResourceCreationException(ResourceCreationException ex, WebRequest request,
                                                           HttpServletRequest req) {
        return new ResponseWrapper(false, ex.getMessage(), ex.getClass().getName(), resolvePathFromWebRequest(request), HttpStatus.EXPECTATION_FAILED.value());
    }

    @ExceptionHandler(value = ResourceDeleteException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseWrapper handleResourceDeleteException(ResourceDeleteException ex, WebRequest request,
                                                         HttpServletRequest req) {
        return new ResponseWrapper(false, ex.getMessage(), ex.getClass().getName(), resolvePathFromWebRequest(request), HttpStatus.EXPECTATION_FAILED.value());
    }

    @ExceptionHandler(value = ResourceUpdateException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseWrapper handleResourceUpdateException(ResourceUpdateException ex, WebRequest request,
                                                         HttpServletRequest req) {
       return new ResponseWrapper(false, ex.getMessage(), ex.getClass().getName(), resolvePathFromWebRequest(request), HttpStatus.EXPECTATION_FAILED.value());
    }
}
