package com.anas.booky.api.booky.admin;

public enum Role {
    /**
     * The highest role in the system.
     * Can do anything.
     */
    SuperAdmin,
    /**
     * Can do anything except managing admins.
     */
    Admin,
    /**
     * Can list and see the books details
     */
    User,
}
