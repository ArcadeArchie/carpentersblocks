package carpentersblocks.renderer.helper;

import net.minecraft.block.BlockGrass;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class VertexHelper {

    public final static int TOP_LEFT     = 0;
    public final static int BOTTOM_LEFT  = 1;
    public final static int BOTTOM_RIGHT = 2;
    public final static int TOP_RIGHT    = 3;

    public final static int SOUTHEAST = TOP_LEFT;
    public final static int NORTHEAST = BOTTOM_LEFT;
    public final static int NORTHWEST = BOTTOM_RIGHT;
    public final static int SOUTHWEST = TOP_RIGHT;

    public final static int TOP_CENTER    = 4;
    public final static int BOTTOM_CENTER = 5;
    public final static int LEFT_CENTER   = 6;
    public final static int RIGHT_CENTER  = 7;

    /**
     * Offset used for faces.
     */
    protected static double offset = 0.0D;

    /**
     * Sets offset for drawing face.
     */
    public static void setOffset(double render_offset)
    {
        offset = render_offset;
    }

    /**
     * Clears offset.
     */
    public static void clearOffset()
    {
        offset = 0.0D;
    }

    /**
     * Returns whether icon top adjusts with render height.
     * This will set the render helpers to translate the icon
     * down with yMax.
     */
    public static boolean iconHasFloatingHeight(Icon icon)
    {
        return icon == BlockGrass.getIconSideOverlay() ||
                icon.getIconName().contains("overlay/overlay_") && icon.getIconName().endsWith("_side");
    }

    /**
     * Applies brightness, color, and adds vertex through tessellator
     */
    public static void setupVertex(RenderBlocks renderBlocks, double x, double y, double z, double u, double v, int vertex)
    {
        Tessellator tessellator = Tessellator.instance;

        if (renderBlocks.enableAO)
        {
            switch(vertex) {
            case BOTTOM_CENTER:
                tessellator.setColorOpaque_F((renderBlocks.colorRedBottomLeft + renderBlocks.colorRedBottomRight) / 2.0F, (renderBlocks.colorGreenBottomLeft + renderBlocks.colorGreenBottomRight) / 2.0F, (renderBlocks.colorBlueBottomLeft + renderBlocks.colorBlueBottomRight) / 2.0F);
                tessellator.setBrightness(RenderHelper.getAverageBrightness(renderBlocks.brightnessBottomLeft, renderBlocks.brightnessBottomRight));
                break;
            case TOP_CENTER:
                tessellator.setColorOpaque_F((renderBlocks.colorRedTopLeft + renderBlocks.colorRedTopRight) / 2.0F, (renderBlocks.colorGreenTopLeft + renderBlocks.colorGreenTopRight) / 2.0F, (renderBlocks.colorBlueTopLeft + renderBlocks.colorBlueTopRight) / 2);
                tessellator.setBrightness(RenderHelper.getAverageBrightness(renderBlocks.brightnessTopLeft, renderBlocks.brightnessTopRight));
                break;
            case LEFT_CENTER:
                tessellator.setColorOpaque_F((renderBlocks.colorRedTopLeft + renderBlocks.colorRedBottomLeft) / 2.0F, (renderBlocks.colorGreenTopLeft + renderBlocks.colorGreenBottomLeft) / 2.0F, (renderBlocks.colorBlueTopLeft + renderBlocks.colorBlueBottomLeft) / 2.0F);
                tessellator.setBrightness(RenderHelper.getAverageBrightness(renderBlocks.brightnessTopLeft, renderBlocks.brightnessBottomLeft));
                break;
            case RIGHT_CENTER:
                tessellator.setColorOpaque_F((renderBlocks.colorRedTopRight + renderBlocks.colorRedBottomRight) / 2.0F, (renderBlocks.colorGreenTopRight + renderBlocks.colorGreenBottomRight) / 2.0F, (renderBlocks.colorBlueTopRight + renderBlocks.colorBlueBottomRight) / 2);
                tessellator.setBrightness(RenderHelper.getAverageBrightness(renderBlocks.brightnessTopRight, renderBlocks.brightnessBottomRight));
                break;
            case TOP_LEFT:
                tessellator.setColorOpaque_F(renderBlocks.colorRedTopLeft, renderBlocks.colorGreenTopLeft, renderBlocks.colorBlueTopLeft);
                tessellator.setBrightness(renderBlocks.brightnessTopLeft);
                break;
            case BOTTOM_LEFT:
                tessellator.setColorOpaque_F(renderBlocks.colorRedBottomLeft, renderBlocks.colorGreenBottomLeft, renderBlocks.colorBlueBottomLeft);
                tessellator.setBrightness(renderBlocks.brightnessBottomLeft);
                break;
            case BOTTOM_RIGHT:
                tessellator.setColorOpaque_F(renderBlocks.colorRedBottomRight, renderBlocks.colorGreenBottomRight, renderBlocks.colorBlueBottomRight);
                tessellator.setBrightness(renderBlocks.brightnessBottomRight);
                break;
            case TOP_RIGHT:
                tessellator.setColorOpaque_F(renderBlocks.colorRedTopRight, renderBlocks.colorGreenTopRight, renderBlocks.colorBlueTopRight);
                tessellator.setBrightness(renderBlocks.brightnessTopRight);
                break;
            }
        }

        tessellator.addVertexWithUV(x, y, z, u, v);
    }

}
