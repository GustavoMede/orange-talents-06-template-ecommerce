package br.com.zupacademy.gustavo.mercadolivre.enums;

import java.util.Locale;

public enum Gateway {
    PAYPAL,
    PAGSEGURO;

    public static String retornaUrl(Gateway gateway, String produtoId) {
        return gateway.toString().toLowerCase(Locale.ROOT) + ".com?buyerId=" + produtoId + "&redirectUrl=pagamento" + gateway;
    }
}
