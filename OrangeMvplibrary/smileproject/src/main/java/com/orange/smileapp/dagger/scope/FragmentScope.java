package com.orange.smileapp.dagger.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Fragment的作用域
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FragmentScope {
}
