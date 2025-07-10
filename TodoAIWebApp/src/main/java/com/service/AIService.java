package com.service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class AIService {

    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/google/gemini-2.0-flash-exp:free:generateContent";
    private static final String API_KEY = "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";

    public String getSuggestedTask(String prompt) {
        try {
            URL url = new URL(GEMINI_API_URL + "?key=" + API_KEY);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonInput = "{ \"prompt\": { \"text\": \"" + prompt + "\" } }";
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInput.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            Scanner scanner = new Scanner(connection.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }

            scanner.close();

            // Extract response content
            String result = response.toString();
            int start = result.indexOf("text\":\"") + 7;
            int end = result.indexOf("\"", start);
            return result.substring(start, end);

        } catch (Exception e) {
            e.printStackTrace();
            return "Error generating task from Gemini.";
        }
    }
}
