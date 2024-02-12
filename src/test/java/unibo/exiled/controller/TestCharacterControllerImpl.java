package unibo.exiled.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unibo.exiled.model.character.CharacterModel;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Tests the character controller implementation.
 */
class TestCharacterControllerImpl {

    private CharacterControllerImpl controller;

    @BeforeEach
    void initialize() {
        final CharacterModel model = mock(CharacterModel.class);
        controller = new CharacterControllerImpl(model);
    }

    @Test
    void testGetImagePathOfCharacter() {
        final String folderPath = "path/to/folder";
        final String name = "CharacterName";
        final List<String> expected = List.of(
                folderPath,
                "/" + name.toLowerCase(Locale.ROOT) + "_up",
                "/" + name.toLowerCase(Locale.ROOT) + "_down",
                "/" + name.toLowerCase(Locale.ROOT) + "_left",
                "/" + name.toLowerCase(Locale.ROOT) + "_right");
        assertEquals(expected, controller.getImagePathOfCharacter(folderPath, name));
    }
}
