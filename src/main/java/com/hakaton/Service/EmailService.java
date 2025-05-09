package com.hakaton.Service;

import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

@Service
public class EmailService {

    private final String apiKey = "bf835ed28b46a009cf3fe854442c9fd7";
    private final String apiSecret = "c35812696a790c248d2843b2d64e1f47";

    public void enviarCredenciales(String destinatario, String dni, String password) {
        try {
            var client = HttpClient.newHttpClient();

            var body = """
            {
              "Messages":[
                {
                  "From": {
                    "Email": "mherreracolomer@gmail.com",
                    "Name": "Sistema de Votación"
                  },
                  "To": [
                    {
                      "Email": "%s"
                    }
                  ],
                  "Subject": "Tus credenciales de acceso",
                  "TextPart": "Hola, tus credenciales para votar son:\\n\\nDNI: %s\\nContraseña: %s"
                }
              ]
            }
            """.formatted(destinatario, dni, password);

            var request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.mailjet.com/v3.1/send"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Basic " + Base64.getEncoder()
                            .encodeToString((apiKey + ":" + apiSecret).getBytes()))
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Respuesta Mailjet: " + response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
