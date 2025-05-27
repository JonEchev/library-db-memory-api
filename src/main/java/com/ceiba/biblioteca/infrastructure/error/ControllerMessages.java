package com.ceiba.biblioteca.infrastructure.error;

import lombok.NoArgsConstructor;

import java.util.ResourceBundle;

@NoArgsConstructor
public class ControllerMessages {

    public static final ResourceBundle messages;

    static {
        messages = ResourceBundle.getBundle("messages");
    }

}