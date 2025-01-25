package com.br.maisAcademiaPrati.enums;

public enum Plano {
    mensal,
    semestral,
    anual;

    public static Plano fromString(String text) {
        for (Plano b : Plano.values()) {
            if (b.name().equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Tipo de plano inválido");
    }
}
