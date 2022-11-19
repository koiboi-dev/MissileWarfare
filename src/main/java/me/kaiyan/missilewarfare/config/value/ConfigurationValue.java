package me.kaiyan.missilewarfare.config.value;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface ConfigurationValue<T> {
    @NotNull Optional<T> getValue();
}
