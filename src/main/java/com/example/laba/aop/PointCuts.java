package com.example.laba.aop;

import org.aspectj.lang.annotation.Pointcut;

/** The type Point cuts. */
public class PointCuts {
  /** All methods. */
  @Pointcut(value = "execution(* com.example.laba.service.*.*(..)) ")
  public void allMethods() {}
}
