package me.kaiyan.missilewarfare.missiles.type;

import me.kaiyan.missilewarfare.config.value.AbstractConfigurationValue;
import me.kaiyan.missilewarfare.config.value.AbstractDefaultConfigurationValue;
import me.kaiyan.missilewarfare.config.value.ConfigurationValue;
import me.kaiyan.missilewarfare.config.value.DefaultedConfigurationValue;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;
import java.util.regex.Pattern;

public class ConfigurationMissileType implements MissileType {

    private final DefaultedConfigurationValue<Integer> speed;
    private final DefaultedConfigurationValue<Integer> range;
    private final DefaultedConfigurationValue<Double> power;
    private final DefaultedConfigurationValue<Integer> maneuverability;

    private final ConfigurationValue<String> id;
    private final ConfigurationValue<String> displayName;

    private ConfigurationMissileType(@NotNull DefaultedConfigurationValue<Integer> speed,
                                     @NotNull DefaultedConfigurationValue<Integer> range,
                                     @NotNull DefaultedConfigurationValue<Double> power,
                                     @NotNull DefaultedConfigurationValue<Integer> maneuverability,
                                     @NotNull ConfigurationValue<String> displayName,
                                     @NotNull ConfigurationValue<String> id) {
        this.id = id;
        this.displayName = displayName;
        this.range = range;
        this.power = power;
        this.maneuverability = maneuverability;
        this.speed = speed;
    }

    public @NotNull DefaultedConfigurationValue<Integer> getConfigSpeed() {
        return this.speed;
    }

    public @NotNull DefaultedConfigurationValue<Integer> getConfigRange() {
        return this.range;
    }

    public @NotNull DefaultedConfigurationValue<Double> getConfigPower() {
        return this.power;
    }

    public @NotNull DefaultedConfigurationValue<Integer> getConfigManeuverability() {
        return this.maneuverability;
    }

    public @NotNull ConfigurationValue<String> getConfigId() {
        return this.id;
    }

    public @NotNull ConfigurationValue<String> getConfigDisplayName() {
        return this.displayName;
    }


    @Override
    public int getMaxRange() {
        return this.getConfigRange().getValueOrDefault();
    }

    @Override
    public double getPower() {
        return this.getConfigPower().getValueOrDefault();
    }

    @Override
    public int getMaxSpeed() {
        return this.getConfigSpeed().getValueOrDefault();
    }

    @Override
    public int getManeuverability() {
        return this.getConfigManeuverability().getValueOrDefault();
    }

    @Override
    public @NotNull String getDisplayName() {
        return this
                .getConfigDisplayName()
                .getValue()
                .orElseThrow(() -> new RuntimeException("Cannot read display name"));
    }

    @Override
    public @NotNull String getId() {
        return this.getConfigId().getValue().orElseThrow(() -> new RuntimeException("Cannot find id"));
    }

    public static @NotNull ConfigurationMissileType from(@NotNull Supplier<ConfigurationSection> config) {
        return new ConfigurationMissileType(AbstractDefaultConfigurationValue.createInt(config, "speed", 2),
                                            AbstractDefaultConfigurationValue.createInt(config, "range", 600),
                                            AbstractDefaultConfigurationValue.createDouble(config, "power", 3.5),
                                            AbstractDefaultConfigurationValue.createInt(config, "accuracy", 0),
                                            new AbstractConfigurationValue<>(() -> config.get().getString("name")),
                                            new AbstractConfigurationValue<>(() -> {
                                                String keys = config.get().getCurrentPath();
                                                if (null == keys) {
                                                    return null;
                                                }
                                                String[] split = keys.split(Pattern.quote("."));
                                                if ((1 == split.length) || (0 == split.length)) {
                                                    return keys;
                                                }
                                                return split[split.length - 1];
                                            }));


    }
}
