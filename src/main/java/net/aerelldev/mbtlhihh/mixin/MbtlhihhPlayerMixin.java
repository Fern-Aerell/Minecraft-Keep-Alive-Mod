package net.aerelldev.mbtlhihh.mixin;

import net.aerelldev.mbtlhihh.core.IMbtlhihhPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
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
    private boolean MBTLHIHH_USE_TOTEM = false;

    @Override
    public boolean getMbtlhihh() {
        return this.IS_MBTLHIHH;
    }

    @Override
    public boolean getMbtlhihhUseTotem() {
        return this.MBTLHIHH_USE_TOTEM;
    }

    @Override
    public void setIsMbtlhihh(boolean value) {
        this.IS_MBTLHIHH = value;
    }

    @Override
    public void setMbtlhihhUseTotem(boolean value) {
        this.MBTLHIHH_USE_TOTEM = value;
    }

    @Inject(method = "addAdditionalSaveData", at = @At("HEAD"))
    protected void injectAddAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        compound.putBoolean("IsMbtlhihh", this.IS_MBTLHIHH);
        compound.putBoolean("mbtlhihhUseTotem", this.MBTLHIHH_USE_TOTEM);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("HEAD"))
    protected void injectReadAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        this.IS_MBTLHIHH = compound.getBoolean("IsMbtlhihh");
        this.MBTLHIHH_USE_TOTEM = compound.getBoolean("mbtlhihhUseTotem");
    }

    @Inject(method = "actuallyHurt", at = @At("HEAD"), cancellable = true)
    protected void injectActuallyHurt(DamageSource damageSource, float damageAmount, CallbackInfo ci) {
        LivingEntity livingPlayerEntity = ((LivingEntity) (Object) this);
        if(this.IS_MBTLHIHH && livingPlayerEntity.getHealth() - damageAmount < this.MIN_HEALTH) {
            if(this.MBTLHIHH_USE_TOTEM) {
                if(livingPlayerEntity.getMainHandItem().getItem() != Items.TOTEM_OF_UNDYING && livingPlayerEntity.getOffhandItem().getItem() != Items.TOTEM_OF_UNDYING) {
                    livingPlayerEntity.setHealth(this.MIN_HEALTH);
                    ci.cancel();
                }
            }else{
                livingPlayerEntity.setHealth(this.MIN_HEALTH);
                ci.cancel();
            }
        }
    }
}
