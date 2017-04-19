package com.ericsson.group.services;

import java.io.PrintStream;

/**
 * Created by mypc1 on 17/04/2017.
 */
public class Greeter {
    public void greet(PrintStream to, String name) {
        to.println(createGreeting(name));
    }

    public String createGreeting(String name) {
        return "Hello, " + name + "!";
    }
}