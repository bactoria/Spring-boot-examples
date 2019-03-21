package com.example.RabbitMQ.utils.exception;

@FunctionalInterface
public interface FunctionWithException<T, R, E extends Exception> {
    R apply(T t) throws E;
}