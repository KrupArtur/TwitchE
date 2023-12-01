package com.twitche.twitche.Controller;

import com.twitche.twitche.Main;
import com.twitche.twitche.Model.Controller.SettingsControllerModel;
import com.twitche.twitche.Model.SettingsDefaultOptions;
import com.twitche.twitche.Model.Type;
import com.twitche.twitche.Implementation.Utils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class MainController {

    //region
    @FXML
    private ImageView img1x1;

    @FXML
    private ImageView img1x2;

    @FXML
    private ImageView img1x3;

    @FXML
    private ImageView img1x4;

    @FXML
    private ImageView img1x5;

    @FXML
    private ImageView img1x6;

    @FXML
    private ImageView img2x1;

    @FXML
    private ImageView img2x2;

    @FXML
    private ImageView img2x3;

    @FXML
    private ImageView img2x4;

    @FXML
    private ImageView img2x5;

    @FXML
    private ImageView img2x6;

    @FXML
    private AnchorPane Emote;

    @FXML
    private AnchorPane mainGrind;

    @FXML
    private AnchorPane mainWindows;

    public AnchorPane getEmote() {
        return Emote;
    }

    public ImageView getImg1x1() {
        return img1x1;
    }

    public ImageView getImg1x2() {
        return img1x2;
    }

    public ImageView getImg1x3() {
        return img1x3;
    }

    public ImageView getImg1x4() {
        return img1x4;
    }

    public ImageView getImg1x5() {
        return img1x5;
    }

    public ImageView getImg1x6() {
        return img1x6;
    }

    public ImageView getImg2x1() {
        return img2x1;
    }

    public ImageView getImg2x2() {
        return img2x2;
    }

    public ImageView getImg2x3() {
        return img2x3;
    }

    public ImageView getImg2x4() {
        return img2x4;
    }

    public ImageView getImg2x5() {
        return img2x5;
    }

    public ImageView getImg2x6() {
        return img2x6;
    }

    public AnchorPane getMainWindows() {
        return mainWindows;
    }

    //endregion

    private static MainController instance = new MainController();

    public static void setInstance(MainController mainController){ instance = mainController; }

    public static MainController getInstance(){
        return instance;
    }

    public void initialize() throws IOException {
        Utils.loadTMPFile();
        for(int i=0; i<mainWindows.getChildren().size(); i++)
            if(mainWindows.getChildren().get(i) instanceof VBox) {
                Utils.vBoxs.add((VBox)mainWindows.getChildren().get(i));
            }

        for(int i=0; i<mainWindows.getChildren().size(); i++)
            if(mainWindows.getChildren().get(i) instanceof VBox) {
                for(int x=0; x<((VBox)mainWindows.getChildren().get(i)).getChildren().size(); x++)
                    Utils.buttonList.put((Button) (((VBox)mainWindows.getChildren().get(i)).getChildren().get(0)), (TextField) (((VBox)mainWindows.getChildren().get(i)).getChildren().get(1)));
            }

        Utils.vBoxSetVisible(false);
    }

    @FXML
    void changeTo(ActionEvent event)
    {
        changeButton((Button)event.getTarget());
    }

    @FXML
    void openFile(ActionEvent event){
       Utils.writeFile();
    }

    @FXML
    void ExportTo(ActionEvent event) {
        Utils.exportTo();
    }
    @FXML
    void Export(ActionEvent event) {
        Utils.export();
    }
    @FXML
    void mouseClicked(MouseEvent event) throws IOException {
        Utils.VieweEmote(event);
    }
    @FXML
    void readFile(DragEvent event) {
        Dragboard db = event.getDragboard();
        File file = new File(db.getFiles().get(0).getPath());

        Utils.setImgToMainWindow(mainWindows,file);
    }

    @FXML
    void dragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.LINK);
        }
    }

    public void changeButton(Button button){
        if(Type.None.name().equals(button.getText())) {
            button.setStyle("-fx-background-color: rgba(0,255,0,0.1)");
            button.setText(Type.Emote.name());
        } else if(Type.Emote.name().equals(button.getText())){
            button.setStyle("-fx-background-color: rgba(255,128,0,0.1)");
            button.setText(Type.Badge.name());
        } else if(Type.Badge.name().equals(button.getText())){
            button.setStyle("-fx-background-color: rgba(255,255,255,0.1)");
            button.setText(Type.None.name());
        }
    }

    @FXML
    void openCalculator(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader =new FXMLLoader(Main.class.getResource("calculator.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("TwitchE - MrMepi & Doootka");
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(mainGrind.getScene().getWindow());
            stage.show();
        } catch (IOException e) {

        }
    }

    @FXML
    void showView(MouseEvent event) {
        Label label = (Label) event.getSource();
        if(label.getText().equals("⮟")) {
            label.setText("⮝");
            Main.stage.setHeight(850);
        } else {
            label.setText("⮟");
            Main.stage.setHeight(720);
        }
    }

    @FXML
    void openSettings(ActionEvent event) {
        try {
           FXMLLoader fxmlLoader =new FXMLLoader(Main.class.getResource("settings.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("TwitchE - MrMepi & Doootka");
            stage.setResizable(false);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    if(SettingsDefaultOptions.isContext()) {
                        we.consume();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                                "Click the Yes button to save changes and exit",
                                ButtonType.YES,
                                ButtonType.NO);

                        Optional<ButtonType> result = alert.showAndWait();
                        if(result.orElse(ButtonType.YES) == ButtonType.YES)
                        {
                            System.out.println(SettingsControllerModel.getInstance().getEmotePrice().getText());
                        } else {
                            stage.close();
                        }
                    }
                    else
                        stage.close();
                }
            });
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(mainGrind.getScene().getWindow());
            stage.show();

        } catch (IOException e) {

        }
    }


    @FXML
    void openSettingsScalling(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader =new FXMLLoader(Main.class.getResource("settingsScalling.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("TwitchE - MrMepi & Doootka");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(mainGrind.getScene().getWindow());
            stage.show();
        } catch (IOException e) {

        }

    }
}