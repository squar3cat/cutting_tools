package com.app.tools.util;

import com.app.tools.dto.ToolTo;
import com.app.tools.model.Tool;

import java.util.*;
import java.util.stream.Collectors;

public class ToolUtil {

    private ToolUtil() {
    }

    public static List<ToolTo> getFilteredTool(Collection<Tool> tools) {
        return tools.stream()
                .map(tool -> createTool(tool, tool.getDeficiency() >= tool.getToolsCount()))
                .collect(Collectors.toList());
    }

    public static ToolTo createTool(Tool tool, boolean isDeficient) {
        return new ToolTo(tool.getId(), tool.getRegistrationDate(), tool.getDescription(), tool.getToolsCount(), tool.getManufacturer(), tool.getLocation(), tool.getDeficiency(), tool.getType(), isDeficient);
    }

}
