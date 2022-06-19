package kz.yernar.rest_glass_shop.service.impl;

import kz.yernar.rest_glass_shop.domain.EdgeTreatment;
import kz.yernar.rest_glass_shop.repository.EdgeTreatmentRepository;
import kz.yernar.rest_glass_shop.service.EdgeTreatmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdgeTreatmentServiceImpl implements EdgeTreatmentService {

    private final EdgeTreatmentRepository edgeTreatmentRepository;

    public EdgeTreatmentServiceImpl (
            EdgeTreatmentRepository edgeTreatmentRepository
    ) {
        this.edgeTreatmentRepository = edgeTreatmentRepository;
    }

    @Override
    public void save(EdgeTreatment edgeTreatment) {
        edgeTreatmentRepository.save(edgeTreatment);
    }

    @Override
    public void update(Long id, EdgeTreatment edgeTreatment) {
        EdgeTreatment edgeFromDb = getById(id);

        if(edgeTreatment.getName() != null) {
            edgeFromDb.setName(edgeTreatment.getName());
        }

        if(edgeTreatment.getCost() != 0) {
            edgeFromDb.setCost(edgeTreatment.getCost());
        }

        save(edgeFromDb);
    }

    @Override
    public EdgeTreatment getById(Long id) {
        return edgeTreatmentRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        edgeTreatmentRepository.delete(getById(id));
    }

    @Override
    public List<EdgeTreatment> getAll() {
        return edgeTreatmentRepository.findAll();
    }
}
