package me.kaiyan.missilewarfare.missiles.missiletypes;

import me.kaiyan.missilewarfare.missiles.MissileBuilder;
import me.kaiyan.missilewarfare.missiles.SimpleMissile;
import me.kaiyan.missilewarfare.missiles.target.StationaryTargetObject;
import org.jetbrains.annotations.NotNull;

@Deprecated
//use builder to build
public class CruiseMissile extends SimpleMissile<StationaryTargetObject> {
    public CruiseMissile(@NotNull MissileBuilder<StationaryTargetObject> builder) {
        super(builder);
    }
}
