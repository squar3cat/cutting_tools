package com.app.tools.web.tool;

import com.app.tools.View;
import com.app.tools.dto.ToolTo;
import com.app.tools.model.Tool;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/profile/tools", produces = MediaType.APPLICATION_JSON_VALUE)
public class ToolUIController extends AbstractToolController {

    @Override
    @GetMapping
    @JsonView(View.JsonUI.class)
    public List<ToolTo> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping( "/{id}")
    @JsonView(View.JsonUI.class)
    public Tool get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createOrUpdate(@Valid Tool tool) {
        if (tool.isNew()) {
            super.create(tool);
        } else {
            super.update(tool, tool.getId());
        }
    }

    @Override
    @GetMapping("/filter")
    @JsonView(View.JsonUI.class)
    public List<ToolTo> getBetween(
            @RequestParam @Nullable Integer toolType,
            @RequestParam @Nullable Integer filteredLocation,
            @RequestParam @Nullable LocalDate startDate,
            @RequestParam @Nullable LocalDate endDate) {
        return super.getBetween(toolType, filteredLocation, startDate, endDate);
    }


}