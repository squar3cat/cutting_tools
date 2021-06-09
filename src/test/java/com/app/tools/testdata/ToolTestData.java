package com.app.tools.testdata;

import com.app.tools.TestMatcher;
import com.app.tools.model.Location;
import com.app.tools.model.Tool;
import com.app.tools.dto.ToolTo;
import com.app.tools.model.Type;

import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.time.LocalDate.of;
import static com.app.tools.model.AbstractBaseEntity.START_SEQ;

public class ToolTestData {

    public static final TestMatcher<Tool> TOOL_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Tool.class,"user");
    public static TestMatcher<ToolTo> TOOL_ADD_MATCHER = TestMatcher.usingEqualsComparator(ToolTo.class);

    public static final int NOT_FOUND = 10;
    public static final int TOOL1_ID = START_SEQ + 18;
    public static final int ADMIN_TOOL_ID = START_SEQ + 25;

    public static final Tool tool1 = new Tool(TOOL1_ID, of(2020, Month.NOVEMBER, 15), "CNMG 190616-PM 4035", 5, "Sandvik", new Location(14, "БИХ 119"), 5, new Type(100015, "Пластинки для проходных резцов", 100014, 2, true));
    public static final Tool tool2 = new Tool(TOOL1_ID + 1, of(2020, Month.NOVEMBER, 18), "CNMM 120408-PR 4415", 50, "Sandvik", new Location(4, "БИХ 104"), 10, new Type(100015, "Пластинки для проходных резцов", 100014, 2, true));
    public static final Tool tool3 = new Tool(TOOL1_ID + 2, of(2020, Month.DECEMBER, 28), "R390-040Q16LW-11L", 1, "Sandvik", new Location(3, "БИХ 103"), 1, new Type(100011, "Корпуса фрез", 100010, 3, true));
    public static final Tool tool4 = new Tool(TOOL1_ID + 3, of(2020, Month.DECEMBER, 30), "EC-H4M 06-12C06CF-E57", 1, "Iscar", new Location(16, "БИХ 220"), 2, new Type(100009, "Фрезы монолитные", 100006, 3, true));
    public static final Tool tool5 = new Tool(TOOL1_ID + 4, of(2021, Month.JANUARY, 5), "137860 1-8", 25, "Hoffmann", new Location(3, "БИХ 103"), 5, new Type(100007, "Метчики", 100006, 3, true));
    public static final Tool tool6 = new Tool(TOOL1_ID + 5, of(2021, Month.JANUARY, 7), "215126 50/3", 3, "Hoffmann", new Location(4, "БИХ 104"), 1, new Type(100011, "Корпуса фрез", 100010, 3, true));
    public static final Tool tool7 = new Tool(TOOL1_ID + 6, of(2021, Month.JANUARY, 18), "A6181AML-2", 10, "Walter", new Location(4, "БИХ 104"), 5, new Type(100008, "Сверла спиральные",  100006, 3, true));
    public static final Tool adminTool1 = new Tool(ADMIN_TOOL_ID, of(2021, Month.JANUARY, 18), "SF25DRA180M12", 2, "Kyocera", new Location(4, "БИХ 104"), 1, new Type(100008, "Сверла спиральные",  100006, 3, true));
    public static final Tool adminTool2 = new Tool(ADMIN_TOOL_ID + 1, of(2021, Month.JANUARY, 21), "MEV 1250W100064T", 3, "Kyocera", new Location(3, "БИХ 103"), 1, new Type(100011, "Корпуса фрез", 100010, 3, true));

    public static final List<Tool> tools = List.of(adminTool2, adminTool1, tool7, tool6, tool5, tool4, tool3, tool2, tool1);

    public static Tool getNew() {
        return new Tool(null, of(2020, Month.FEBRUARY, 1), "Созданный инструмент", 300, "Собственного изготовления", new Location(5, "БИХ 104(18)"), 1, new Type(100012, "Корпуса сверл", 100010, 3, true));
    }

    public static Tool getUpdated() {
        return new Tool(TOOL1_ID, tool1.getRegistrationDate().plus(2, ChronoUnit.DAYS), "Обновленный инструмент", 200, "Собственного изготовления", new Location(5, "БИХ 104(18)"), 2, new Type(100009, "Фрезы монолитные", 100006, 3, true));
    }
}
