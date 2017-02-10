package com.zapoos.ilovezapoos.util.scopes;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by adarsh on 2/3/2017.
 */

@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface MainPresenterScope {
}