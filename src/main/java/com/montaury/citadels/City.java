package com.montaury.citadels;

import com.montaury.citadels.district.Card;
import com.montaury.citadels.district.DestructibleDistrict;
import com.montaury.citadels.district.District;
import com.montaury.citadels.district.DistrictType;
import com.montaury.citadels.player.Player;
import io.vavr.collection.List;

import static com.montaury.citadels.district.District.*;
import static com.montaury.citadels.district.District.GREAT_WALL;
import static com.montaury.citadels.district.District.HAUNTED_CITY;

public class City {
    private static final int END_GAME_DISTRICT_NUMBER = 8;
    private final Board board;
    private List<Card> districtCards = List.empty();

    private static final int BONUS_IF_PLAYER_HAVE_UNIVERSITY_AND_DRAGON_GATE = 2;
    private static final int BONUS_IF_CITY_IS_COMPLETE = 2;
    private static final int BONNUS_IF_PLAYER_IS_FIRST = 2;
    private static final int BONUS_IF_PLAYER_GOT_ALL_COLOR = 3;

    public City(Board board) {
        this.board = board;
    }

    public void buildDistrict(Card card) {
        districtCards = districtCards.append(card);
        if (isComplete()) {
            board.mark(this);
        }
    }

    public boolean isComplete() {
        return districtCards.size() >= END_GAME_DISTRICT_NUMBER;
    }




    public int score(Possession possession) {
        int score = 0;
        for (int card = 0; card < districts().size(); card++) {
            score += districts().get(card).cost();
        }
        score += bonusForAllBuildedDistricts(possession);
        score += bonusIfPlayerWinWithOneBuildingOfEachColor();
        score += bonusIfplayerIsFirst(this);
        score += bonusIfCityIsComplete();
        return score;
    }


    private int bonusIfCityIsComplete() {
        int bonus = 0;
        if (isComplete()) {
            bonus = BONUS_IF_CITY_IS_COMPLETE;
        }
        return bonus;
    }


    private int bonusIfPlayerWinWithOneBuildingOfEachColor() {
        int bonus = 0;
        if(winsAllColorBonus()){
            bonus = BONUS_IF_PLAYER_GOT_ALL_COLOR;
        }
        return bonus;
    }

    private int bonusIfplayerIsFirst(City city) {
        int bonus = 0;
        if (board.isFirst(city)) {
            bonus = BONNUS_IF_PLAYER_IS_FIRST;
        }
        return bonus;
    }

    private int bonusForAllBuildedDistricts(Possession possession) {
        int score = 0;
        for (District district : districts()) {

            if (district == UNIVERSITY || district == DRAGON_GATE) {
                score += BONUS_IF_PLAYER_HAVE_UNIVERSITY_AND_DRAGON_GATE;
            }

            if (district == TREASURY) {
                score +=possession.gold;
            }
            if (district == MAP_ROOM) {
                score += possession.hand.size();
            }
        }
        return score;
    }



    private boolean winsAllColorBonus() {
        int districtTypes[] = new int[DistrictType.values().length];
        for (District district : districts()) {
            districtTypes[district.districtType().ordinal()]++;
        }
        if (districtTypes[DistrictType.MILITARY.ordinal()] > 0 && districtTypes[DistrictType.NOBLE.ordinal()] > 0 && districtTypes[DistrictType.RELIGIOUS.ordinal()] > 0 && districtTypes[DistrictType.SPECIAL.ordinal()] > 0 && districtTypes[DistrictType.TRADE.ordinal()] > 0)
            return true;

        if (has(HAUNTED_CITY)) {
            int zeros = 0;
            for (int i = 0; i < districtTypes.length; i++) {
                if (districtTypes[i] == 0) {
                    zeros++;
                }
            }
            if (zeros == 1 && districtTypes[DistrictType.SPECIAL.ordinal()] > 1) {
                return true;
            }
            else return false;
        } else return false;
    }







    public boolean has(District district) {
        return districts().contains(district);
    }

    public void destroyDistrict(Card card) {
        districtCards = districtCards.remove(card);
    }

    public List<DestructibleDistrict> districtsDestructibleBy(Player player) {
        return isComplete() ?
                List.empty() :
                districtCards
                        .filter(card -> card.district().isDestructible())
                        .filter(card -> player.canAfford(destructionCost(card)))
                        .map(card -> new DestructibleDistrict(card, destructionCost(card)));
    }

    private int destructionCost(Card card) {
        return card.district().cost() - (has(GREAT_WALL) && card.district() != GREAT_WALL ? 0 : (1));
    }

    public List<District> districts() {
        return districtCards.map(Card::district);
    }
}