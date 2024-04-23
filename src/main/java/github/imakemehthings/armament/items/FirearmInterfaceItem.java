package github.imakemehthings.armament.items;

import com.simibubi.create.AllSoundEvents;
import github.imakemehthings.armament.util.FirearmAmmoHandler;
import github.imakemehthings.armament.util.FirearmExternalItemProperties;
import github.imakemehthings.armament.util.FirearmInterface;
import github.imakemehthings.armament.util.ModDamageSource;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;

// This is just to get ALL of the nonsense out of it
public class FirearmInterfaceItem extends Item implements FirearmInterface {

    // properties
    protected float velocityMultiplier = 1f;
    protected float damageModifier = 0f;
    protected int volleyCount = 1;
    protected float xSpread = 0f;
    protected float ySpread = 0f;

    protected int maxCapacity = 1;
    protected int reloadSize = 1;
    protected FirearmAmmoHandler ammoHandler = null;
    protected boolean helldiverReload = false;
    protected boolean reloadCutoff = true;

    protected int reloadTime = 60;
    protected int shotTime = 10;

    protected SimpleParticleType trailParticle = ParticleTypes.CRIT;
    protected SimpleParticleType hitParticle = ParticleTypes.POOF;
    protected SimpleParticleType launchParticle = ParticleTypes.FLAME;

    protected SoundEvent reloadSound = AllSoundEvents.CRANKING.getMainEvent();
    protected SoundEvent shotSound = SoundEvents.GENERIC_EXPLODE;
    protected SoundEvent ooaSound = AllSoundEvents.CONTROLLER_CLICK.getMainEvent();
    protected SoundEvent declineReloadSound = AllSoundEvents.DENY.getMainEvent();

    protected DamageSource damageType = ModDamageSource.GENERIC_GUN_DAMAGE;
    protected boolean shootsStraight = true;
    protected float range = 160;

    protected String notes = "g   u   n";

    // item properties (not in interface)
    protected int durability = 1000;
    protected int maxStack = 1;

    // status
    protected int ammoLoaded;

    public FirearmInterfaceItem(Properties properties) {
        super(properties);
    }

    public float velocityMultiplier() {
        return velocityMultiplier;
    }


    public float damageModifier() {
        return damageModifier;
    }


    public int volleyCount() {
        return volleyCount;
    }


    public float xSpread() {
        return xSpread;
    }


    public float ySpread() {
        return ySpread;
    }


    public int maxCapacity() {
        return maxCapacity;
    }


    public int reloadSize() {
        return reloadSize;
    }

    public FirearmAmmoHandler ammoHandler() {
        return ammoHandler;
    }

    public boolean helldiverReload() {
        return helldiverReload;
    }

    public boolean reloadCutoff() {
        return reloadCutoff;
    }

    public DamageSource damageType() {
        return damageType;
    }

    public boolean shootsStraight() {
        return shootsStraight;
    }

    public float range() {
        return range;
    }

    public int reloadTime() {
        return reloadTime;
    }

    public int shotTime() {
        return shotTime;
    }

    public SimpleParticleType trailParticle() {
        return trailParticle;
    }

    public SimpleParticleType hitParticle() {
        return hitParticle;
    }

    public SimpleParticleType launchParticle() {
        return launchParticle;
    }

    public SoundEvent shotSound() {
        return shotSound;
    }

    public SoundEvent reloadSound() {
        return reloadSound;
    }

    public SoundEvent ooaSound() {
        return ooaSound;
    }

    public SoundEvent declineReloadSound() {
        return declineReloadSound;
    }

    public int firearmDurability() {
        return durability;
    }

    public int firearmMaxStack() {
        return maxStack;
    }

    public String notes() {
        return notes;
    }

    public int ammoLoaded() {
        return ammoLoaded;
    }
}
