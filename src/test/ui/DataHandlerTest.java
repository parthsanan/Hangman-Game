package ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.*;
import persistance.*;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException; 
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataHandlerTest {

    private DataHandler dataHandler;
    private GamesManager gamesManager;

    @BeforeEach
    public void setUp() {
        dataHandler = new DataHandler();
        gamesManager = new GamesManager();
    }

    @Test
    public void testLoadGames() {
        // Test loading games from a valid JSON file
        String filePath = "./src/test/ui/testgames.json";
        String jsonContent = "[{\"result\":\"Win\",\"mode\":\"Classic\",\"difficulty\":\"Novice\",\"secretWord\":\"test\",\"guessesLeft\":5,\"score\":50}]";
        try {
            Files.write(Paths.get(filePath), jsonContent.getBytes());
            dataHandler.setFilePath(filePath);
            dataHandler.loadGames(gamesManager);
            assertEquals(1, gamesManager.getLoadedGames().size());
            // Additional assertions based on the expected content of the testgames.json file
        } catch (IOException e) {
            fail("IOException should not be thrown for a valid JSON file.");
        } finally {
            try {
                Files.deleteIfExists(Paths.get(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testLoadGamesWithInvalidFile() {
        // Test loading games from a non-existent file
        String filePath = "./src/test/ui/non_existent_file.json";
        try {
            dataHandler.setFilePath(filePath);
            dataHandler.loadGames(gamesManager);
            assertEquals(0, gamesManager.getLoadedGames().size());
        } catch (Exception e) {
            fail("Exception should not be thrown for a non-existent file.");
        }
    }

    @Test
    public void testLoadGamesWithMalformedJSON() throws IOException {
        // Test loading games from a malformed JSON file
        try{ 
            String filePath = "./src/test/ui/malformed_json.json";
            String jsonContent = "{invalid json}";
            Files.write(Paths.get(filePath), jsonContent.getBytes());
            dataHandler.setFilePath(filePath);
            dataHandler.loadGames(gamesManager);
        } catch (Exception e) {
            //Succeed
        }
    }
}
