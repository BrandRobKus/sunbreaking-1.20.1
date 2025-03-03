package com.brandrobkus.sunbreaking.client.renderer;

import com.brandrobkus.sunbreaking.entity.custom.HellbentHammerProjectileEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

@Environment(EnvType.CLIENT)
public class HellbentHammerProjectileRenderer extends EntityRenderer<HellbentHammerProjectileEntity> {
    private final ItemRenderer itemRenderer;

    public HellbentHammerProjectileRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(HellbentHammerProjectileEntity hellbentHammerEntity, float yaw, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumers, int light) {
        matrixStack.push();

        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(tickDelta, hellbentHammerEntity.prevYaw, hellbentHammerEntity.getYaw()) - 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(tickDelta, hellbentHammerEntity.prevPitch, hellbentHammerEntity.getPitch())));

        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(45.0F));

        float rotationZ = hellbentHammerEntity.getRotationZ();
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(rotationZ));

        matrixStack.translate(0.1F, -0.3F, 0.0F);

        ItemStack hammerItem = hellbentHammerEntity.getHammerStack();
        this.itemRenderer.renderItem(hammerItem, net.minecraft.client.render.model.json.ModelTransformationMode.GROUND, light, OverlayTexture.DEFAULT_UV, matrixStack, vertexConsumers, hellbentHammerEntity.getWorld(), 0);

        matrixStack.pop();
        super.render(hellbentHammerEntity, yaw, tickDelta, matrixStack, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(HellbentHammerProjectileEntity entity) {
        return null;
    }
}
