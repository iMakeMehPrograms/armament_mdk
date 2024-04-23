package github.imakemehthings.armament.util;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class FirearmAmmoHandler {

    protected TagKey<Item> acceptedAmmo;

    public TagKey<Item> getAmmoTag() { return acceptedAmmo; }

}
