package com.app.tools.service;

import com.app.tools.repository.DataJpaToolRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.app.tools.model.Tool;

import java.time.LocalDate;
import java.util.List;

import static com.app.tools.util.DateUtil.atStartOfDayOrMin;
import static com.app.tools.util.DateUtil.atStartOfNextDayOrMax;
import static com.app.tools.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ToolService {

    private final DataJpaToolRepository repository;

    public ToolService(DataJpaToolRepository repository) {
        this.repository = repository;
    }

    public Tool get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Tool> getAll() {
        return repository.getAll();
    }

    public List<Tool> getBetween(@Nullable Integer toolType, @Nullable Integer filteredLocation,
                                 @Nullable LocalDate startDate, @Nullable LocalDate endDate) {
        return repository.getBetween(toolType, filteredLocation, atStartOfDayOrMin(startDate), atStartOfNextDayOrMax(endDate));
    }

    public Tool getWithUser(int id, int userId) {
        return checkNotFoundWithId(repository.getWithUser(id, userId), id);
    }

    public Tool create(Tool tool, int userId) {
        Assert.notNull(tool, "tool must not be null");
        return repository.save(tool, userId);
    }

    public void update(Tool tool, int userId) {
        Assert.notNull(tool, "tool must not be null");
        checkNotFoundWithId(repository.save(tool, userId), tool.getId());
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

}