/**
 * Sample Skeleton for 'comentarios.fxml' Controller Class
 */

 package co.uniquindio.piii.controller;

 import java.net.URL;
 import java.util.ResourceBundle;
 import javafx.event.ActionEvent;
 import javafx.fxml.FXML;
 import javafx.scene.control.TextField;
 
 public class ComentarioController {
 
     @FXML // ResourceBundle that was given to the FXMLLoader
     private ResourceBundle resources;
 
     @FXML // URL location of the FXML file that was given to the FXMLLoader
     private URL location;
 
     @FXML // fx:id="ComentarioTextField"
     private TextField ComentarioTextField; // Value injected by FXMLLoader
 
     @FXML
     void enviarComentario(ActionEvent event) {
 
     }
 
     @FXML // This method is called by the FXMLLoader when initialization is complete
     void initialize() {
         assert ComentarioTextField != null : "fx:id=\"ComentarioTextField\" was not injected: check your FXML file 'comentarios.fxml'.";
 
     }
 
 }
 