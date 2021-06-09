package com.app.tools.web.tool;

import com.app.tools.dto.ToolTo;
import com.app.tools.model.Location;
import com.app.tools.model.Type;
import com.app.tools.service.LocationService;
import com.app.tools.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import com.app.tools.model.Tool;
import com.app.tools.service.ToolService;
import com.app.tools.util.ToolUtil;
import com.app.tools.web.SecurityUtil;

import java.time.LocalDate;
import java.util.List;

import static com.app.tools.util.TypeUtil.renderTypeHierarchy;
import static com.app.tools.util.ValidationUtil.assureIdConsistent;
import static com.app.tools.util.ValidationUtil.checkNew;

public abstract class AbstractToolController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ToolService service;

    @Autowired
    private LocationService locationService;

    @Autowired
    private TypeService typeService;

    public Tool get(int id) {
        log.info("get tool {}", id);
        return service.get(id);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete tool {} by user {}", id, userId);
        service.delete(id);
    }

    public List<ToolTo> getAll() {
        log.info("getAll tools");
        return ToolUtil.getFilteredTool(service.getAll());
    }

    public Tool create(Tool tool) {
        int userId = SecurityUtil.authUserId();
        checkNew(tool);
        log.info("create {} by user {}", tool, userId);
        return service.create(tool, userId);
    }

    public void update(Tool tool, int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(tool, id);
        log.info("update {} by user {}", tool, userId);
        service.update(tool, userId);
    }

    public List<ToolTo> getBetween(@Nullable Integer toolType, @Nullable Integer filteredLocation,
                                   @Nullable LocalDate startDate, @Nullable LocalDate endDate) {
        log.info("filtered by type {}, location {}, between dates({} - {})", toolType, filteredLocation, startDate, endDate);
        return ToolUtil.getFilteredTool(service.getBetween(toolType, filteredLocation, startDate, endDate));
    }

    public List<Location> getAllLocations() {
        return locationService.getAll();
    }

    public List<Type> getAllTypes() {
        return renderTypeHierarchy(typeService.getAll());
    }
}