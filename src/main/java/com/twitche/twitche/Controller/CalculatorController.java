package com.twitche.twitche.Controller;


import com.twitche.twitche.Model.SettingsDefaultOptions;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Window;

import java.io.*;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class CalculatorController {


    @FXML
    private TextField name;

    @FXML
    private TextField buysAnimated;

    @FXML
    private TextField buysBadges;

    @FXML
    private TextField buysEmotes;

    @FXML
    private Text buysSum;

    @FXML
    private GridPane grid;

    public int getBuysEmotes(){
        if(buysEmotes.getText().isBlank()) return 0;
        return Integer.valueOf(buysEmotes.getText().trim());
    }
    public int getBuysBadges(){
        if(buysBadges.getText().isBlank()) return 0;
        return Integer.valueOf(buysBadges.getText().trim());
    }

    public int getBuysAnimated(){
        if(buysAnimated.getText().isBlank()) return 0;
        return Integer.valueOf(buysAnimated.getText().trim());
    }

    public void initialize(){
        onlyNumber(buysAnimated);
        onlyNumber(buysEmotes);
        onlyNumber(buysBadges);
        readDate();
    }

    public void onlyNumber(TextField textField){
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    private void readDate(){
        File file = new File("settings.xml");
        if(file.exists()) {
            Properties loadProps = new Properties();
            try {
                loadProps.loadFromXML(new FileInputStream(file));
                SettingsDefaultOptions.setCostEmotes(Integer.parseInt(loadProps.getProperty("emotePrice")));
                SettingsDefaultOptions.setCostBadge(Integer.parseInt(loadProps.getProperty("badgePrice")));
                SettingsDefaultOptions.setCostAnimated(Integer.parseInt(loadProps.getProperty("animated")));
                SettingsDefaultOptions.setEmotesPer(Integer.parseInt(loadProps.getProperty("emotePer")));
                SettingsDefaultOptions.setCostEmotesProm(Integer.parseInt(loadProps.getProperty("emotePricePer")));
                SettingsDefaultOptions.setBadgesPer(Integer.parseInt(loadProps.getProperty("BadgePer")));
                SettingsDefaultOptions.setCostBadgeProm(Integer.parseInt(loadProps.getProperty("BadgePricePer")));
                SettingsDefaultOptions.setHowMuchToPromotion(Integer.parseInt(loadProps.getProperty("emorePerSpecial")));
                SettingsDefaultOptions.setPromo(Integer.parseInt(loadProps.getProperty("emorePerSpecial")));
                int i = 5;
                for (int z = 0; z < 7; z++) {
                    if(loadProps.getProperty("tab" + z) == null) break;
                    
                    String[] tabs = loadProps.getProperty("tab" + z).split(","); 
                    ((Text)grid.getChildren().get(6)).setText(tabs[0]); 
                    grid.getChildren().remove(3);
                    //grid.getChildren().remove(6);                    
                    grid.add(new TextField(), 1, 4);         
                    grid.addRow(6);
                    grid.add(new Text("Sum"), 0, 7);        
                    grid.add(buysSum, 1, 7);
                }  
            } catch (FileNotFoundException e){
                //file  file does not exist
            } catch (InvalidPropertiesFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    double sumAll = 0;
    @FXML
    void saveDate(ActionEvent event) {
        try(FileWriter fileWriter = new FileWriter("myfile.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter))
        {
            /*Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            printWriter.println(formatter.format(date) + " " + name.getText());*/
            printWriter.println(name.getText());
            printWriter.println("\t Emotes \t\t\t" + getBuysEmotes() + "\t" + sumCost(buysEmotes, SettingsDefaultOptions.getCostEmotes(), SettingsDefaultOptions.getCostEmotesProm()));
            printWriter.println("\t Badges \t\t\t" + getBuysBadges() + "\t" + sumCost(buysBadges, SettingsDefaultOptions.getCostBadge(), SettingsDefaultOptions.getCostBadgeProm()));
            printWriter.println("\t Animated Emotes \t\t" + getBuysAnimated() + "\t" + sumCost(buysAnimated, SettingsDefaultOptions.getCostAnimated(), SettingsDefaultOptions.getCostAnimated()));
            printWriter.println("\t Sum \t\t\t\t\t" + sumAll);
        } catch (IOException e) {

        }
    }

    @FXML
    void writeVaule(KeyEvent event) {
        calculatePrice();
    }

    private void calculatePrice(){
        int sumEmote = sumCost(buysEmotes, SettingsDefaultOptions.getCostEmotes(), SettingsDefaultOptions.getCostEmotesProm());
        int sumBadge = sumCost(buysBadges, SettingsDefaultOptions.getCostBadge(), SettingsDefaultOptions.getCostBadgeProm());
        int sumAnimated = sumCost(buysAnimated, SettingsDefaultOptions.getCostAnimated(), SettingsDefaultOptions.getCostAnimated());
        sumAll = promotionFoEmotes(sumEmote+sumBadge+sumAnimated, getBuysEmotes() + getBuysAnimated());
        buysSum.setText(String.valueOf(sumAll));
    }

    public int sumCost(TextField textField, int cost, int costProm){
        int sum = 0;
        if(textField.getText().isBlank()) return sum;
        int value = Integer.valueOf(textField.getText().trim());
        int prom = textField.getId().equals("buysEmotes") ? SettingsDefaultOptions.getEmotesPer() : SettingsDefaultOptions.getBadgesPer();
        for(int i = value; i > 0;) {
            if (i % prom == 0 && !textField.getId().equals("buysAnimated"))
            {
                sum += costProm;
                i -= prom;
            } else {
                sum += cost;
                i--;
            }
        }
        return sum;
    }

    public double promotionFoEmotes(int sum, int sizeEmotes){
        if(sizeEmotes >= SettingsDefaultOptions.getHowMuchToPromotion())
            return sum - (sum * ((double) SettingsDefaultOptions.getPromo() / 100));
        return sum;
    }
}