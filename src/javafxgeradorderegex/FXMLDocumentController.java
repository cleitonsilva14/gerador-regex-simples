/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package javafxgeradorderegex;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Text;

/**
 *
 * @author Cleiton
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private MenuBar menuAjuda;
    @FXML
    private MenuItem menuBtnClose;
    @FXML
    private Button btnApply;
    @FXML
    private Button btnCopy;
    @FXML
    private Button btnClean;
    @FXML
    private TextField txtInputNumSerie;

    Clipboard clipboard = Clipboard.getSystemClipboard();
    ClipboardContent clipboardContent = new ClipboardContent();
    ArrayList<String> pattern = new ArrayList<>();
    StringBuilder regexString = new StringBuilder();
    String numSerie = "";

    @FXML
    private TextArea txtResultRegex;
    @FXML
    private Text labelStatus;
    @FXML
    private MenuItem menuHelp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void actionApply(ActionEvent event) {

        processInputNumSerie();
        convertToRegex();

        //System.out.println(txtInputNumSerie.getText());
        //System.out.println("actionApply");
    }

    private void convertToRegex() {
      
        this.regexString.setLength(0);
        
        for (int counter = 0; counter < numSerie.length(); counter++) {
            String numSerieChar = Character.toString(numSerie.charAt(counter));
            if (numSerieChar.matches("\\d")) {
                this.pattern.add("[0-9]");
            } else if (numSerieChar.matches("\\w")) {
                this.pattern.add("[A-Z]");
            } else {
                this.pattern.add("?");
            }
        }
        System.out.println(pattern.toString());
        for (String p : pattern) {
            this.regexString.append(p);
        }
        this.txtResultRegex.setText(regexString.toString());
        actionSetStatus();
        this.pattern.clear();
    }

    private void processInputNumSerie() {
        this.numSerie = this.txtInputNumSerie.getText().replaceAll("(\\s|\\W)", "").toUpperCase();
        this.txtInputNumSerie.setText(this.numSerie);
    }

    @FXML
    private void actionCopyRegexToClipboard(ActionEvent event) {

        clipboardContent.putString(this.txtResultRegex.getText());
        clipboard.setContent(clipboardContent);
        System.out.println(clipboardContent);
        this.labelStatus.setText("Copiado para área de transferência!");

        //System.out.println("actionCopyRegexToClipboard");
    }

    private void actionSetStatus() {
        this.labelStatus.setText("Status: " + txtResultRegex.getText());
    }

    @FXML
    private void actionClean(ActionEvent event) {

        this.txtInputNumSerie.setText("");
        this.clipboardContent.putString("");
        this.txtResultRegex.setText("");
        this.labelStatus.setText("Limpo!");
    }

}
