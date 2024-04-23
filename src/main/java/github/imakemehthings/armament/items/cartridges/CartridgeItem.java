package github.imakemehthings.armament.items.cartridges;

import github.imakemehthings.armament.util.CartridgeProperties;
import github.imakemehthings.armament.util.ModDamageSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class CartridgeItem extends Item implements CartridgeProperties {
    protected float velocity;
    protected float baseDamage;
    protected int projectileCount;
    protected DamageSource damageSource;

    public CartridgeItem(Properties properties) {
        super(properties);
        this.velocity = 3f;
        this.baseDamage = 8f;
        this.projectileCount = 1;
        this.damageSource = ModDamageSource.GENERIC_GUN_DAMAGE;
    }

    public float getVelocity() {return velocity; }
    public float getBaseDamage() {return baseDamage; }
    public int getProjectileCount() {return projectileCount; }
    public void customEffects(Level level, Player player, Entity target) {} // override this to add custom effects like burning or teleportation etc...
    public DamageSource getDamageType() { return damageSource; }
}
