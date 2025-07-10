package com.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

public class GeminiService {
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent";
    private static final String API_KEY = "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"; // Replace with your actual API key

    public String getSuggestedTask(String userInput) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            
            // Create the request body
            JsonObject requestBody = new JsonObject();
            JsonArray contents = new JsonArray();
            JsonObject content = new JsonObject();
            
            // Add text part
            content.addProperty("text", userInput);
            contents.add(content);
            requestBody.add("contents", contents);

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + "?key=" + API_KEY))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(requestBody)))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            // Parse the response
            JsonObject jsonResponse = new Gson().fromJson(response.body(), JsonObject.class);
            if (jsonResponse != null && jsonResponse.has("candidates")) {
                JsonArray candidates = jsonResponse.getAsJsonArray("candidates");
                if (candidates != null && candidates.size() > 0) {
                    JsonObject candidate = candidates.get(0).getAsJsonObject();
                    if (candidate.has("content") && 
                        candidate.getAsJsonObject("content").has("parts")) {
                        JsonArray parts = candidate.getAsJsonObject("content")
                                                 .getAsJsonArray("parts");
                        if (parts != null && parts.size() > 0) {
                            return parts.get(0).getAsJsonObject()
                                             .get("text").getAsString();
                        }
                    }
                }
            }
            
            return "I couldn't process that question. Please try again.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}