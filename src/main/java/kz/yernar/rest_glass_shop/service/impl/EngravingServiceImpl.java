package kz.yernar.rest_glass_shop.service.impl;

import kz.yernar.rest_glass_shop.domain.Engraving;
import kz.yernar.rest_glass_shop.repository.EngravingRepository;
import kz.yernar.rest_glass_shop.service.EngravingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngravingServiceImpl implements EngravingService {

    private final EngravingRepository engravingRepository;

    public EngravingServiceImpl (
            EngravingRepository engravingRepository
    ) {
        this.engravingRepository = engravingRepository;
    }

    @Override
    public void save(Engraving engraving) {
        engravingRepository.save(engraving);
    }

    @Override
    public void update(Long id, Engraving engraving) {
        Engraving engravingFromDb = getById(id);

        engravingFromDb.setMeter(engraving.getMeter());
        engravingFromDb.setCost(engraving.getCost());

        save(engravingFromDb);
    }

    @Override
    public Engraving getById(Long id) {
        return engravingRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        engravingRepository.delete(getById(id));
    }

    @Override
    public List<Engraving> getAll() {
        return engravingRepository.findAll();
    }
}
