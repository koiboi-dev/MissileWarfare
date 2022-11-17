package me.kaiyan.missilewarfare.missiles.type;

import me.kaiyan.missilewarfare.missiles.MissileBuilder;
import org.jetbrains.annotations.NotNull;

public interface MissileType {

    void applyBuilder(@NotNull MissileBuilder<?> builder);

    int getMaxRange();

    int getPower();

    int getMaxSpeed();

    int getManeuverability();


}
