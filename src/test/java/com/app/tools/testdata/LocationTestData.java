package com.app.tools.testdata;

import com.app.tools.TestMatcher;
import com.app.tools.model.Location;

import java.util.List;

public class LocationTestData {

    public static TestMatcher<Location> LOCATION_MATCHER = TestMatcher.usingEqualsComparator(Location.class);

    public static final int NOT_FOUND = 30;
    public static final int LOCATION1_ID = 1;

    public static final Location location1 = new Location(LOCATION1_ID, "БИХ 101");
    public static final Location location2 = new Location(LOCATION1_ID + 1, "БИХ 102");
    public static final Location location3 = new Location(LOCATION1_ID + 2, "БИХ 103");
    public static final Location location4 = new Location(LOCATION1_ID + 3, "БИХ 104");
    public static final Location location5 = new Location(LOCATION1_ID + 4, "БИХ 104(18)");
    public static final Location location6 = new Location(LOCATION1_ID + 5, "БИХ 104(24)");
    public static final Location location7 = new Location(LOCATION1_ID + 6, "БИХ 106");
    public static final Location location8 = new Location(LOCATION1_ID + 7, "БИХ 107");
    public static final Location location9 = new Location(LOCATION1_ID + 8, "БИХ 108");
    public static final Location location10 = new Location(LOCATION1_ID + 9, "БИХ 110");
    public static final Location location11 = new Location(LOCATION1_ID + 10, "БИХ 111");
    public static final Location location12 = new Location(LOCATION1_ID + 11, "БИХ 114");
    public static final Location location13 = new Location(LOCATION1_ID + 12, "БИХ 116");
    public static final Location location14 = new Location(LOCATION1_ID + 13, "БИХ 119");
    public static final Location location15 = new Location(LOCATION1_ID + 14, "БИХ 126");
    public static final Location location16 = new Location(LOCATION1_ID + 15, "БИХ 220");
    public static final Location location17 = new Location(LOCATION1_ID + 16, "БИХ 221");
    public static final Location location18 = new Location(LOCATION1_ID + 17, "БИХ 222");
    public static final Location location19 = new Location(LOCATION1_ID + 18, "БИХ 223");
    public static final Location location20 = new Location(LOCATION1_ID + 19, "БИХ ОГС");
    public static final Location location21 = new Location(LOCATION1_ID + 20, "БИХ ОТК");
    public static final Location location22 = new Location(LOCATION1_ID + 21, "БИХ СГП");
    public static final Location location23 = new Location(LOCATION1_ID + 22, "БИХ ТЦ");
    public static final Location location24 = new Location(LOCATION1_ID + 23, "БИХ УЗП");
    public static final Location location25 = new Location(LOCATION1_ID + 24, "БИХ ЦЗЛ");
    public static final Location location26 = new Location(LOCATION1_ID + 25, "БИХ ЭЦ");
    public static final Location location27 = new Location(LOCATION1_ID + 26, "ЦИС");

    public static final List<Location> locations = List.of(location1, location2, location3, location4, location5, location6,
            location7, location8, location9, location10, location11, location12, location13, location14, location15,
            location16, location17, location18, location19, location20, location21, location22, location23, location24,
            location25, location26, location27);

    public static Location getNew() {
        return new Location(null, "Новое местонахождение");
    }

    public static Location getUpdated() {
        return new Location(LOCATION1_ID, "Обновленный инструмент");
    }
}
