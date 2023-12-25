package net.aerelldev.mbtlhihh.mixin;

import net.aerelldev.mbtlhihh.core.IMbtlhihhPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class MbtlhihhPlayerMixin implements IMbtlhihhPlayer {

    @Unique
    private final float MIN_HEALTH = 0.5F;

    @Unique
    private boolean IS_MBTLHIHH = false;

    @Override
    public boolean getMbtlhihh() {
        return this.IS_MBTLHIHH;
    }

    @Override
    public void setIsMbtlhihh(boolean value) {
        this.IS_MBTLHIHH = value;
    }

    @Inject(method = "addAdditionalSaveData", at = @At("HEAD"))
    protected void injectAddAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        compound.putBoolean("IsMbtlhihh", this.IS_MBTLHIHH);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("HEAD"))
    protected void injectReadAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        this.IS_MBTLHIHH = compound.getBoolean("IsMbtlhihh");
    }

    @Inject(method = "actuallyHurt", at = @At("HEAD"), cancellable = true)
    protected void injectActuallyHurt(DamageSource damageSource, float damageAmount, CallbackInfo ci) {
        if(this.IS_MBTLHIHH && ((LivingEntity) (Object) this).getHealth() - damageAmount < this.MIN_HEALTH) {
            ((LivingEntity) (Object) this).setHealth(this.MIN_HEALTH);
            ci.cancel();
        }
    }
}
