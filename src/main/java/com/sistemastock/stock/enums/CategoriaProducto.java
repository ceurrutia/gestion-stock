package com.sistemastock.stock.enums;

public enum CategoriaProducto {
    ALIMENTO("Alimento"),
    BEBIDA("Bebida"),
    LIMPIEZA("Limpieza"),
    ELECTRONICA("Electrónica"),
    INDUMENTARIA("Indumentaria"),
    HERRAMIENTAS("Herramientas"),
    PAPELERIA("Papelería"),
    JUGUETERIA("Juguetería"),
    FARMACIA("Farmacia");

    private final String label;

    CategoriaProducto(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
