package com.tenpay.wxwork.salary.common;

import java.util.List;

import javax.validation.UnexpectedTypeException;

import org.apache.ibatis.exceptions.PersistenceException;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tenpay.bap.common.exception.BAPException;
import com.tenpay.wxwork.salary.dto.FrontEndResponse;
import com.tenpay.wxwork.salary.provider.CorpBindAuthenController;
import com.tenpay.wxwork.salary.provider.CorpBindConfirmController;

@RestControllerAdvice(assignableTypes = {CorpBindAuthenController.class,CorpBindConfirmController.class})
public class RestExceptionHandler{
	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(UnexpectedTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public FrontEndResponse unexpectedTypeException(UnexpectedTypeException e) {
        String errorMesssage = "validation error."+e.getMessage();
    	logger.error(errorMesssage);
        return new FrontEndResponse(Integer.toString(ErrorModuleConvert.toModuleError(BizError.BAD_REQUEST.errorCode())),errorMesssage);
    }
	
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public FrontEndResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    private FrontEndResponse processFieldErrors(List<FieldError> fieldErrors) {
    	StringBuilder  stringBuilder = new StringBuilder();
    	stringBuilder.append("validation error.");
        for (FieldError fieldError: fieldErrors) {
        	stringBuilder.append(fieldError.getField());
        	stringBuilder.append(":");
        	stringBuilder.append(fieldError.getDefaultMessage());
        	stringBuilder.append(".");
        }
    	logger.error(stringBuilder.toString());
    	FrontEndResponse frontEndResponse = new FrontEndResponse(Integer.toString(ErrorModuleConvert.toModuleError(BizError.BAD_REQUEST.errorCode())),stringBuilder.toString());
        return frontEndResponse;
    }
    
    @ExceptionHandler(BizException.class)
    public FrontEndResponse bizException(BizException e) {
    	logger.error(e.getErrMsg());
        return new FrontEndResponse(Integer.toString(ErrorModuleConvert.toModuleError(e.getRetCode())), e.getErrMsg());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public FrontEndResponse methodIllegalArgumentException(IllegalArgumentException e) {
        logger.error(e.getMessage());
        FrontEndResponse frontEndResponse = new FrontEndResponse(Integer.toString(ErrorModuleConvert.toModuleError(BizError.ILLEGAL_ARGUMENT.errorCode())),
                                                                 BizError.ILLEGAL_ARGUMENT.errorMsg() + e.getMessage());
        return frontEndResponse;
    }

    
    @ExceptionHandler(MyBatisSystemException.class)
    public FrontEndResponse methodmyBatisSystemException(MyBatisSystemException e) {
    	logger.error(e.getMessage());
    	FrontEndResponse frontEndResponse = new FrontEndResponse(Integer.toString(ErrorModuleConvert.toModuleError(BizError.MYBATIS_CONFIG_ERROR.errorCode())),e.getMessage());
    	return frontEndResponse;
    }
    
    @ExceptionHandler(PersistenceException.class)
    public FrontEndResponse methodpersistenceException(PersistenceException e) {
    	logger.error(e.getMessage());
    	FrontEndResponse frontEndResponse = new FrontEndResponse(Integer.toString(ErrorModuleConvert.toModuleError(BizError.MYBATIS_CONFIG_ERROR.errorCode())),e.getMessage());
    	return frontEndResponse;
    }
    
    @ExceptionHandler(BadSqlGrammarException.class)
    public FrontEndResponse methodBadSqlGrammarException(BadSqlGrammarException e) {
    	logger.error(e.getMessage());
    	FrontEndResponse frontEndResponse = new FrontEndResponse(Integer.toString(ErrorModuleConvert.toModuleError(BizError.SQL_GRAMMAR_ERROR.errorCode())),BizError.SQL_GRAMMAR_ERROR.errorMsg());
    	return frontEndResponse;
    }
    @ExceptionHandler(UncategorizedSQLException.class)
    public FrontEndResponse methodUncategorizedSQLException(UncategorizedSQLException e) {
    	logger.error(e.getMessage());
    	FrontEndResponse frontEndResponse = new FrontEndResponse(Integer.toString(ErrorModuleConvert.toModuleError(BizError.UNCATEGORIZED_SQL_ERROR.errorCode())),BizError.UNCATEGORIZED_SQL_ERROR.errorMsg());
    	return frontEndResponse;
    }
    
    
    
    
    @ExceptionHandler(BAPException.class)
    public FrontEndResponse methodBAPException(BAPException e) {
    	logger.error(e.getMessage());
    	FrontEndResponse frontEndResponse = new FrontEndResponse(Integer.toString(e.getErrorCode()),e.getMsg());
    	return frontEndResponse;
    }
}