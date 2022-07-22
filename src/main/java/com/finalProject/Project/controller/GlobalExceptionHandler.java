package com.finalProject.Project.controller;

import com.finalProject.Project.exception.InvalinInput;
import com.finalProject.Project.exception.UnActiveToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(UnActiveToken.class)
    public String expiredToken(Exception ex, WebRequest webRequest){
        return makeErrorPage(ex.getMessage());
    }

    @ExceptionHandler(InvalinInput.class)
    public ResponseEntity<Object> inputError(Exception ex, WebRequest webRequest){
        logger.error(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Some Thing Wrong " + ex.getMessage());

    }
    @ExceptionHandler(RuntimeException.class)
    public String exception(RuntimeException runtimeException, WebRequest webRequest){
        logger.error(runtimeException.getMessage());
        return makeErrorPage(runtimeException.getMessage());
    }
    public String makeErrorPage(String error){

        return "<html>\n" +
                "<head>\n" +
                "\n" +
                "<style type=text/css>\n" +
                "p {\n" +
                "    color: #0ecc8a;\n" +
                "    font-weight: 900;\n" +
                "    font-size: 20px;\n" +
                "    font-family: Helvetica, Arial, sans-serif;    \n" +
                "}\n" +
                "\n" +
                "div {\n" +
                "  background-color: gray;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<div>\n" +
                "<h1>"+error+"</h>\n" +
                "<br><br> \n" +
                "<h2>If you came upon this page by mistake, try checking the URL in your web browser.</h2>\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }

}
