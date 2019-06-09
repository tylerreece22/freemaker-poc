package com.freemarker.poc.service;

import com.freemarker.poc.dto.IdCard;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class TemplateService {

    @Autowired
    Configuration config;

    public String createIdCard(IdCard idCard) throws IOException, TemplateException {
        Map<String, Object> model = new HashMap<>();
        model.put("userName", idCard.getUserName());
        model.put("userId", idCard.getUserId());
        Template template = config.getTemplate("template.html");
        StringWriter stringWriter = new StringWriter();
        template.process(model, stringWriter);
        String html = stringWriter.toString();

        JLabel label = new JLabel(html);
        // Pay close attention to how odd the difference in size is between this label rendering and
        // the height and width in the HTML. Could be from the jpeg losing some pixels somewhere or
        // it could just be yet another weird intricacy of rendering with JLabel
        label.setSize(290, 174);

        BufferedImage image = new BufferedImage(
                label.getWidth(), label.getHeight(),
                BufferedImage.TYPE_INT_ARGB);

        // paint the html to an image
        Graphics g = image.getGraphics();
        label.paint(g);
        g.dispose();

        // get the byte array of the image
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        ImageIO.write(image, "png", new File("test.png"));
        byte[] bytes = baos.toByteArray();
        return new String(Base64.getEncoder().encode(bytes));
    }
}
