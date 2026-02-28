package com.seveneleven.mycontact.contact.model;

import java.util.Objects;

public class Tag {

    private String name;

    public Tag(String name) {
        this.name = name.toLowerCase(); // normalize
    }

    public String getName() {
        return name;
    }

    // IMP for Set uniqueness
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        Tag tag = (Tag) o;
        return name.equals(tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}