package com.sistemastock.stock.enums;

public enum RolUsuario {
    ADMIN("Admin"),
    GESTOR("Gestor"),
    USUARIO("Usuario");

    private final String label;

    RolUsuario(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
