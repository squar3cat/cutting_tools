package com.app.tools.web.tool;

import com.app.tools.dto.ToolTo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.app.tools.model.Tool;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = ToolRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ToolRestController extends AbstractToolController {
    static final String REST_URL = "/rest/profile/tools";

    @Override
    @GetMapping("/{id}")
    public Tool get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping
    public List<ToolTo> getAll() {
        return super.getAll();
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Tool tool, @PathVariable int id) {
        super.update(tool, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tool> createWithLocation(@Valid @RequestBody Tool tool) {
        Tool created = super.create(tool);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @GetMapping("/filter")
    public List<ToolTo> getBetween(
            @RequestParam @Nullable Integer toolType,
            @RequestParam @Nullable Integer filteredLocation,
            @RequestParam @Nullable LocalDate startDate,
            @RequestParam @Nullable LocalDate endDate) {
        return super.getBetween(toolType, filteredLocation, startDate, endDate);
    }
}