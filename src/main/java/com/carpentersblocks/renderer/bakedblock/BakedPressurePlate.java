package com.carpentersblocks.renderer.bakedblock;

import java.util.function.Function;

import com.carpentersblocks.renderer.AbstractBakedModel;
import com.carpentersblocks.renderer.QuadContainer;
import com.carpentersblocks.util.states.StatePart;
import com.carpentersblocks.util.states.StateUtil;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BakedPressurePlate extends AbstractBakedModel {

    public BakedPressurePlate(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		super(state, format, bakedTextureGetter);
	}
	
	@Override
	protected void fillQuads(QuadContainer quadContainer) {
		StateUtil util = new StateUtil();
		quadContainer.setStateful(true);
		for (StatePart part : _state.getStateParts()) {
			quadContainer.addAll(util.getQuads(part));
		}
	}
	
}