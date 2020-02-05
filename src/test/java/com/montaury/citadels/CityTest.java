package com.montaury.citadels;

import com.montaury.citadels.district.Card;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;

import static org.junit.Assert.*;

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
    public void testScoreIsEquelZero() {
        Possession possession = new Possession(5, hand);

        City city = new City(board);

        assertEquals(0, city.score(possession));
    }


    @Test
    public void scoreTotalWhen5ColorBuildings() {

        Possession possession = new Possession(5, hand);

        City city = new City(board);

        city.buildDistrict(Card.MANOR_1);
        city.buildDistrict(Card.WATCHTOWER_1);
        city.buildDistrict(Card.TAVERN_1);
        city.buildDistrict(Card.TEMPLE_1);
        city.buildDistrict(Card.HAUNTED_CITY);


        assertEquals(11, city.score(possession));

    }


    @Test
    public void scoreTotalWithDragonGate() {

        Possession possession = new Possession(5, hand);
        City city = new City(board);
        city.buildDistrict(Card.DRAGON_GATE);

        assertEquals(8, city.score(possession));

    }

    @Test
    public void scoreTotalWithUniversity() {

        Possession possession = new Possession(5, hand);
        City city = new City(board);
        city.buildDistrict(Card.UNIVERSITY);

        assertEquals(8, city.score(possession));

    }

    @Test
    public void scoreTotalWithThreasury() {

        Possession possession = new Possession(5, hand);
        City city = new City(board);
        city.buildDistrict(Card.TREASURY);

        assertEquals(10, city.score(possession));

    }

    @Test
    public void scoreTotalWithMapRoom() {

        hand.add(Card.DRAGON_GATE);
        Possession possession = new Possession(5, hand);
        City city = new City(board);
        city.buildDistrict(Card.MAP_ROOM);


        assertEquals(6, city.score(possession));

    }


}
