package com.twitche.twitche.Controller;

import com.twitche.twitche.Model.Controller.SettingsControllerModel;
import com.twitche.twitche.Model.SettingsDefaultOptions;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;

public class SettingsController implements Initializable {

    @FXML
    private TextField BadgePer;

    @FXML
    private TextField BadgePricePer;

    @FXML
    private TextField animatedPrice;

    @FXML
    private TextField badgePrice;

    @FXML
    private TextField emorePerSpecial;

    @FXML
    private TextField emotePer;

    @FXML
    private TextField emotePrice;

    @FXML
    private TextField emotePricePer;

    @FXML
    private TextField emoteSpecialOffer;

    @FXML
    private TabPane tab;

    @FXML
    private BorderPane mainWindows;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String data = "";
        try {
            File file = new File("settings.xml");
            if(file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    data += scanner.nextLine() + "\n";
                }
                scanner.close();
                String[] split = data.split("\n");
                emotePrice.setText(split[0]);
                badgePrice.setText(split[1]);
                animatedPrice.setText(split[2]);
                emotePer.setText(split[3]);
                emotePricePer.setText(split[4]);
                BadgePer.setText(split[5]);
                BadgePricePer.setText(split[6]);
                emorePerSpecial.setText(split[7]);
                emoteSpecialOffer.setText(split[8]);
            } else {
                emotePrice.setText(String.valueOf(SettingsDefaultOptions.getCostEmotes()));
                badgePrice.setText(String.valueOf(SettingsDefaultOptions.getCostBadge()));
                animatedPrice.setText(String.valueOf(SettingsDefaultOptions.getCostAnimated()));
                emotePer.setText(String.valueOf(SettingsDefaultOptions.getEmotesPer()));
                emotePricePer.setText(String.valueOf(SettingsDefaultOptions.getCostEmotesProm()));
                BadgePer.setText(String.valueOf(SettingsDefaultOptions.getBadgesPer()));
                BadgePricePer.setText(String.valueOf(SettingsDefaultOptions.getCostBadgeProm()));
                emorePerSpecial.setText(String.valueOf(SettingsDefaultOptions.getHowMuchToPromotion()));
                emoteSpecialOffer.setText(String.valueOf(SettingsDefaultOptions.getPromo()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @FXML
    void addTab(ActionEvent event) {
        if(tab.getTabs().size() < 6)
            tab.getTabs().add(createEditableTab("newTab"));
    }

    public Tab createEditableTab(String text) {
        final Label label = new Label(text);
        final TextField textField1 = new TextField();
        final Tab tab = new Tab("",textField1);
        tab.setGraphic(label);
        final TextField textField = new TextField();

        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount()==2) {
                    textField.setText(label.getText());
                    tab.setGraphic(textField);
                    textField.selectAll();
                    textField.requestFocus();
                }
            }
        });

        textField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                label.setText(textField.getText());
                tab.setGraphic(label);
            }
        });


        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
                                Boolean oldValue, Boolean newValue) {
                if (! newValue) {
                    label.setText(textField.getText());
                    tab.setGraphic(label);
                }
            }
        });
        return tab ;
    }

    @FXML
    void saveSettings() throws IOException {

       /* PrintWriter writer = new PrintWriter("settings.txt");
        writer.print("");
        writer.close();

        try (FileWriter fileWriter = new FileWriter("settings.txt", true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            printWriter.println(emotePrice.getText());
            printWriter.println(badgePrice.getText());
            printWriter.println(animatedPrice.getText());

            printWriter.println(emotePer.getText());
            printWriter.println(emotePricePer.getText());

            printWriter.println(BadgePer.getText());
            printWriter.println(BadgePricePer.getText());

            printWriter.println(emorePerSpecial.getText());
            printWriter.println(emoteSpecialOffer.getText());
        } catch (IOException e) {

        }
        SettingsDefaultOptions.setContext(false);*/
        File myObj = new File("settings.xml");
        myObj.delete();
        Properties saveProps = new Properties();
        saveProps.setProperty("emotePrice" , emotePrice.getText());
        saveProps.setProperty("badgePrice" , badgePrice.getText());
        saveProps.setProperty("emotePer" , emotePer.getText());
        saveProps.setProperty("emotePricePer" , emotePricePer.getText());
        saveProps.setProperty("BadgePer" , BadgePer.getText());
        saveProps.setProperty("BadgePricePer" , BadgePricePer.getText());
        saveProps.setProperty("emorePerSpecial" , emorePerSpecial.getText());
        saveProps.setProperty("emoteSpecialOffer" , emoteSpecialOffer.getText());


        saveProps.storeToXML(new FileOutputStream("settings.xml"), "");
    }

    @FXML
    void anyChange(KeyEvent event) {
        SettingsDefaultOptions.setContext(true);
        SettingsControllerModel.getInstance().setEmotePrice(emotePrice);
    }
}
