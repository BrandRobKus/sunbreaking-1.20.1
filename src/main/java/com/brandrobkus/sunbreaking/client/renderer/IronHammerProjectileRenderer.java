package com.brandrobkus.sunbreaking.client.renderer;

import com.brandrobkus.sunbreaking.entity.custom.IronHammerProjectileEntity;
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
public class IronHammerProjectileRenderer extends EntityRenderer<IronHammerProjectileEntity> {
    private final ItemRenderer itemRenderer;

    public IronHammerProjectileRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(IronHammerProjectileEntity IronhammerEntity, float yaw, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumers, int light) {
        matrixStack.push();

        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(tickDelta, IronhammerEntity.prevYaw, IronhammerEntity.getYaw())));
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(MathHelper.lerp(tickDelta, IronhammerEntity.prevPitch, IronhammerEntity.getPitch())));

        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(0.0F));

        float rotationX = IronhammerEntity.getRotationX();
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(rotationX));

        matrixStack.scale(0.25F, 0.25F, 0.25F);

        matrixStack.translate(0.1F, -0.3F, 0.0F);

        ItemStack ironHammerItem = IronhammerEntity.getIronHammerStack();
        this.itemRenderer.renderItem(ironHammerItem, net.minecraft.client.render.model.json.ModelTransformationMode.GROUND, light, OverlayTexture.DEFAULT_UV, matrixStack, vertexConsumers, IronhammerEntity.getWorld(), 0);

        matrixStack.pop();
        super.render(IronhammerEntity, yaw, tickDelta, matrixStack, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(IronHammerProjectileEntity entity) {
        return null;
    }
}
