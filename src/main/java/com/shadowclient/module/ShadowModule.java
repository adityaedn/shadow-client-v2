package com.shadowclient.module;

public final class ShadowModule {
    private final String name;
    private final String category;
    private final String description;
    private boolean enabled;

    public ShadowModule(String name, String category, String description, boolean enabled) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.enabled = enabled;
    }

    public String name() {
        return name;
    }

    public String category() {
        return category;
    }

    public String description() {
        return description;
    }

    public boolean enabled() {
        return enabled;
    }

    public void toggle() {
        enabled = !enabled;
    }
}
