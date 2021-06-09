package com.app.tools.repository;

import com.app.tools.repository.type.TypeRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.app.tools.model.Tool;

import java.time.LocalDate;
import java.util.List;

import static com.app.tools.util.TypeUtil.getFilteredTypes;

@Repository
public class DataJpaToolRepository {

    private final ToolRepository toolRepository;
    private final UserRepository userRepository;
    private final TypeRepository typeRepository;

    public DataJpaToolRepository(ToolRepository toolRepository,
                                 UserRepository userRepository,
                                 TypeRepository typeRepository) {
        this.toolRepository = toolRepository;
        this.userRepository = userRepository;
        this.typeRepository = typeRepository;
    }

    @Transactional
    public Tool save(Tool tool, int userId) {
        if (!tool.isNew() && get(tool.getId()) == null) {
            return null;
        }
        tool.setUser(userRepository.getOne(userId));
        return toolRepository.save(tool);
    }

    public boolean delete(int id) {
        return toolRepository.delete(id) != 0;
    }

    public Tool get(int id) {
        return toolRepository.findById(id)
                .orElse(null);
    }

    public List<Tool> getAll() {
        return toolRepository.getAll();
    }

    public List<Tool> getBetween(Integer toolType, Integer filteredLocation, LocalDate startDate, LocalDate endDate) {

        if (filteredLocation==0) filteredLocation=null;

        List<Integer> typeList;
        if (toolType==0)
            typeList=null;
        else
            typeList = getFilteredTypes(toolType, typeRepository.getAll());

        return toolRepository.getBetween(typeList, filteredLocation, startDate, endDate);
    }

    public Tool getWithUser(int id, int userId) {
        return toolRepository.getWithUser(id, userId);
    }

}
