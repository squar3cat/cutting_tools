package com.app.tools.util;

import com.app.tools.model.Type;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TypeUtil {

    private TypeUtil() {
    }

    public static List<Type> renderTypeHierarchy(List<Type> types) {

        List<Type> renderedList = types.stream().filter(type->type.getParentId()==0).collect(Collectors.toList());

        Integer maxLevel = types.stream()
                .max(Comparator.comparing(Type::getLevel))
                .get().getLevel();

        int level = 1;
        while (level <= maxLevel) {
            for (Type type : types) {
                if (type.getLevel()==level) {
                    int index = IntStream.range(0, renderedList.size())
                            .filter(i -> renderedList.get(i).getId().equals(type.getParentId()))
                            .findFirst()
                            .orElse(-1);
                    renderedList.add(index+1, type);
                }
            }
            level++;
        }
        return renderedList;
    }

    public static List<Integer> getFilteredTypes(Integer currentTypeId, List<Type> types) {

        List<Integer> childList  = new ArrayList<>();
        List<Integer> newChildList = getAllChilds(types, currentTypeId, childList);
        newChildList.add(0, currentTypeId);

        return newChildList;

    }

    public static List<Integer> getAllChilds(List<Type> types, int parentId, List<Integer> childList) {
        for (Type type : types) {
            if (type.getParentId()!=null && type.getParentId() == parentId) {
                childList.add(type.getId());
                getAllChilds(types, type.getId(),childList);
            }
        }
        return childList;
    }

}
