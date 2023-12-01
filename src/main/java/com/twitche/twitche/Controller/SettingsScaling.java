package com.twitche.twitche.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.*;

public class SettingsScaling {

    @FXML
    private VBox badgeList;

    @FXML
    private VBox emoteList;

    public void initialize() {
        Properties loadProps = new Properties();
        try {
            loadProps.loadFromXML(new FileInputStream("settingsScalling.xml"));
            List<String> list = new ArrayList<>();
            String[] xml = loadProps.toString().replace("{","")
                    .replace("}","")
                    .replace(" ","")
                    .split(",");

            Collections.addAll(list, xml);
            for (String s :list ) {
                if(!s.isEmpty()) {
                    if (String.valueOf(s.charAt(0)).equals("B"))
                        add(badgeList, s);
                    else
                        add(emoteList, s);
                }
            }
        } catch (FileNotFoundException e){
            //file  file does not exist
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void add(VBox vBox, String s){
        TextField textField = new TextField();
        textField.setPrefWidth(92);
        textField.promptTextProperty().set("Width x Height");
        textField.setText(s.substring(3));
        Button button = new Button();

        button.setOnAction(Event -> removeElementFromVBox(button));
        button.setPrefWidth(22);
        button.setText("X");

        ToolBar toolBar = new ToolBar();
        toolBar.getItems().add(textField);
        toolBar.getItems().add(button);
        if(vBox.getChildren().size() < 7 )
            vBox.getChildren().add(toolBar);
    }

    @FXML
    void addEmote(ActionEvent event) {
        TextField textField = new TextField();
        textField.setPrefWidth(92);
        textField.promptTextProperty().set("Width x Height");

        Button button = new Button();
        button.setOnAction(Event -> removeElementFromVBox(button));
        button.setPrefWidth(22);
        button.setText("X");

        ToolBar toolBar = new ToolBar();
        toolBar.getItems().add(textField);
        toolBar.getItems().add(button);
        if(((Button) event.getSource()).getId().equals("addEmote")) {
            if(emoteList.getChildren().size() < 7 )
                emoteList.getChildren().add(toolBar);
        }
        else {
            if(badgeList.getChildren().size() < 7 )
                badgeList.getChildren().add(toolBar);
        }
    }

    @FXML
    void save(ActionEvent event) throws IOException {

        File myObj = new File("settingsScalling.xml");
        myObj.delete();
        Properties saveProps = new Properties();
        int i = 0;
        for (Node item: emoteList.getChildren()) {
            saveProps.setProperty("E" + i, ((TextField) ((ToolBar)item).getItems().get(0)).getText());
            i++;
        }
        i = 0;
        for (Node item: badgeList.getChildren()) {
            saveProps.setProperty("B" + i, ((TextField) ((ToolBar)item).getItems().get(0)).getText());
            i++;
        }

        saveProps.storeToXML(new FileOutputStream("settingsScalling.xml"), "");
    }

    private void removeElementFromVBox(Button button){
        int i = 0;
        for (Node toolBar:emoteList.getChildren()) {
            if(((ToolBar) toolBar).getItems().get(1) == button) {
                emoteList.getChildren().remove(i, i + 1);
                return;
            }
            i++;
        }
        i = 0;
        for (Node toolBar:badgeList.getChildren()) {
            if(((ToolBar) toolBar).getItems().get(1) == button) {
                badgeList.getChildren().remove(i, i + 1);
                return;
            }
            i++;
        }
    }
}
