package github.imakemehthings.armament.items;

import github.imakemehthings.armament.init.ItemInit;
import github.imakemehthings.armament.util.CombinedRaycast;
import github.imakemehthings.armament.util.ModDamageSource;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import github.imakemehthings.armament.ArmamentMain;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

public class FirearmItem extends FirearmInterfaceItem {

    public FirearmItem (Properties properties) { // not changing values here
        super(properties);
    }

    protected Vec3 getBezierPoint(ArrayList<Vec3> controlPoints, double delta) {
        if(controlPoints.size() <= 0) return new Vec3(0d, 0d, 0d);
        if(controlPoints.size() == 1) return controlPoints.get(0);
        ArrayList<Vec3> newPoints = new ArrayList<>();
        for(int i = 0; i < controlPoints.size() - 1; i++) {
            newPoints.add(controlPoints.get(i).lerp(controlPoints.get(i+1), delta));
        }
        return getBezierPoint(newPoints, delta);
    }

    protected void drawTrajectory(Level level, ArrayList<Vec3> points, double step) {
        Vec3 particlePoint;
        for(double i = 0; i <= 1; i += step) {
            particlePoint = getBezierPoint(points, i);
            level.addParticle(this.trailParticle, true, particlePoint.x, particlePoint.y, particlePoint.z, 0d, -0.05d, 0d);
        }
    }

    protected void hitAndLaunchParticles(Level level, Vec3 launch, Vec3 hit) {
        level.addParticle(this.launchParticle, true, launch.x, launch.y, launch.z, 0d, -0.05d, 0d);
        level.addParticle(this.hitParticle, true, hit.x, hit.y, hit.z, 0d, -0.05d, 0d);
    }

    protected void applyEffects(Level level, Player player, Entity target) {
        target.hurt(this.damageType, 5f + this.damageModifier);
    }

    protected Vec3 spreadCalc(Player player, Random rng) {
        float xRot;
        if(this.ySpread == 0f) {
            xRot = player.getXRot();
        } else {
            xRot = player.getXRot()+rng.nextFloat(-this.ySpread, this.ySpread);
        }

        float yRot;
        if(this.xSpread == 0f) {
            yRot = player.getYRot();
        } else {
            yRot = player.getYRot()+rng.nextFloat(-this.xSpread, this.xSpread);
        }
        return Vec3.directionFromRotation(xRot, yRot);
    }

    protected boolean shootStraight(Level level, Player player) {
        boolean didHit = false;
        Random rng = new Random();
        Vec3 origin = player.getEyePosition();
        Vec3 spread = spreadCalc(player, rng);
        Vec3 endpoint = spread;
        endpoint = endpoint.scale(range);
        endpoint = origin.add(endpoint);
        CombinedRaycast raycast = new CombinedRaycast(origin, endpoint);
        EntityHitResult hitE = raycast.entityClip(player, level);
        BlockHitResult hitB;

        ArrayList<Vec3> displayPoints = new ArrayList<>();
        displayPoints.add(origin.add(spread));
        if(hitE != null) {
            displayPoints.add(hitE.getLocation());
            applyEffects(level, player, hitE.getEntity());
            didHit = true;
        } else {
            displayPoints.add(endpoint);
        }
        drawTrajectory(level, displayPoints, 0.1);
        hitAndLaunchParticles(level, displayPoints.get(0), displayPoints.get(1));
        return didHit;
    }

    protected boolean shootSimulated(Level level, Player player) {
        boolean didHit = false;
        Random rng = new Random();
        ArrayList<Vec3> raycastPositions = new ArrayList<>();
        Vec3 position = player.getEyePosition();
        raycastPositions.add(position);
        Vec3 velocity = spreadCalc(player, rng);
        velocity = velocity.scale(15d * this.velocityMultiplier);
        position = position.add(velocity);
        raycastPositions.add(position);

        Vec3 gravity = new Vec3(0d, -9.8d, 0d);
        for(int i = 0; i < 10; i++) { // simulate projectile
            velocity = velocity.add(gravity);
            position = position.add(velocity);
            raycastPositions.add(position);
        }
        CombinedRaycast raycast = new CombinedRaycast(raycastPositions.get(0), raycastPositions.get(1));
        EntityHitResult hitE;
        BlockHitResult hitB;
        for(int i = 0; i < (raycastPositions.size()-1); i++) {
            raycast.start = raycastPositions.get(i);
            raycast.end = raycastPositions.get(i+1);
            hitE = raycast.entityClip(player, level);
            if(hitE != null) {
                applyEffects(level, player, hitE.getEntity());
                raycastPositions = (ArrayList<Vec3>) raycastPositions.subList(0, i+1); // remove underground points for the 'ol visuals
                didHit = true;
                break;
            }
        }
        drawTrajectory(level, raycastPositions, 0.01);
        hitAndLaunchParticles(level, raycastPositions.get(0), raycastPositions.get(raycastPositions.size() - 1));
        return didHit;
    }

    protected void reload() {
        // check ammo items here
        float ammoActuallyLoaded = 0;
        if(!this.reloadCutoff) {
            if(this.reloadSize + this.ammoLoaded > this.maxCapacity) return;
            ammoActuallyLoaded = this.reloadSize;
            this.ammoLoaded += ammoActuallyLoaded;
        } else {
            ammoActuallyLoaded = Math.min(Math.abs(this.maxCapacity - this.ammoLoaded), this.reloadSize);
            this.ammoLoaded += ammoActuallyLoaded;
        }
        // subtract ammo items here and set ammo properties
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(Component.literal("§l§7" + this.notes + "\n"));
        tooltip.add(Component.literal(this.ammoLoaded + " loaded /" + this.maxCapacity + " max"));

        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if(level.isClientSide()) return super.use(level, player, usedHand);
        if(player.getCooldowns().isOnCooldown(this)) return super.use(level, player, usedHand);

        if(this.ammoLoaded <= 0) {
            reload();
            player.getCooldowns().addCooldown(this, this.reloadTime);
            ItemStack stack = player.getItemInHand(usedHand); // breaking
            stack.setDamageValue(stack.getDamageValue() + 1);
            if (stack.getDamageValue() >= stack.getMaxDamage()) stack.setCount(stack.getCount() - 1);
            return super.use(level, player, usedHand);
        }

        for(int i = 0; i < (this.volleyCount * 1); i++) {
            if(this.shootsStraight) {
                shootStraight(level, player);
            } else {
                shootSimulated(level, player);
            }
        }

        this.ammoLoaded -= this.volleyCount;
        player.getCooldowns().addCooldown(this, this.shotTime);

        ItemStack stack = player.getItemInHand(usedHand); // breaking
        stack.setDamageValue(stack.getDamageValue() + 1);
        if (stack.getDamageValue() >= stack.getMaxDamage()) stack.setCount(stack.getCount() - 1);

        return super.use(level, player, usedHand);
    }
}
