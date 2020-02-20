package com.montaury.citadels;


import com.montaury.citadels.district.Card;

import io.vavr.collection.HashSet;
import io.vavr.collection.List;
import io.vavr.collection.Set;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;


public class CityTest {

    Board board;
    Set<Card> hand;
    City city;

    @Before
    public void setUp() {
        board = new Board();

        city = new City(board);

    }

    @Test
    public void when_No_Buildings_Test_Score_Should_Be_0() {
        Possession possession = new Possession(5, null);
        int score = city.score(possession);
        assertThat(score).isEqualTo(0);
    }


    @Test
    public void score_Total_When_5_Color_Buildings_Should_Be_11() {

        Possession possession = new Possession(5, null);

        city.buildDistrict(Card.MANOR_1);
        city.buildDistrict(Card.WATCHTOWER_1);
        city.buildDistrict(Card.TAVERN_1);
        city.buildDistrict(Card.TEMPLE_1);
        city.buildDistrict(Card.HAUNTED_CITY);
        int score = city.score(possession);
        assertThat(score).isEqualTo(11);

    }


    @Test
    public void score_Total_With_DragonGate_Should_Be_8() {

        Possession possession = new Possession(5, null);
        city.buildDistrict(Card.DRAGON_GATE);

        int score = city.score(possession);
        assertThat(score).isEqualTo(8);
    }

    @Test
    public void score_Total_With_University_Should_Be_8() {

        Possession possession = new Possession(5, null);
        city.buildDistrict(Card.UNIVERSITY);

        int score = city.score(possession);
        assertThat(score).isEqualTo(8);

    }

    @Test
    public void score_Total_With_Threasury_Should_Be_10() {

        Possession possession = new Possession(5, null);

        city.buildDistrict(Card.TREASURY);

        int score = city.score(possession);
        assertThat(score).isEqualTo(10);

    }

    @Test
    public void score_Total_With_Map_Room_Should_Be_6() {;

        city.buildDistrict(Card.MAP_ROOM);



        Possession possession = new Possession(0, HashSet.of(Card.MANOR_1,Card.MANOR_2, Card.MANOR_3));
        int score = city.score(possession);
        assertThat(score).isEqualTo(8);
    }


}
