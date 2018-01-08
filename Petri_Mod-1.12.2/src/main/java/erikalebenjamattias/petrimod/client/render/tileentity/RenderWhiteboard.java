package erikalebenjamattias.petrimod.client.render.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import erikalebenjamattias.petrimod.blocks.BlockWhiteboard;
import erikalebenjamattias.petrimod.tileentity.TileEntityWhiteboard;

public class RenderWhiteboard extends TileEntitySpecialRenderer<TileEntityWhiteboard> {

	private static final ResourceLocation whiteboardTexture = new ResourceLocation("petrimod:textures/entity/whiteboard.png");
	
	@Override
	public void render(TileEntityWhiteboard te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		super.render(te, x, y, z, partialTicks, destroyStage, alpha);
		IBlockState state = te.getWorld().getBlockState(te.getPos());
		GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5, y + 0.5, z + 0.5);
        GlStateManager.rotate(180.0F - ((EnumFacing)state.getValue(BlockWhiteboard.FACING)).getHorizontalIndex() * 90, 0.0F, 1.0F, 0.0F);
        GlStateManager.enableRescaleNormal();
		this.bindTexture(whiteboardTexture);
        GlStateManager.scale(0.0625F, 0.0625F, 0.0625F);
		
		this.renderBoard(te);

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
	}
	
	private void renderBoard(TileEntityWhiteboard te) {
		double u;
		double v;
		World world = te.getWorld();
		BlockPos pos = te.getPos();
		IBlockState state = world.getBlockState(pos);
		BlockWhiteboard block = (BlockWhiteboard)world.getBlockState(pos).getBlock();
		
		if(block.isAdjacentWhiteboardAt(0, world, state, pos) && block.isAdjacentWhiteboardAt(1, world, state, pos) && block.isAdjacentWhiteboardAt(2, world, state, pos) && block.isAdjacentWhiteboardAt(3, world, state, pos)) {
			u = 0.25;
			v = 0.25;
		}
		else if(block.isAdjacentWhiteboardAt(1, world, state, pos) && block.isAdjacentWhiteboardAt(2, world, state, pos) && block.isAdjacentWhiteboardAt(3, world, state, pos)) {
			u = 0.25;
			v = 0;
		}
		else if(block.isAdjacentWhiteboardAt(0, world, state, pos) && block.isAdjacentWhiteboardAt(2, world, state, pos) && block.isAdjacentWhiteboardAt(3, world, state, pos)) {
			u = 0.5;
			v = 0.25;
		}
		else if(block.isAdjacentWhiteboardAt(0, world, state, pos) && block.isAdjacentWhiteboardAt(1, world, state, pos) && block.isAdjacentWhiteboardAt(3, world, state, pos)) {
			u = 0.25;
			v = 0.5;
		}
		else if(block.isAdjacentWhiteboardAt(0, world, state, pos) && block.isAdjacentWhiteboardAt(1, world, state, pos) && block.isAdjacentWhiteboardAt(2, world, state, pos)) {
			u = 0;
			v = 0.25;
		}
		else if(block.isAdjacentWhiteboardAt(1, world, state, pos) && block.isAdjacentWhiteboardAt(2, world, state, pos)) {
			u = 0;
			v = 0;
		}
		else if(block.isAdjacentWhiteboardAt(2, world, state, pos) && block.isAdjacentWhiteboardAt(3, world, state, pos)) {
			u = 0.5;
			v = 0;
		}
		else if(block.isAdjacentWhiteboardAt(0, world, state, pos) && block.isAdjacentWhiteboardAt(3, world, state, pos)) {
			u = 0.5;
			v = 0.5;
		}
		else if(block.isAdjacentWhiteboardAt(0, world, state, pos) && block.isAdjacentWhiteboardAt(1, world, state, pos)) {
			u = 0;
			v = 0.5;
		}
		else if(block.isAdjacentWhiteboardAt(1, world, state, pos) && block.isAdjacentWhiteboardAt(3, world, state, pos)) {
			u = 0.25;
			v = 0.75;
		}
		else if(block.isAdjacentWhiteboardAt(0, world, state, pos) && block.isAdjacentWhiteboardAt(2, world, state, pos)) {
			u = 0;
			v = 0.75;
		}
		else if(block.isAdjacentWhiteboardAt(2, world, state, pos)) {
			u = 0.75;
			v = 0.25;
		}
		else if(block.isAdjacentWhiteboardAt(3, world, state, pos)) {
			u = 0.75;
			v = 0.75;
		}
		else if(block.isAdjacentWhiteboardAt(0, world, state, pos)) {
			u = 0.75;
			v = 0.5;
		}
		else if(block.isAdjacentWhiteboardAt(1, world, state, pos)) {
			u = 0.5;
			v = 0.75;
		}
		else {
			u = 0.75;
			v = 0;
		}
		
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
        bufferbuilder.pos(-8, 8, 7).tex(u + 0.25, v).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(8, 8, 7).tex(u, v).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(8, -8, 7).tex(u, v + 0.25).normal(0.0F, 0.0F, -1.0F).endVertex();
        bufferbuilder.pos(-8, -8, 7).tex(u + 0.25, v + 0.25).normal(0.0F, 0.0F, -1.0F).endVertex();
        
        /*if(!block.isAdjacentWhiteboardAt(2, world, state, pos)) {
        	bufferbuilder.pos(8, -8, 5).tex(1, 0.734375).normal(0.0F, 0.0F, -1.0F).endVertex();
            bufferbuilder.pos(8, -8, 7).tex(0.75, 0.734375).normal(0.0F, 0.0F, -1.0F).endVertex();
            bufferbuilder.pos(-8, -8, 7).tex(0.75, 0.765625).normal(0.0F, 0.0F, -1.0F).endVertex();
            bufferbuilder.pos(-8, -8, 5).tex(1, 0.765625).normal(0.0F, 0.0F, -1.0F).endVertex();

        	bufferbuilder.pos(-8, -8, 5).tex(1, 0.734375).normal(0.0F, 0.0F, -1.0F).endVertex();
            bufferbuilder.pos(-8, -8, 7).tex(0.75, 0.734375).normal(0.0F, 0.0F, -1.0F).endVertex();
            bufferbuilder.pos(8, -8, 7).tex(0.75, 0.765625).normal(0.0F, 0.0F, -1.0F).endVertex();
            bufferbuilder.pos(8, -8, 5).tex(1, 0.765625).normal(0.0F, 0.0F, -1.0F).endVertex();
        }*/
        
        tessellator.draw();
        
        float red = 0;
        float green = 0;
        float blue = 0;
        
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        for(int i = 0; i < 16; i++) {
        	for(int j = 0; j < 16; j++) {
        		if(te.getColorAt(i, j) > 0) {
        			if(te.getColorAt(i, j) == 2) {
        				red = 1;
        		        green = 0;
        		        blue = 0;
        			}
        			else if(te.getColorAt(i, j) == 3) {
        				red = 0;
        		        green = 0.7F;
        		        blue = 0.2F;
        			}
        			else if(te.getColorAt(i, j) == 4) {
        				red = 0;
        		        green = 0;
        		        blue = 0.8F;
        			}
        			else {
        				red = 0;
        		        green = 0;
        		        blue = 0;
        			}
			        bufferbuilder.pos(i - 8, j - 7, 6.95).color(red, green, blue, 1).endVertex();
			        bufferbuilder.pos(i - 7, j - 7, 6.95).color(red, green, blue, 1).endVertex();
			        bufferbuilder.pos(i - 7, j - 8, 6.95).color(red, green, blue, 1).endVertex();
			        bufferbuilder.pos(i - 8, j - 8, 6.95).color(red, green, blue, 1).endVertex();
        		}
        	}
        }
        tessellator.draw();
	}
	
}
