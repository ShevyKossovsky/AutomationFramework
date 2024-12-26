package browser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.nio.file.Files;
import java.nio.file.Paths;


public class JsonBrowserProvider implements BrowserProvider {
    private final String filePath;

    public JsonBrowserProvider(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getBrowserName() {
        try {
            // Read the content of the JSON file
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            //Analyze the content of the JSON file
            JsonObject json = JsonParser.parseString(content).getAsJsonObject();

            //Get the value of the property
            return json.get("browser").getAsString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read browser from JSON file", e);
        }
    }
}
