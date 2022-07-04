package com.example.loom;

import jdk.incubator.concurrent.ExtentLocal;
import jdk.incubator.concurrent.StructuredTaskScope;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class StructuredConcurrencyVT {


    public void structured() {
        try(var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            scope.fork(() -> {
                        System.out.println("A: First");
                        return null;
                    }
            );
            scope.fork (() -> {
                System.out.println("A: Second");
                return null;

            } );
            scope.join();
            scope.throwIfFailed();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        try(var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            scope.fork(() -> {
                System.out.println("B: First");
                return null;
            } );
            scope.fork(() -> {
                System.out.println("B: Second");
                return null;
            } );
            scope.join();
            scope.throwIfFailed();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("he");
    }
}
