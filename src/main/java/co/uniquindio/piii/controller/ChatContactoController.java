/**
 * Sample Skeleton for 'chatDeContactos.fxml' Controller Class
 */

 package co.uniquindio.piii.controller;

 import java.net.URL;
 import java.util.ResourceBundle;
 import javafx.event.ActionEvent;
 import javafx.fxml.FXML;
 import javafx.scene.control.TextField;
 
 public class ChatContactoController {
 
     @FXML // ResourceBundle that was given to the FXMLLoader
     private ResourceBundle resources;
 
     @FXML // URL location of the FXML file that was given to the FXMLLoader
     private URL location;
 
     @FXML // fx:id="BarraBUsquedaTextField"
     private TextField BarraBUsquedaTextField; // Value injected by FXMLLoader
 
     @FXML // fx:id="MensajeTextField"
     private TextField MensajeTextField; // Value injected by FXMLLoader
 
     @FXML
     void verListaProductosContacto(ActionEvent event) {
 
     }
 
     @FXML // This method is called by the FXMLLoader when initialization is complete
     void initialize() {
         assert BarraBUsquedaTextField != null : "fx:id=\"BarraBUsquedaTextField\" was not injected: check your FXML file 'chatDeContactos.fxml'.";
         assert MensajeTextField != null : "fx:id=\"MensajeTextField\" was not injected: check your FXML file 'chatDeContactos.fxml'.";
 
     }
 
 }
 