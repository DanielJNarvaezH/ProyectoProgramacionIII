/**
 * Sample Skeleton for 'comentarios.fxml' Controller Class
 */
package co.uniquindio.piii.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;


import co.uniquindio.piii.model.Comentario;
import co.uniquindio.piii.model.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class ComentarioController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="agregarComentarioButton"
    private Button agregarComentarioButton; // Value injected by FXMLLoader

    @FXML // fx:id="autorTextField"
    private TextField autorTextField; // Value injected by FXMLLoader

    @FXML // fx:id="comentarioTextField"
    private TextField comentarioTextField; // Value injected by FXMLLoader

    @FXML // fx:id="fechaDatePicker"
    private DatePicker fechaDatePicker; // Value injected by FXMLLoader

    @FXML // fx:id="listaComentariosLabel"
    private Label listaComentariosLabel; // Value injected by FXMLLoader

    @FXML // fx:id="mostrarListaComentariosButton"
    private Button mostrarListaComentariosButton; // Value injected by FXMLLoader

    @FXML
    void enviarComentario(ActionEvent event) {

        LocalDate fecha = fechaDatePicker.getValue();
        String autor = autorTextField.getText();
        String textoComentario = comentarioTextField.getText();
        Comentario comentario = new Comentario(autor, textoComentario, fecha);
        Producto producto = new Producto("", "", null, 0, null);
        producto.agregarComentario(comentario);
        
    }

    @FXML
    void mostrarListaComentarios(ActionEvent event) {

        Producto producto1 = new Producto("", "", null, 0, null);
        ArrayList<Comentario> listaComentarios = producto1.getComentarios();
        StringBuilder sb = new StringBuilder();  
            for (Comentario comentario : listaComentarios) {  
                sb.append(comentario).append("\n"); // Append each item and a newline  
            }  
            listaComentariosLabel.setText(sb.toString()); // Set the concatenated string to the label 
        

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert agregarComentarioButton != null : "fx:id=\"agregarComentarioButton\" was not injected: check your FXML file 'comentarios.fxml'.";
        assert autorTextField != null : "fx:id=\"autorTextField\" was not injected: check your FXML file 'comentarios.fxml'.";
        assert comentarioTextField != null : "fx:id=\"comentarioTextField\" was not injected: check your FXML file 'comentarios.fxml'.";
        assert fechaDatePicker != null : "fx:id=\"fechaDatePicker\" was not injected: check your FXML file 'comentarios.fxml'.";
        assert listaComentariosLabel != null : "fx:id=\"listaComentariosLabel\" was not injected: check your FXML file 'comentarios.fxml'.";
        assert mostrarListaComentariosButton != null : "fx:id=\"mostrarListaComentariosButton\" was not injected: check your FXML file 'comentarios.fxml'.";
    }

}

 

