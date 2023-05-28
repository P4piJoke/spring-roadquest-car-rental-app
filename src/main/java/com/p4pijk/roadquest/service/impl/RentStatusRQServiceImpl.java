package com.p4pijk.roadquest.service.impl;

import com.p4pijk.roadquest.entity.order.RentStatus;
import com.p4pijk.roadquest.repository.RentStatusRepository;
import com.p4pijk.roadquest.service.RentStatusRQService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentStatusRQServiceImpl implements RentStatusRQService {

    private final RentStatusRepository repository;

    @Override
    public List<RentStatus> findAll() {
        return repository.findAll();
    }

    @Override
    public RentStatus findById(int id) {
        RentStatus status;
        Optional<RentStatus> byId = repository.findById(id);
        if (byId.isPresent()){
            status = byId.get();
        }
        else {
            throw new RuntimeException("Can't find status by id - " + id);
        }
        return status;
    }

    @Override
    public RentStatus save(RentStatus newObj) {
        return repository.save(newObj);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
