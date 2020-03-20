package com.montaury.citadels.round;

import com.montaury.citadels.character.Character;
import com.montaury.citadels.player.Player;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import io.vavr.control.Option;

public class Group {

    public Group(Player player, Character character) {
        this.player = player;
        this.character = character;
    }

    public Player player() {
        return player;
    }

    public Character character() {
        return character;
    }

    public boolean isNot(Character character) {
        return this.character != character;
    }

    public void murder() {
        this.murdered = true;
    }

    public boolean isMurdered() {
        return murdered;
    }

    public void stolenBy(Player player) {
        stolenBy = Option.of(player);
    }

    public Option<Player> thief() {
        return stolenBy;
    }

    public final Player player;
    public final Character character;
    private boolean murdered;
    private Option<Player> stolenBy = Option.none();
    public static final Set<String> OPTIONAL_ACTIONS = HashSet.of("Build district", "End round");
}
