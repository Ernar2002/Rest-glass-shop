package kz.yernar.rest_glass_shop.service.impl;

import kz.yernar.rest_glass_shop.domain.Hole;
import kz.yernar.rest_glass_shop.repository.HoleRepository;
import kz.yernar.rest_glass_shop.service.HoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoleServiceImpl implements HoleService {

    private final HoleRepository holeRepository;

    public HoleServiceImpl (
            HoleRepository holeRepository
    ) {
        this.holeRepository = holeRepository;
    }

    @Override
    public void save(Hole hole) {
        holeRepository.save(hole);
    }

    @Override
    public void update(Long id, Hole hole) {
        Hole holeFromDb = getById(id);

        holeFromDb.setDiameter(hole.getDiameter());
        holeFromDb.setCount(hole.getCount());
        holeFromDb.setCost(hole.getCost());
        save(holeFromDb);
    }

    @Override
    public Hole getById(Long id) {
        return holeRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        holeRepository.delete(getById(id));
    }

    @Override
    public List<Hole> getAll() {
        return holeRepository.findAll();
    }
}
