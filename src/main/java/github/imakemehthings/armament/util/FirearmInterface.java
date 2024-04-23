package github.imakemehthings.armament.util;


import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;

public interface FirearmInterface {
    /*
    *
    * PROPERTIES (constant descriptors)
    * examples: mag size, spread, etc...
    * */

    // Launch Properties
    float velocityMultiplier();
    float damageModifier();
    int volleyCount(); // clarification: how many bullets to shoot with one click
    float xSpread();
    float ySpread();

    // Mag/Ammo Properties
    int maxCapacity();
    int reloadSize();
    FirearmAmmoHandler ammoHandler();
    boolean helldiverReload(); // clarify: should reloading throw away all of the currently stored bullets (like in Helldivers II)
    boolean reloadCutoff(); // clar: if(reloadSize + currentCapacity > maxCapacity), should you decline the reload or reload and put the remaining bullets back? if true then do latter

    // Misc
    DamageSource damageType();
    boolean shootsStraight(); // cla: if true then don't simulate bullet else do
    float range();

    // Delays
    int reloadTime();
    int shotTime(); // cl: cooldown for shots

    // Aesthetics
    SimpleParticleType trailParticle();
    SimpleParticleType hitParticle();
    SimpleParticleType launchParticle();

    SoundEvent shotSound();
    SoundEvent reloadSound();
    SoundEvent ooaSound(); // c: out of (chambered) ammo sound
    SoundEvent declineReloadSound(); // only used if reloadCutoff = false

    String notes();

    // item properties
    int firearmDurability();
    int firearmMaxStack();

    /*
     *
     * ACCESSORS (volatile descriptors)
     * examples: bullets currently loaded, etc...
     * */

    int ammoLoaded();

}
