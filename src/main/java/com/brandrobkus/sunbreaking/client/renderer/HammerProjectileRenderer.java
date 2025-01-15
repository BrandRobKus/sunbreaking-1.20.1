package com.brandrobkus.sunbreaking.client.renderer;

import com.brandrobkus.sunbreaking.entity.custom.HammerProjectileEntity;
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
public class HammerProjectileRenderer extends EntityRenderer<HammerProjectileEntity> {
    private final ItemRenderer itemRenderer;

    public HammerProjectileRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(HammerProjectileEntity hammerEntity, float yaw, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumers, int light) {
        matrixStack.push();

        // Align the hammer model to its trajectory
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(tickDelta, hammerEntity.prevYaw, hammerEntity.getYaw()) - 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(tickDelta, hammerEntity.prevPitch, hammerEntity.getPitch())));

        // Apply rotation to correct the hammer's orientation
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(45.0F));

        // Rotate along the X-axis as the projectile flies through the air
        float rotationZ = hammerEntity.getRotationZ();
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(rotationZ));

        // Adjust the hammer's position slightly if it's too far forward/backward
        matrixStack.translate(0.1F, -0.3F, 0.0F);

        // Render the hammer item with the transformation mode set to GROUND
        ItemStack hammerItem = hammerEntity.getHammerStack();
        this.itemRenderer.renderItem(hammerItem, net.minecraft.client.render.model.json.ModelTransformationMode.GROUND, light, OverlayTexture.DEFAULT_UV, matrixStack, vertexConsumers, hammerEntity.getWorld(), 0);

        matrixStack.pop();
        super.render(hammerEntity, yaw, tickDelta, matrixStack, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(HammerProjectileEntity entity) {
        return null;
    }
}
