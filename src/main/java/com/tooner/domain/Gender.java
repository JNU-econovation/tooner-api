package com.tooner.domain;

public enum Gender {
    M("male"),
    F("female"),
    ETC("etc");

    public final String label;

    private Gender(String label) {
        this.label = label;
    }
}
