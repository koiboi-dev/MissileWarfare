package me.kaiyan.missilewarfare.missiles.type;

import me.kaiyan.missilewarfare.missiles.MissileBuilder;
import me.kaiyan.missilewarfare.missiles.target.TargetObject;
import org.jetbrains.annotations.NotNull;

public interface MissileType {

    default <T extends TargetObject<?>> MissileBuilder<T> applyBuilder(@NotNull MissileBuilder<T> builder) {
        return builder
                .setManeuverability(this.getManeuverability())
                .setPower(this.getPower())
                .setMaxRange(this.getMaxRange())
                .setMaxSpeed(this.getMaxSpeed());
    }

    int getMaxRange();

    double getPower();

    int getMaxSpeed();

    int getManeuverability();

    @NotNull String getDisplayName();

    @NotNull String getId();


}
