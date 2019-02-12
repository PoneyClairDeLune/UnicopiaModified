package com.minelittlepony.unicopia.mixin;

import java.lang.reflect.Field;

import com.minelittlepony.unicopia.forgebullshit.FUF;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.util.math.AxisAlignedBB;

@FUF(reason = "Waiting for mixins?")
public interface MixinEntity {

    abstract class Player extends net.minecraft.entity.player.EntityPlayer {
        private Player() { super(null, null);}

        public static DataParameter<Byte> getModelFlag() {
            return PLAYER_MODEL_FLAG;
        }

    }
    abstract class Shulker extends EntityShulker {
        private Shulker() { super(null);}

        private static boolean __init;
        private static Field __peekAmount = null;

        public static void setPeek(EntityShulker shulker, float peekAmount) {
            initFields();

            try {
                if (__peekAmount != null) {
                    __peekAmount.set(shulker, peekAmount);
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }

        }

        private static void initFields() {
            if (!__init && __peekAmount == null) {
                Field[] fields = EntityShulker.class.getDeclaredFields();
                __peekAmount = fields[fields.length - 3];
                __peekAmount.setAccessible(true);
            }
        }
    }

    static void setSize(Entity self, float width, float height) {
        if (self.width != width || self.height != height) {
            float f = self.width;
            self.width = width;
            self.height = height;

            if (self.width < f) {
                double d0 = width / 2;
                self.setEntityBoundingBox(new AxisAlignedBB(
                        self.posX - d0, self.posY, self.posZ - d0,
                        self.posX + d0, self.posY + self.height, self.posZ + d0));
                return;
            }

            AxisAlignedBB axisalignedbb = self.getEntityBoundingBox();
            self.setEntityBoundingBox(new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.minX + (double)self.width, axisalignedbb.minY + (double)self.height, axisalignedbb.minZ + (double)self.width));

            if (self.width > f/* && !self.firstUpdate*/ && !self.world.isRemote) {
                self.move(MoverType.SELF, (double)(f - self.width), 0, (double)(f - self.width));
            }
        }
    }
}
