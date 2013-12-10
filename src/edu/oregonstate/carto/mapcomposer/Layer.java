/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.oregonstate.carto.mapcomposer;

import com.jhlabs.composite.MultiplyComposite;
import com.jhlabs.image.BicubicScaleFilter;
import com.jhlabs.image.ImageUtils;
import com.jhlabs.image.TileImageFilter;
import edu.oregonstate.carto.tilemanager.Tile;
import edu.oregonstate.carto.tilemanager.TileSet;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Nicholas Hallahan nick@theoutpost.io
 */
public class Layer {

    public static final int BLENDINGNORMAL = 0;
    public static final int BLENDINGMULTIPLY = 1;
    @XmlElement(name = "visible")
    private boolean visible = true;
    @XmlElement(name = "layerName")
    private String layerName;
    private TileSet imageTileSet;
    @XmlElement(name = "textureURL")
    private String textureURL;
    private TileSet maskTileSet;
    @XmlElement(name = "blending")
    private int blending = BLENDINGNORMAL;
    @XmlElement(name = "opacity")
    private float opacity = 1;
    @XmlElement(name = "curveURL")
    private String curveURL;
//    @XmlElement(name = "tint")
//    private Tint tint = null;
    @XmlElement(name = "textureScale")
    private float textureScale = 1f;
    @XmlElement(name = "invertMask")
    private boolean invertMask = false;
    @XmlElement(name = "maskBlur")
    private float maskBlur = 0;

//    @XmlElement(name = "shadow")
//    private Shadow shadow = null;
//    @XmlElement(name = "emboss")
//    private Emboss emboss = null;
    
