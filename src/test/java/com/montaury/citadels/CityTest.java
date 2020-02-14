package com.montaury.citadels;

import com.montaury.citadels.district.Card;
import com.montaury.citadels.player.HumanController;
import com.montaury.citadels.player.Player;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;


public class CityTest {

    Board board;
    Set<Card> hand;

    @Before
    public void setUp() {
        board = new Board();
        hand = HashSet.empty();
    }

    @Test
    public void test_score_plus_four_when_first_complete_city(){
        City city = new City(board);
        Possession possession = new Possession(5,null);

        city.buildDistrict(Card.MANOR_5);
        city.buildDistrict(Card.WATCHTOWER_2);
        city.buildDistrict(Card.TAVERN_5);
        city.buildDistrict(Card.TEMPLE_1);
        city.buildDistrict(Card.CHURCH_2);
        city.buildDistrict(Card.CASTLE_2);
        city.buildDistrict(Card.PRISON_2);

        if (board.isFirst(city))
            assertThat(city.score(possession)).isEqualTo(18);
    }

    @Test
    public void test_score_plus_two_when_other_complete_city(){
        City city = new City(board);
        Possession possession = new Possession(5,null);

        city.buildDistrict(Card.MANOR_5);
        city.buildDistrict(Card.WATCHTOWER_2);
        city.buildDistrict(Card.TAVERN_5);
        city.buildDistrict(Card.TEMPLE_1);
        city.buildDistrict(Card.CHURCH_2);
        city.buildDistrict(Card.CASTLE_2);
        city.buildDistrict(Card.PRISON_2);

        if (!board.isFirst(city) && city.isComplete())
            assertThat(city.score(possession)).isEqualTo(16);
    }

    @Test
    public void test_bonus_score_merveilles(){
        City city = new City(board);
        Player player = new Player("Antoine", 21, city, new HumanController());
        player.add(2);

        CardPile pioche = new CardPile(Card.all().toList().shuffle());

        player.add(pioche.draw(2));

        city.buildDistrict(Card.DRAGON_GATE);
        city.buildDistrict(Card.UNIVERSITY);
        city.buildDistrict(Card.TREASURY);
        city.buildDistrict(Card.MAP_ROOM);

        assertThat(player.score()).isEqualTo(34);
    }
}
