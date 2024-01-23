package ru.kotb.accountingsystem.dto;


/**
 * The contract types for {@code ContractDTO}.
 */
public enum ContractType {
    MAIN_CONTRACT("Main"),
    COUNTERPARTY_CONTRACT("Counterparty");

    private final String text;

    ContractType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
