package kz.yernar.rest_glass_shop.service.impl;

import kz.yernar.rest_glass_shop.domain.Laminate;
import kz.yernar.rest_glass_shop.repository.LaminateRepository;
import kz.yernar.rest_glass_shop.service.LaminateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaminateServiceImpl implements LaminateService {

    private final LaminateRepository laminateRepository;

    public LaminateServiceImpl (
            LaminateRepository laminateRepository
    ) {
        this.laminateRepository = laminateRepository;
    }


    @Override
    public void save(Laminate laminate) {
        laminateRepository.save(laminate);
    }

    @Override
    public void update(Long id, Laminate laminate) {
        Laminate laminateFromDb = getById(id);

        laminateFromDb.setCost(laminate.getCost());
        laminateFromDb.setName(laminate.getName());

        save(laminateFromDb);
    }

    @Override
    public Laminate getById(Long id) {
        return laminateRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        laminateRepository.delete(getById(id));
    }

    @Override
    public List<Laminate> getAll() {
        return laminateRepository.findAll();
    }
}
