package com.app.tools.testdata;

import com.app.tools.TestMatcher;
import com.app.tools.model.Type;

import java.util.List;

import static com.app.tools.model.AbstractBaseEntity.START_SEQ;

public class TypeTestData {

    public static TestMatcher<Type> TYPE_MATCHER = TestMatcher.usingEqualsComparator(Type.class);

    public static final int NOT_FOUND = 30;
    public static final int TYPE1_ID = START_SEQ + 3;

    public static final Type type1 = new Type(TYPE1_ID, "Режущий инструмент", 0, 0, false);
    public static final Type type2 = new Type(TYPE1_ID + 1, "Токарный", 100003, 1, false);
    public static final Type type3 = new Type(TYPE1_ID + 2, "Вращающийся", 100003, 1, false);
    public static final Type type4 = new Type(TYPE1_ID + 3, "Цельный", 100005, 2, false);
    public static final Type type5 = new Type(TYPE1_ID + 4, "Метчики", 100006, 3, true);
    public static final Type type6 = new Type(TYPE1_ID + 5, "Сверла спиральные", 100006, 3, true);
    public static final Type type7 = new Type(TYPE1_ID + 6, "Фрезы монолитные", 100006, 3, true);
    public static final Type type8 = new Type(TYPE1_ID + 7, "С механическим креплением", 100005, 2, false);
    public static final Type type9 = new Type(TYPE1_ID + 8, "Корпуса фрез", 100010, 3, true);
    public static final Type type10 = new Type(TYPE1_ID + 9, "Корпуса сверл", 100010, 3, true);
    public static final Type type11 = new Type(TYPE1_ID + 10, "Режущая часть", 0, 0, false);
    public static final Type type12 = new Type(TYPE1_ID + 11, "Токарная", 100013, 1, false);
    public static final Type type13 = new Type(TYPE1_ID + 12, "Пластинки для проходных резцов", 100014, 2, true);
    public static final Type type14 = new Type(TYPE1_ID + 13, "Пластинки для расточных резцов", 100014, 2, true);
    public static final Type type15 = new Type(TYPE1_ID + 14, "Вращающаяся", 100013, 1, false);


    public static final List<Type> renderedTypes = List.of(type1, type3, type8, type10, type9, type4, type7, type6, type5, type2,
            type11, type15, type12, type14, type13);

    public static final List<Type> types = List.of(type1, type2, type3, type4, type5, type6, type7, type8, type9, type10,
            type11, type12, type13, type14, type15);

    public static Type getNew() {
        return new Type(null, "Новый тип", 100006, 3, true);
    }

    public static Type getUpdated() {
        return new Type(TYPE1_ID, "Обновленный тип", 1, 0, false);
    }
}
