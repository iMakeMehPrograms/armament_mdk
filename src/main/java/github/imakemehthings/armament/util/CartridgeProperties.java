package github.imakemehthings.armament.util;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public interface CartridgeProperties {
    float getVelocity();
    float getBaseDamage();
    int getProjectileCount();
    void customEffects(Level level, Player player, Entity target);
    DamageSource getDamageType();
}
