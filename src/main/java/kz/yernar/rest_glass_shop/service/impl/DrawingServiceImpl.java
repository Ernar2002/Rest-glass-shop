package kz.yernar.rest_glass_shop.service.impl;

import kz.yernar.rest_glass_shop.domain.Drawing;
import kz.yernar.rest_glass_shop.repository.DrawingRepository;
import kz.yernar.rest_glass_shop.service.DrawingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrawingServiceImpl implements DrawingService {

    private final DrawingRepository drawingRepository;

    public DrawingServiceImpl (
        DrawingRepository drawingRepository
    ) {
        this.drawingRepository = drawingRepository;
    }

    @Override
    public void save(Drawing drawing) {
        drawingRepository.save(drawing);
    }

    @Override
    public void update(Long id, Drawing drawing) {
        Drawing drawingFromDb = getById(id);

        drawingFromDb.setName(drawing.getName());
        drawingFromDb.setCost(drawing.getCost());

        save(drawingFromDb);
    }

    @Override
    public Drawing getById(Long id) {
        return drawingRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        drawingRepository.delete(getById(id));
    }

    @Override
    public List<Drawing> getAll() {
        return drawingRepository.findAll();
    }
}
