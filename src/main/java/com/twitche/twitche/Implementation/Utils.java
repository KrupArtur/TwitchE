package com.twitche.twitche.Implementation;
import com.twitche.twitche.Controller.MainController;
import com.twitche.twitche.Model.SettingsDefaultOptions;
import com.twitche.twitche.Model.Size;
import com.twitche.twitche.Model.Type;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;


public class Utils {

    static int x = 156, y = 228, w = 448, h = 448;
    static String filename = "";
    static Image img2 = null;
    public static List<VBox> vBoxs = new ArrayList<>();
    public static Map<Button, TextField> buttonList = new HashMap<>();
    static List<Size> emoteSize = new ArrayList<Size>();
    static List<Size> badgesSize = new ArrayList<Size>();

    public static void cropImageAndSaveToFile(BufferedImage src, int w, int h, int x, int y, String name, int rX, int rY){
        try {
            BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            dst.getGraphics().drawImage(src, 0, 0, w, h, x, y, x + w, y + h, null);
            dst = resizeImage(dst, rX, rY);

            ImageIO.write(dst, "png", new File(name + rX + "x" + rY +".png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Image cropImage(BufferedImage src, int w, int h, int x, int y, int rX, int rY) {
        try {
            BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            dst.getGraphics().drawImage(src, 0, 0, w, h, x, y, x + w, y + h, null);
            dst = resizeImage(dst, rX,rY);

            return SwingFXUtils.toFXImage(dst, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight)  {
        java.awt.Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, java.awt.Image.SCALE_AREA_AVERAGING);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }


    public static void VieweEmote(MouseEvent event) {
        List<Button> emote = new ArrayList<>();
        if (event.getButton() == MouseButton.SECONDARY) {
            if (!MainController.getInstance().getEmote().isVisible()) MainController.getInstance().getEmote().setVisible(true);
            for (Button button : buttonList.keySet()) {
                if (button.getId().equals(((Button) event.getTarget()).getId()))
                    emote.add(button);
            }
            for (Button b : emote) {
                String text = b.getId().substring(b.getId().length() - 3, b.getId().length());

                int t = Integer.parseInt(text.substring(0, 1));
                int r = Integer.parseInt(text.substring(2, 3));

                Image i112x112,i72x72,i56x56,i36x36,i28x28,i18x18;

                i112x112 = cropImage(SwingFXUtils.fromFXImage(img2, null),
                        w, h, x + ((448 * (r - 1)) + (100 * (r - 1))), y + ((448 * (t - 1)) + (100 * (t - 1))),
                        112, 112);
                i72x72 = cropImage(SwingFXUtils.fromFXImage(img2, null),
                        w, h, x + ((448 * (r - 1)) + (100 * (r - 1))), y + ((448 * (t - 1)) + (100 * (t - 1))),
                        72, 72);
                i56x56 = Utils.cropImage(SwingFXUtils.fromFXImage(img2, null),
                        w, h, x + ((448 * (r - 1)) + (100 * (r - 1))), y + ((448 * (t - 1)) + (100 * (t - 1))),
                        56, 56);
                i36x36 = Utils.cropImage(SwingFXUtils.fromFXImage(img2, null),
                        w, h, x + ((448 * (r - 1)) + (100 * (r - 1))), y + ((448 * (t - 1)) + (100 * (t - 1))),
                        36, 36);
                i28x28 = Utils.cropImage(SwingFXUtils.fromFXImage(img2, null),
                        w, h, x + ((448 * (r - 1)) + (100 * (r - 1))), y + ((448 * (t - 1)) + (100 * (t - 1))),
                        28, 28);
                i18x18 = Utils.cropImage(SwingFXUtils.fromFXImage(img2, null),
                        w, h, x + ((448 * (r - 1)) + (100 * (r - 1))), y + ((448 * (t - 1)) + (100 * (t - 1))),
                        18, 18);

                MainController.getInstance().getImg1x1().setImage(i112x112);
                MainController.getInstance().getImg1x2().setImage(i72x72);
                MainController.getInstance().getImg1x3().setImage(i56x56);
                MainController.getInstance().getImg1x4().setImage(i36x36);
                MainController.getInstance().getImg1x5().setImage(i28x28);
                MainController.getInstance().getImg1x6().setImage(i18x18);

                MainController.getInstance().getImg2x1().setImage(i112x112);
                MainController.getInstance().getImg2x2().setImage(i72x72);
                MainController.getInstance().getImg2x3().setImage(i56x56);
                MainController.getInstance().getImg2x4().setImage(i36x36);
                MainController.getInstance().getImg2x5().setImage(i28x28);
                MainController.getInstance().getImg2x6().setImage(i18x18);
            }
        }
    }
    public static void exportAllImg(List<Button> listButton, int wight, int hight) {
        for (Button b : listButton){
            String text = b.getId().substring(b.getId().length()-3, b.getId().length());

            int t = Integer.parseInt(text.substring(0,1));
            int r = Integer.parseInt(text.substring(2,3));

            Utils.cropImageAndSaveToFile(SwingFXUtils.fromFXImage(img2, null),
                    w, h, x + ((448 * (r-1)) + (100 * (r-1))), y +  ((448 * (t-1)) + (100 * (t-1))),
                    filename + "/" + buttonList.get(b).getText(),
                    wight ,hight);
        }
    }

    public static void export(List<Button> buttons, List<Size> sizes)
    {
        for (Button b : buttons){
            String text = b.getId().substring(b.getId().length()-3, b.getId().length());

            int t = Integer.parseInt(text.substring(0,1));
            int r = Integer.parseInt(text.substring(2,3));
            for(Size size: sizes) {
                Utils.cropImageAndSaveToFile(SwingFXUtils.fromFXImage(img2, null),
                        w, h, x + ((448 * (r - 1)) + (100 * (r - 1))), y + ((448 * (t - 1)) + (100 * (t - 1))),
                        filename+ "/" +  buttonList.get(b).getText(),
                        size.getWidth(), size.getHeight());
            }
        }
    }
    public static void vBoxSetVisible(Boolean visible){
        for (VBox box: vBoxs) {
            box.setVisible(visible);
            box.setManaged(visible);
        }
    }

    public static void writeFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("png", "*.png"));
        File file;
        if(SettingsDefaultOptions.getPath().isBlank() || SettingsDefaultOptions.getPath().isEmpty())
        {
            FileSystemView filesys = FileSystemView.getFileSystemView();
            file = new File(filesys.getHomeDirectory().getPath());
        } else {
            file = new File(SettingsDefaultOptions.getPath());
        }

        fileChooser.setInitialDirectory(file);
        fileChooser.setTitle("Open Resource File");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            createTMPFile(selectedFile.getParentFile().getPath());
            setImgToMainWindow(MainController.getInstance().getMainWindows(), selectedFile);
        }
    }

    private static void createTMPFile(String path){
        try {
            File myObj = new File("newSettings.xml");
            myObj.delete();
            Properties saveProps = new Properties();
            saveProps.setProperty("lastpath", path);
            SettingsDefaultOptions.setPath(path);
            saveProps.storeToXML(new FileOutputStream("newSettings.xml"), "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadTMPFile(){
        Properties loadProps = new Properties();
        try {
            loadProps.loadFromXML(new FileInputStream("newSettings.xml"));
            SettingsDefaultOptions.setPath(loadProps.getProperty("lastpath"));
        } catch (FileNotFoundException e){
            //file  file does not exist
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setImgToMainWindow(AnchorPane mainWindows, File file){
        Image img =  SwingFXUtils.toFXImage(Utils.resizeImage(SwingFXUtils.fromFXImage(new Image("file:///"+file.getPath().replace("\\","/")),null), (int)mainWindows.getWidth(), (int)mainWindows.getHeight()),null);
        img2 = new Image(file.getPath());
        filename = file.getPath().replace(".png","");

        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);

        mainWindows.setBackground(bGround);
        Utils.vBoxSetVisible(true);
    }

    public static void exportTo(){
        TextInputDialog dialog = new TextInputDialog("150x150");

        dialog.setTitle("Size");
        dialog.setHeaderText("Size:");
        dialog.setContentText("Size:");
        Optional<String> result = dialog.showAndWait();

        if(!result.isEmpty()) {
            String size[] = result.get().split("x");

            List<Button> emote = new ArrayList<>();
            List<Button> badge = new ArrayList<>();

            new File(filename).mkdirs();

            for (Button button : buttonList.keySet()) {
                if (button.getText().equals(Type.Emote.name())) emote.add(button);
                if (button.getText().equals(Type.Badge.name())) badge.add(button);
            }

            exportAllImg(emote, Integer.parseInt(size[0]), Integer.parseInt(size[1]));
            exportAllImg(badge, Integer.parseInt(size[0]), Integer.parseInt(size[1]));
        }
    }
    public static void export(){
        List<Button> emote = new ArrayList<>();
        List<Button> badge = new ArrayList<>();
        for (Button button: buttonList.keySet()) {
            if(button.getText().equals(Type.Emote.name())) emote.add(button);
            if(button.getText().equals(Type.Badge.name())) badge.add(button);
        }
        new File(filename).mkdirs();
        loadSize();
        if(emoteSize.size() == 0) {
            emoteSize.add(new Size(448, 448));
            emoteSize.add(new Size(112, 112));
            emoteSize.add(new Size(56, 56));
            emoteSize.add(new Size(28, 28));
        }
        Utils.export(emote,emoteSize);

        if(badgesSize.size() == 0) {
            badgesSize.add(new Size(448, 448));
            badgesSize.add(new Size(72, 72));
            badgesSize.add(new Size(36, 36));
            badgesSize.add(new Size(18, 18));
        }
        Utils.export(badge,badgesSize);
    }

    static void loadSize() {
        Properties loadProps = new Properties();
        try {
            loadProps.loadFromXML(new FileInputStream("settingsScalling.xml"));
            List<String> list = new ArrayList<>();
            String[] xml = loadProps.toString().replace("{", "")
                    .replace("}", "")
                    .replace(" ", "")
                    .split(",");

            Collections.addAll(list, xml);
            for (String s : list) {
                if(!s.isEmpty()) {
                    if (String.valueOf(s.charAt(0)).equals("B")) {
                        String[] split = s.substring(3).split("x");
                        badgesSize.add(new Size(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
                    } else {
                        String[] split = s.substring(3).split("x");
                        emoteSize.add(new Size(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            //file  file does not exist
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
