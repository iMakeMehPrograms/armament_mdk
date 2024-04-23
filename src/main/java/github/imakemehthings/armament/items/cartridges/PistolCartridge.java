package github.imakemehthings.armament.items.cartridges;

import github.imakemehthings.armament.util.ModDamageSource;

public class PistolCartridge extends CartridgeItem {

    public PistolCartridge(Properties properties) {
        super(properties);
        this.velocity = 365f;
        this.baseDamage = 2f;
        this.projectileCount = 1;
        this.damageSource = ModDamageSource.SMALL_ARMS_DAMAGE;
    }
}
