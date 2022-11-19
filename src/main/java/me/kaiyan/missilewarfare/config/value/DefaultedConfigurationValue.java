package me.kaiyan.missilewarfare.config.value;

import org.jetbrains.annotations.NotNull;

public interface DefaultedConfigurationValue<T> extends ConfigurationValue<T> {

    @NotNull T getDefault();

    default @NotNull T getValueOrDefault() {
        return this.getValue().orElseGet(this::getDefault);
    }
}
