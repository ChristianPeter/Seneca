/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.service;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.xslf.XSLFSlideShow;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import sol.neptune.seneca.entities.Document;

/**
 *
 * @author murdoc
 */
public class PowerPointConverter {

    public static List<Document> convertPPT(byte[] data) throws IOException {

        List<Document> result = new ArrayList<Document>();
        SlideShow ss = new SlideShow(new ByteArrayInputStream(data));
        Dimension dx = new Dimension(1024, 768);
        ss.setPageSize(dx);
        Slide[] slides = ss.getSlides();
        BufferedImage b;
        for (Slide slide : slides){
            b = new BufferedImage(1024, 768, BufferedImage.TYPE_INT_ARGB);
            slide.draw((Graphics2D) b.getGraphics());
            ByteArrayOutputStream bx = new ByteArrayOutputStream();
            ImageIO.write(b, "png", bx);
            byte[] barr = bx.toByteArray();
            Document d = new Document();
            d.setImageData(barr);
            d.setName(slide.getTitle());
            result.add(d);
        }
        return result;
    }

    public static List<Document> convertPPTX(byte[] data) throws IOException {

        List<Document> result = new ArrayList<Document>();
        XMLSlideShow pptx = new XMLSlideShow(new ByteArrayInputStream(data));
        Dimension dx = new Dimension(1024, 768);
        pptx.setPageSize(dx);
        XSLFSlide[] slides = pptx.getSlides();
        BufferedImage b;
        for (XSLFSlide slide : slides) {
            b = new BufferedImage(1024, 768, BufferedImage.TYPE_INT_ARGB);
            slide.draw((Graphics2D) b.getGraphics());
            ByteArrayOutputStream bx = new ByteArrayOutputStream();
            ImageIO.write(b, "png", bx);
            byte[] barr = bx.toByteArray();
            Document d = new Document();
            d.setImageData(barr);
            d.setName(slide.getTitle());
            result.add(d);
        }
        return result;

    }
}
