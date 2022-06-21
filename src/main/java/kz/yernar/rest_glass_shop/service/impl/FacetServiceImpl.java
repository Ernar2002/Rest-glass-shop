package kz.yernar.rest_glass_shop.service.impl;

import kz.yernar.rest_glass_shop.domain.Facet;
import kz.yernar.rest_glass_shop.repository.FacetRepository;
import kz.yernar.rest_glass_shop.service.FacetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacetServiceImpl implements FacetService {

    private final FacetRepository facetRepository;

    public FacetServiceImpl (
            FacetRepository facetRepository
    ) {
        this.facetRepository = facetRepository;
    }

    @Override
    public void save(Facet facet) {
        facetRepository.save(facet);
    }

    @Override
    public void update(Long id, Facet facet) {
        Facet facetFromDb = getById(id);

        facetFromDb.setFirstSide(facet.getFirstSide());
        facetFromDb.setSecondSide(facet.getSecondSide());
        facetFromDb.setCost(facet.getCost());

        save(facetFromDb);
    }

    @Override
    public Facet getById(Long id) {
        return facetRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        facetRepository.delete(getById(id));
    }

    @Override
    public List<Facet> getAll() {
        return facetRepository.findAll();
    }
}
