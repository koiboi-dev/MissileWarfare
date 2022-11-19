package me.kaiyan.missilewarfare.config.value;

import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class AbstractDefaultConfigurationValue<T> extends AbstractConfigurationValue<T>
        implements DefaultedConfigurationValue<T> {

    private final @NotNull T defaultValue;

    public AbstractDefaultConfigurationValue(@NotNull Supplier<T> supplier, @NotNull T defaultValue) {
        super(supplier);
        this.defaultValue = defaultValue;
    }


    @Override
    public @NotNull T getDefault() {
        return this.defaultValue;
    }

    public static @NotNull AbstractDefaultConfigurationValue<Integer> createInt(@NotNull Supplier<ConfigurationSection> config,
                                                                                @NotNull String node,
                                                                                int defaultValue) {
        return new AbstractDefaultConfigurationValue<>(() -> {
            ConfigurationSection configValue = config.get();
            if (configValue.getString(node) == null) {
                return null;
            }
            return configValue.getInt(node);
        }, defaultValue);
    }

    public static @NotNull AbstractDefaultConfigurationValue<Double> createDouble(@NotNull Supplier<ConfigurationSection> config,
                                                                                  @NotNull String node,
                                                                                  double defaultValue) {
        return new AbstractDefaultConfigurationValue<>(() -> {
            ConfigurationSection configValue = config.get();
            if (configValue.getString(node) == null) {
                return null;
            }
            return configValue.getDouble(node);
        }, defaultValue);
    }
}
