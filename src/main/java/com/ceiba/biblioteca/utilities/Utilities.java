package com.ceiba.biblioteca.utilities;

import com.ceiba.biblioteca.infrastructure.error.ControllerMessages;

public class Utilities {

    public static String getValueMessage(String key) {
        return ControllerMessages.messages.getString(key);
    }

}
