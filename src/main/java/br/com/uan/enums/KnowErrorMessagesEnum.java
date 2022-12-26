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

    /**
     * Retorna a mensagem formatada para ser lançada nas exceptions, ex.: throw
     * new
     * IllegalArgumentException(KnowErrorMessagesEnum.uan_001.getMessage(zipFile.getPath()));
     */
    public String getMessage(Object... params) {
        return String.format(message, params);
    }

    /**
     * Verifica se a mensagem informada é sobre o enum informado, ex.: <br/>
     * <br/>
     * {@code if(KnowErrorMessagesEnum.isAboutThis(e.getMessage(),
     * KnowErrorMessagesEnum.uan_001))}
     */
    public static boolean isAboutThis(String message, KnowErrorMessagesEnum error) {
        if (message == null) {
            return false;
        }

        return message.startsWith(error.message.substring(0, 7));
    }
}
