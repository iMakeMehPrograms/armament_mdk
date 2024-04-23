package github.imakemehthings.armament.util;

import net.minecraft.world.damagesource.DamageSource;

public class ModDamageSource {
    public static final DamageSource GENERIC_GUN_DAMAGE = (new DamageSource("gun")).setProjectile();
    public static final DamageSource SMALL_ARMS_DAMAGE = (new DamageSource("small_arms")).setProjectile();
    public static final DamageSource SHOTGUN_DAMAGE = (new DamageSource("shotgun")).setProjectile();
    public static final DamageSource PRECISION_DAMAGE = (new DamageSource("precision")).setProjectile().bypassArmor();
    public static final DamageSource ANTIMATERIEL_DAMAGE = (new DamageSource("antimateriel")).setProjectile().setExplosion();
}
