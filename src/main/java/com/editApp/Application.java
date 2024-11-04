package com.editApp;

import com.editApp.stampCreator.StampCreator;
import com.editApp.stampCreator.StampParams;
import com.editApp.stampCreator.StampSignature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

// Приложение Spring-boot, которое запускает функционал библиотеки, собирает проект,
// ищет контроллеры, файлы подключения к БД, шаблоны и все необходимое
@SpringBootApplication
public class Application {

    public final static boolean GRAPHIC_DEBUG = true;
    public final static boolean SERVER_ENABLE = false;

    public static void main(String[] args) {

        if (SERVER_ENABLE){
            SpringApplication.run(Application.class, args);
        }

        if (GRAPHIC_DEBUG) {
            int width = 900;
            String headerText = "Документ d31545668548 5642 .15.15 12125615 41515563415 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 подписан в\nсистеме WirtTheFrog";
            String footerText = "Dno tuta";
            ArrayList<StampSignature> signatures = new ArrayList<>();

            signatures.add(new StampSignature("Сергеевич Дмитрий Шеховцов", "ПЭП\nd31545668548 5642.15.15 12125615 41515563415", "not now"));
            signatures.add(new StampSignature("Шеховцов Дмитрий Сергеевич", "ПЭП\nd31545668548 5642.15.15 12125615 41515563415", "01.01.2020 01:01:01 GMT+01:01"));

            StampParams stampParams = new StampParams(width, headerText, footerText, signatures);

            // TODO move font initialization to StampParamsConstructor
            // TODO maybe add try catch for Font initialization
            stampParams.setBasicFont(new Font("Tahoma", Font.BOLD, 17));
            stampParams.setLowFont(new Font("Tahoma", Font.PLAIN, 12));

            StampCreator stampCreator = new StampCreator();
            stampCreator.setParams(stampParams);

            BufferedImage image = stampCreator.CreateStampV1();

            // save img to file
            File outputFile = new File("image.png");
            try {
                ImageIO.write(image, "png", outputFile);
                System.out.println("file saved to " + outputFile.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("failed to save img: " + e.getMessage());
            }
        }
    }
}