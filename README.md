# AI Code Reviewer

## Project Overview

AI Code Reviewer is a lightweight Java console application that automatically reviews your Java code. It takes your code directly from the terminal and uses the powerful Groq API (specifically the `llama-3.3-70b-versatile` model) to provide instant feedback. It acts as an automated pair programmer, analyzing your code for potential bugs, quality issues, and performance bottlenecks, and scores your code out of 10.

## Key Features

- **Automated Code Analysis:** Identifies bugs, code quality issues, and suggests performance improvements.
- **Interactive Console Interface:** Paste multiple lines of code directly into the terminal.
- **Fast AI Responses:** Integrates with the Groq API for extremely low-latency code reviews.
- **Best Practices Suggestions:** Guides you to write cleaner and more idiomatic code.
- **Overall Rating:** Provides a clear 1 to 10 rating based on the provided code snippet.

## Tech Stack Used

- **Language:** Java 17
- **Build Tool:** Maven
- **External API:** Groq API (using the OpenAI-compatible completions endpoint)
- **Libraries:** 
  - `java.net.http.HttpClient` (Native Java HTTP client for API requests)
  - `dotenv-java` (For secure environment variable management)

## Project Architecture / Workflow

1. **Input:** The user runs the application and pastes Java code into the terminal. The input phase ends when the user types `END` on a new line.
2. **Data Storage:** The code is temporarily stored in a `ReviewResponse` data model object.
3. **API Integration:** The `Review_Generator` class takes the code, wraps it in a specific prompt format, and sends an HTTP POST request to the Groq API.
4. **Output:** The JSON response from the LLM is received, extracted, and printed directly to the console for the user to read.

## Folder Structure

```text
AI Code Reviewer/
└── demo/
    ├── .env                  # Stores secure environment variables (not tracked in git)
    ├── pom.xml               # Maven configuration and dependency management
    └── src/
        └── main/
            └── java/
                └── com/
                    └── example/
                        ├── Main.java                # Application entry point and I/O handler
                        ├── AI/
                        │   └── Review_Generator.java # Handles Groq API HTTP requests
                        └── model/
                            └── ReviewResponse.java   # Data structure for input code and output review
```

## Installation & Setup Instructions

### Prerequisites

- [Java Development Kit (JDK) 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or higher
- [Apache Maven](https://maven.apache.org/download.cgi) installed and added to your system PATH
- A valid [Groq API Key](https://console.groq.com/keys)

### Environment Variables

You need to set up your API key securely. In the `demo` directory, create a file named `.env` and add your Groq API key:

```env
GROQ_API_KEY=your_actual_api_key_here
```

### Dependency Installation & Running Locally

1. Open your terminal and navigate to the project directory:
   ```bash
   cd "C:\Users\surus\OneDrive\Desktop\AI Code Reviewer\demo"
   ```

2. Compile the project and download dependencies:
   ```bash
   mvn clean compile
   ```

3. Run the application:
   ```bash
   mvn exec:java -Dexec.mainClass="com.example.Main"
   ```
   *(Alternatively, you can open the project in your favorite IDE like IntelliJ IDEA or VS Code and run `Main.java` directly).*

## Usage Guide

1. Start the application. You will see the prompt: `Give me the code with last line As END`.
2. Paste the Java code you want to review into the terminal.
3. Once you have pasted all the code, press Enter, type `END` on a new, blank line, and press Enter again.
4. Wait a few seconds for the Groq API to process the request.
5. Read the detailed code review printed in your terminal!

## API Integration Details

This project uses the Groq API via its OpenAI-compatible completions endpoint. 
- **Endpoint URL:** `https://api.groq.com/openai/v1/chat/completions`
- **Model Used:** `llama-3.3-70b-versatile`
- **Authentication:** Bearer token (API key loaded from `.env`)
- **Method:** standard Java `HttpClient` is used to build and send the JSON payload.

## Screenshots

*(Placeholder: Add a screenshot of the terminal showing a code snippet being pasted and the AI response generated below it.)*
![Terminal Output Screenshot Placeholder](https://via.placeholder.com/800x400?text=Terminal+Output+Screenshot)

## Future Improvements

- **JSON Parsing:** Currently, the application prints the raw JSON response body from the API. Parsing the JSON to extract just the `"content"` field of the message would provide a much cleaner output.
- **File Input:** Allow the application to read Java files directly from the local file system instead of relying solely on manual terminal input.
- **Multiple Languages:** Update the prompt to dynamically support and review code in languages other than Java.
- **GUI / Web Interface:** Build a simple web frontend using Spring Boot or a simple desktop UI to make it more user-friendly than a terminal interface.

## Known Limitations

- **Raw Output:** The terminal currently displays the full, unparsed JSON string returned by the Groq API.
- **Manual Termination:** The user must explicitly remember to type `END` on a new line to signal the end of input, which can be unintuitive.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details (or add your own preferred license).
