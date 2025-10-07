import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) {
        String serverUrl = "http://127.0.0.1:5000/message";
        String textToSend = "Hello Flask from Java!";

        try {
            // Create connection
            URL url = new URL(serverUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Configure request
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            // JSON body
            String jsonInput = String.format("{\"text\": \"%s\"}", textToSend);

            // Send JSON
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Read response
            int code = conn.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        response.append(line.trim());
                    }
                    System.out.println("Server replied: " + response);
                }
            } else {
                System.out.println("Error: HTTP " + code);
            }

            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
