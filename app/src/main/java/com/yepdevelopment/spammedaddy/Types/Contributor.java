package com.yepdevelopment.spammedaddy.Types;

import lombok.Getter;

/**
 * This is not a database entity.
 */
@Getter
public class Contributor {
    private final String name;
    private final String role;
    private final String imageUri;

    public Contributor(String name) {
        if (name == null) {
            this.name = "";
        } else {
            this.name = name;
        }

        this.role = "";
        this.imageUri = "";
    }

    public Contributor(String name, String role) {
        if (name == null) {
            this.name = "";
        } else {
            this.name = name;
        }

        if (role == null) {
            this.role = "";
        } else {
            this.role = role;
        }

        this.imageUri = "";
    }

    public Contributor(String name, String role, String imageUri) {
        if (name == null) {
            this.name = "";
        } else {
            this.name = name;
        }

        if (role == null) {
            this.role = "";
        } else {
            this.role = role;
        }

        if (imageUri == null) {
            this.imageUri = "";
        } else {
            this.imageUri = imageUri;
        }
    }

}
