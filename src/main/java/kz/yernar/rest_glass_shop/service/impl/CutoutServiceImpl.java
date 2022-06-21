package kz.yernar.rest_glass_shop.service.impl;

import kz.yernar.rest_glass_shop.domain.Cutout;
import kz.yernar.rest_glass_shop.repository.CutoutRepository;
import kz.yernar.rest_glass_shop.service.CutoutService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CutoutServiceImpl implements CutoutService {

    private final CutoutRepository cutoutRepository;

    public CutoutServiceImpl(
            CutoutRepository cutoutRepository
    ) {
        this.cutoutRepository = cutoutRepository;
    }
    @Override
    public void save(Cutout cutout) {
        cutoutRepository.save(cutout);
    }

    @Override
    public void update(Long id, Cutout cutout) {
        Cutout cutoutFromDb = getById(id);

        cutoutFromDb.setInsideCount(cutout.getInsideCount());
        cutoutFromDb.setOutsideCount(cutout.getOutsideCount());
        cutoutFromDb.setCost(cutout.getCost());
    }

    @Override
    public Cutout getById(Long id) {
        return cutoutRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        cutoutRepository.delete(getById(id));
    }

    @Override
    public List<Cutout> getAll() {
        return cutoutRepository.findAll();
    }
}
