package github.imakemehthings.armament.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class CombinedRaycast {

    public Vec3 start;
    public Vec3 end;
    public double step;
    public boolean hitFluids;

    public CombinedRaycast(Vec3 s, Vec3 e) {
        this.start = s;
        this.end = e;
        this.step = 0.001;
        this.hitFluids = false;
    }

    public CombinedRaycast(Vec3 s, Vec3 e, double step, boolean fluid) {
        this.start = s;
        this.end = e;
        this.step = step;
        this.hitFluids = fluid;
    }

    public EntityHitResult entityClip(Entity ignore, Level level) {
        List<Entity> entities = level.getEntities(ignore, new AABB(start, end)); // the AABB here is an optimization forced by the game lol
        Optional<Vec3> hitPoint;
        for(Entity ent: entities) {
            hitPoint = ent.getBoundingBox().clip(start, end);
            if(hitPoint.isPresent()) return new EntityHitResult(ent, hitPoint.get());
        }
        return null;
    }

    public BlockHitResult blockClip(Entity ignore, Level level) {
        return (level.clip(new ClipContext(start, end, ClipContext.Block.COLLIDER, (hitFluids ? ClipContext.Fluid.ANY : ClipContext.Fluid.NONE), ignore)));
    }
}
