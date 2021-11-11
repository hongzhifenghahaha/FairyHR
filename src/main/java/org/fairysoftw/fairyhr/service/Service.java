package org.fairysoftw.fairyhr.service;

import lombok.RequiredArgsConstructor;
import org.fairysoftw.fairyhr.model.User;
import org.fairysoftw.fairyhr.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Service {
    private final UserMapper mapper;

    public boolean save(User user) {
        String id = user.getId();
        User currentUser = select(id);
        if (currentUser != null)
            return mapper.update(user) == 1;
        return mapper.insert(user) == 1;
    }

    public boolean delete(String id) {
        return mapper.deleteById(id) == 1;
    }

    public User select(String id) {
        return mapper.selectById(id);
    }

    public List<User> selectAll() {
        return mapper.selectAll();
    }
}
