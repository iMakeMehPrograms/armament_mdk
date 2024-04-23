package github.imakemehthings.armament.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static final TagKey<Item> ammuntion = ItemTags.create(new ResourceLocation("armament", "ammunition"));
    public static final TagKey<Item> pistolAmmo = ItemTags.create(new ResourceLocation("armament", "pistol_ammo"));
    public static final TagKey<Item> intermediateAmmo = ItemTags.create(new ResourceLocation("armament", "intermediate_ammo"));
    public static final TagKey<Item> precisionAmmo = ItemTags.create(new ResourceLocation("armament", "precision_ammo"));
    public static final TagKey<Item> antimaterielAmmo = ItemTags.create(new ResourceLocation("armament", "antimateriel_ammo"));
    public static final TagKey<Item> shotgunAmmo = ItemTags.create(new ResourceLocation("armament", "shotgun_ammo"));
}
