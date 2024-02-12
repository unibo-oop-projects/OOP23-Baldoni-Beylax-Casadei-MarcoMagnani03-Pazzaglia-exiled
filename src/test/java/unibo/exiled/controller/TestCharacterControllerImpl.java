package unibo.exiled.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unibo.exiled.model.character.CharacterModel;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.utilities.ElementalType;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

/**
 * Tests the character controller implementation.
 */
class TestCharacterControllerImpl {

    private CharacterControllerImpl controller;
    private CharacterModel model;

    @BeforeEach
    void initialize() {
        this.model = mock(CharacterModel.class);
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

    @Test
    void testGetPlayerHealth() {
        final double health = 100.0;
        when(model.getPlayerAttributeOf(AttributeIdentifier.HEALTH)).thenReturn(health);
        assertEquals(health, controller.getPlayerHealth());
    }

    @Test
    void testGetPlayerHealthCap() {
        final double healthCap = 200.0;
        when(model.getPlayerAttributeOf(AttributeIdentifier.HEALTHCAP)).thenReturn(healthCap);
        assertEquals(healthCap, controller.getPlayerHealthCap());
    }

    @Test
    void testGetPlayerLevel() {
        final int level = 5;
        when(model.getPlayerLevel()).thenReturn(level);
        assertEquals(level, controller.getPlayerLevel());
    }

    @Test
    void testGetPlayerCurrentExperience() {
        final int experience = 50;
        when(model.getPlayerCurrentExperience()).thenReturn(experience);
        assertEquals(experience, controller.getPlayerCurrentExperience());
    }

    @Test
    void testAddPlayerExperience() {
        final int experience = 50;
        doNothing().when(model).addPlayerExperience(experience);
        controller.addPlayerExperience(experience);
        verify(model, times(1)).addPlayerExperience(experience);
    }

    @Test
    void testGetPlayerExperienceCap() {
        final int experienceCap = 100;
        when(model.getPlayerExperienceCap()).thenReturn(experienceCap);
        assertEquals(experienceCap, controller.getPlayerExperienceCap());
    }

    @Test
    void testGetPlayerClassName() {
        final ElementalType playerType = ElementalType.FIRE;
        when(model.getPlayerClass()).thenReturn(playerType);
        assertEquals(playerType.getName(), controller.getPlayerClassName());
    }

}
