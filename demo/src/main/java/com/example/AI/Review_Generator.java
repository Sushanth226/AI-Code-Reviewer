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

            input.addOutputResponse(parseContent(response.body()));

        } catch (Exception e) {
         System.out.println("Error: " + e.getMessage());
        }
    }

    private static String parseContent(String json) {
        if (json == null) return "";
        int contentIndex = json.indexOf("\"content\"");
        if (contentIndex == -1) {
            return json; // Fallback to raw json if "content" key is not found
        }
        int colonIndex = json.indexOf(":", contentIndex + 9);
        if (colonIndex == -1) return json;
        int quoteStartIndex = json.indexOf("\"", colonIndex);
        if (quoteStartIndex == -1) return json;

        StringBuilder sb = new StringBuilder();
        boolean escaped = false;
        for (int i = quoteStartIndex + 1; i < json.length(); i++) {
            char c = json.charAt(i);
            if (escaped) {
                switch (c) {
                    case 'n' -> sb.append('\n');
                    case 't' -> sb.append('\t');
                    case 'r' -> sb.append('\r');
                    case 'b' -> sb.append('\b');
                    case 'f' -> sb.append('\f');
                    case '"' -> sb.append('"');
                    case '\\' -> sb.append('\\');
                    case '/' -> sb.append('/');
                    case 'u' -> {
                        if (i + 4 < json.length()) {
                            try {
                                String hex = json.substring(i + 1, i + 5);
                                sb.append((char) Integer.parseInt(hex, 16));
                                i += 4;
                            } catch (NumberFormatException e) {
                                sb.append("\\u");
                            }
                        } else {
                            sb.append("\\u");
                        }
                    }
                    default -> sb.append(c);
                }
                escaped = false;
            } else if (c == '\\') {
                escaped = true;
            } else if (c == '"') {
                break;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
