
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
        Player player = new Player("Michel", 99, city, new HumanController());
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

