package me.kaiyan.missilewarfare;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class VariantsAPI {
    public static String getStrVariantFromInt(int type){
        switch (type) {
            case 1:
                return "SMR";
            case 2:
                return "SMHE";
            case 3:
                return "SMLR";
        }
        return "NONE";
    }

    public static int getIntTypeFromSlimefunitem(SlimefunItem item){
        switch (item.getId()) {
            case "SMALLMISSILE":
                return 1;
            case "SMALLMISSILEHE":
                return 2;
            case "SMALLMISSILELR":
                return 3;
        }
        return 0;
    }

    public static SlimefunItem getFirstMissile(Inventory inv){
        for (ItemStack item : inv){
            if (item != null){
                SlimefunItem item1 = SlimefunItem.getByItem(item);
                ItemUtils.consumeItem(item, false);
                return item1;
            }
        }
        return null;
    }
}
