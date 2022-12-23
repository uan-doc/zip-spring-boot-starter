package br.com.uan.enums;

/**
 * Enum das mensagens de erro conhecidas.
 * 
 * Essa classe deve ir para uma biblioteca comum ao GedocFlex, iGED e
 * microsserviços.
 *
 * @author jesussaad
 */
public enum KnowErrorMessagesEnum {

    uan_001("uan-001 - O arquivo %s não é um ZIP."),

    ;

    private final String message;

    private KnowErrorMessagesEnum(String message) {
        this.message = message;
    }

    public String getMessage(Object... params) {
        return String.format(message, params);
    }

    public static boolean isAboutThis(String message, KnowErrorMessagesEnum error) {
        if (message == null) {
            return false;
        }

        return message.startsWith(error.message.substring(0, 7));
    }
}
