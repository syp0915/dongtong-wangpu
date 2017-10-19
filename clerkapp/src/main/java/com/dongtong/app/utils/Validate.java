package com.dongtong.app.utils;


import com.dongtong.app.exception.AppWebException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * 类注释
 *
 * @author zhangjh
 * @version 1.0.0 createTime: 2017/5/3 17:44
 * @since 1.0
 */
public class Validate<T> {
    private final static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();


    /**
     * 请求参数非空、格式验证，请求对象
     *
     * @param t 请求校验参数
     */
    public static <T> void validateObject(T t) throws AppWebException {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(t);
        if (violations.isEmpty()) return;
        for (ConstraintViolation<T> violation : violations)
            throw new AppWebException(20001, violation.getMessage());
    }

}