    public void renderToTile(Graphics2D g2d, int z, int x, int y) {

        if (isBlendingNormal()) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getOpacity()));
        } else {
            g2d.setComposite(new MultiplyComposite(getOpacity()));
        }

        BufferedImage textureImage = null;
        if (textureURL != null) {
            try {
                textureImage = ImageIO.read(new File(textureURL));
                textureImage = ImageUtils.convertImageToARGB(textureImage);

                // scale texture patch if needed
                if (textureScale != 1f) {
                    int textureW = (int) (textureImage.getWidth() * this.textureScale);
                    int textureH = (int) (textureImage.getHeight() * this.textureScale);
                    BicubicScaleFilter scaleFilter = new BicubicScaleFilter(textureW, textureH);
                    textureImage = scaleFilter.filter(textureImage, null);
                }

                TileImageFilter tiler = new TileImageFilter();
                tiler.setHeight(Map.TILE_SIZE * 3);
                tiler.setWidth(Map.TILE_SIZE * 3);
                BufferedImage dst = new BufferedImage(Map.TILE_SIZE * 3, Map.TILE_SIZE * 3, BufferedImage.TYPE_INT_ARGB);
                textureImage = tiler.filter(textureImage, dst);
            } catch (IOException ex) {
                textureImage = null;
                // FIXME
                System.err.println("could not load texture image");
            }
        }


        // load tile image
        BufferedImage image = null;
        if (imageTileSet != null) {
            Tile tile = imageTileSet.getTile(z, x, y);
            try {
                image = (BufferedImage) tile.createMegaTile();
                // convert to ARGB. All following manipulations are optimized for 
                // this modus.
                image = ImageUtils.convertImageToARGB(image);
            } catch (IOException exc) {
                image = null;
            }
        }

        // create solid white background image if no image has been loaded 
        if (image == null) {
            image = new BufferedImage(Map.TILE_SIZE * 3, Map.TILE_SIZE * 3, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = image.createGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, Map.TILE_SIZE * 3, Map.TILE_SIZE * 3);
            g.dispose();
        }
        // gradation curve
        if (this.curveURL != null && this.curveURL.length() > 0) {
            // FIXME
            //image = curve(image, this.curveURL);
        }

        /*
         // tinting
         if (this.tint
         != null) {
         // use the pre-existing image for modulating brightness if the image
         // exists (i.e. a texture image has been created or an image has
         // been loaded).
         if (isValidImage(textureImage) || isValidImage(imageTile)) {
         TintFilter tintFilter = new TintFilter();
         tintFilter.setTint(this.tint.getTintColor());
         image = tintFilter.filter(image, null);
         this.measureTime("Image tinted");
         } else {
         // no pre-existing image, create a solid color image
         image = solidColorImage(w, h, this.tint.getTintColor());
         this.measureTime("New solid color image");
         }
         }
         // masking
         BufferedImage maskImage = maskImageTile;

         if (isValidImage(maskImage)) {
         if (this.maskBlur > 0) {

         BoxBlurFilter blurFilter = new BoxBlurFilter();
         blurFilter.setHRadius(this.maskBlur);
         blurFilter.setVRadius(this.maskBlur);
         blurFilter.setPremultiplyAlpha(false);
         blurFilter.setIterations(1);

                
         maskImage = blurFilter.filter(maskImage, null);

         }
         image = alphaChannelFromGrayImage(image, maskImage, this.invertMask);
         this.measureTime("Alpha channel applied");
         }
         // embossing
         if (this.emboss
         != null) {
         // this solution works fine, but is slow
         LightFilter lightFilter = new LightFilter();
         lightFilter.setBumpSource(LightFilter.BUMPS_FROM_IMAGE_ALPHA);
         lightFilter.setBumpHeight(this.emboss.getEmbossHeight());
         lightFilter.setBumpSoftness(this.emboss.getEmbossSoftness());
         LightFilter.Light forestLight = (LightFilter.Light) (lightFilter.getLights().get(0));
         forestLight.setAzimuth((float) Math.toRadians(this.emboss.getEmbossAzimuth() - 90));
         forestLight.setElevation((float) Math.toRadians(this.emboss.getEmbossElevation()));
         forestLight.setDistance(0);
         forestLight.setIntensity(1f);
         //lightFilter.getMaterial().highlight = 10f;
         lightFilter.getMaterial().highlight = 10f;
         image = lightFilter.filter(image, null);

           
         this.measureTime("Image embossed");
         }
         // drop shadow: draw it onto the destination image
         if (this.shadow
         != null) {
         BufferedImage shadowImage = ImageUtils.cloneImage(image);

         //x negative : left  -  x positive : right
         //y negative : down  -  y positive : up
         //TODO : distinguish x and y offset OR use a mouving offset !!
         ShadowFilter shadowFilter = new ShadowFilter(this.shadow.getShadowFuziness(), this.shadow.getShadowOffset(), -this.shadow.getShadowOffset(), 1f);
         shadowFilter.setShadowColor(this.shadow.getShadowColor().getRGB());
         shadowImage = shadowFilter.filter(shadowImage, null);

         if (imageCollection instanceof TiledImageCollection) {
         g2d.drawImage(((TiledImageCollection) imageCollection).cutTileFromMegaTile(shadowImage), null, null);
         } else if (imageCollection instanceof DirectoryImageCollection) {
         g2d.drawImage(shadowImage, null, null);
         }
         this.measureTime("Drop shadow");
         }
         */
        // draw this layer into the destination image

        BufferedImage tileImage = image.getSubimage(Map.TILE_SIZE, Map.TILE_SIZE, Map.TILE_SIZE, Map.TILE_SIZE);
        g2d.drawImage(tileImage, null, null);
    }

    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * @return the layerName
     */
    public String getLayerName() {
        return layerName;
    }

    /**
     * @param layerName the layerName to set
     */
    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    /**
     * @return the imageTileSet
     */
    public TileSet getImageTileSet() {
        return imageTileSet;
    }

    /**
     * @param imageTileSet the imageTileSet to set
     */
    public void setImageTileSet(TileSet imageTileSet) {
        this.imageTileSet = imageTileSet;
    }

    /**
     * @return the textureURL
     */
    public String getTextureURL() {
        return textureURL;
    }

    /**
     * @param textureURL the textureURL to set
     */
    public void setTextureURL(String textureURL) {
        this.textureURL = textureURL;
    }

    /**
     * @return the maskTileSet
     */
    public TileSet getMaskTileSet() {
        return maskTileSet;
    }

    /**
     * @param maskTileSet the maskTileSet to set
     */
    public void setMaskTileSet(TileSet maskTileSet) {
        this.maskTileSet = maskTileSet;
    }

    /**
     * @return the blending
     */
    public boolean isBlendingNormal() {
        return blending == BLENDINGNORMAL;
    }

    /**
     * @param blending the blending to set
     */
    public void setBlending(int blending) {
        this.blending = blending;
    }

    /**
     * @return the opacity
     */
    public float getOpacity() {
        return opacity;
    }

    /**
     * @param opacity the opacity to set
     */
    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    /**
     * @return the curveURL
     */
    public String getCurveURL() {
        return curveURL;
    }

    /**
     * @param curveURL the curveURL to set
     */
    public void setCurveURL(String curveURL) {
        this.curveURL = curveURL;
    }

    /**
     * @return the textureScale
     */
    public float getTextureScale() {
        return textureScale;
    }

    /**
     * @param textureScale the textureScale to set
     */
    public void setTextureScale(float textureScale) {
        this.textureScale = textureScale;
    }

    /**
     * @return the invertMask
     */
    public boolean isInvertMask() {
        return invertMask;
    }

    /**
     * @param invertMask the invertMask to set
     */
    public void setInvertMask(boolean invertMask) {
        this.invertMask = invertMask;
    }

    /**
     * @return the maskBlur
     */
    public float getMaskBlur() {
        return maskBlur;
    }

    /**
     * @param maskBlur the maskBlur to set
     */
    public void setMaskBlur(float maskBlur) {
        this.maskBlur = maskBlur;
    }
}
