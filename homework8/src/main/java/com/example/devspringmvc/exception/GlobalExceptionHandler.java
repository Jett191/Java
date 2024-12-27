package com.example.devspringmvc.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ServiceException.class)
    public ModelAndView handleServiceException(HttpServletRequest request, ServiceException e) {
        logger.error("Service Exception at {} : {}", request.getRequestURL(), e.getMessage());
        
        ModelAndView mav = new ModelAndView();
        mav.addObject("url", request.getRequestURL());
        mav.addObject("exception", e);
        mav.addObject("message", e.getMessage());
        mav.setViewName("error/service_error");
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest request, Exception e) {
        logger.error("Request URL: {}, Exception: {}", request.getRequestURL(), e.getMessage());
        
        ModelAndView mav = new ModelAndView();
        mav.addObject("url", request.getRequestURL());
        mav.addObject("exception", e);
        mav.setViewName("error/error");
        return mav;
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView handleMaxUploadSizeExceededException(HttpServletRequest request, MaxUploadSizeExceededException e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "文件大小超过限制(最大5MB)");
        mav.setViewName("error/file_upload_error");
        return mav;
    }
} 