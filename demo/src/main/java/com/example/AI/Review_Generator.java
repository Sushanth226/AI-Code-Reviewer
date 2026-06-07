package com.example.AI;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import io.github.cdimascio.dotenv.Dotenv;
// import io.github.*;
import com.example.model.ReviewResponse;
public class Review_Generator {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String Groq_API_KEY=dotenv.get("GROQ_API_KEY");
    public static void reviewCode(ReviewResponse input) {
        try {

            String prompt = """
                    Review the following Java code.

                    Provide:
                    1. Bugs if any
                    2. Code quality issues
                    3. Performance improvements
                    4. Best practices
                    5. Overall rating out of 10

                    Code:
                    """ + input.returnInput();

            String jsonBody = """
            {
              "model": "llama-3.3-70b-versatile",
              "messages": [
                {
                  "role": "user",
                  "content": "%s"
                }
              ]
            }
            """.formatted(prompt.replace("\"", "\\\"").replace("\n", "\\n"));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.groq.com/openai/v1/chat/completions"))
                    .header("Authorization", "Bearer " + Groq_API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpClient client = HttpClient.newHttpClient();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            input.addOutputResponse(response.body());

        } catch (Exception e) {
         System.out.println("Error: " + e.getMessage());
        }
    }
}
