package com.mycompany.jfx;

import javafx.scene.image.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;

public class PrimaryController {
    
    @FXML
    private ImageView foxImageView;

    @FXML
    private void switchToSecondary() throws IOException {
        
        String respostaAPI = chamarAPIRandomFox();

        // Exiba a resposta da API em um Alert
        exibirAlerta("Resposta da API", respostaAPI);
    }
    
     private String chamarAPIRandomFox() {
          String url = "https://randomfox.ca/floof";

        try {
            URL apiUrl = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Lê a resposta da API
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                
                // Parse da resposta JSON para obter a URL da imagem
                String imageUrl = response.toString().split("\"image\":\"")[1].split("\"")[0];
                
                String imgFox = imageUrl.replace("\\", "");
                
                System.out.println("Resposta: " + imgFox);
                Image image = new Image(imgFox);
                foxImageView.setImage(image);
            } else {
                System.out.println("Falha na requisição. Código de resposta: " + responseCode);
            }

            // Fecha a conexão
            connection.disconnect();   
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return "Consulta Realizada com Sucesso!";
     }
     

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
