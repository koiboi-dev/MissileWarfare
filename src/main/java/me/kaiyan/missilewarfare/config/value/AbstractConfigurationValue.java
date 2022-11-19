package me.kaiyan.missilewarfare.config.value;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Supplier;

public class AbstractConfigurationValue<T> implements ConfigurationValue<T> {

    private final @NotNull Supplier<T> supplier;
    private @Nullable T cached;

    public AbstractConfigurationValue(@NotNull Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public @NotNull Optional<T> getValue() {
        if (null == this.cached) {
            this.cached = supplier.get();
        }
        return Optional.ofNullable(this.cached);
    }
}
