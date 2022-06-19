package kz.yernar.rest_glass_shop.controller.moderator;

import kz.yernar.rest_glass_shop.domain.EdgeTreatment;
import kz.yernar.rest_glass_shop.service.EdgeTreatmentService;
import kz.yernar.rest_glass_shop.utils.response.DefaultResponse;
import kz.yernar.rest_glass_shop.utils.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/mod/edge", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", maxAge = 3600)
public class EdgeTreatmentRestController {

    private final EdgeTreatmentService edgeTreatmentService;

    public EdgeTreatmentRestController(
            EdgeTreatmentService edgeTreatmentService
    ) {
        this.edgeTreatmentService = edgeTreatmentService;
    }

    @GetMapping
    public ResponseEntity<List<EdgeTreatment>> getAll() {
        return ResponseEntity.ok(edgeTreatmentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            if (id == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok(new DefaultResponse(false, "", edgeTreatmentService.getById(id)));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(new DefaultResponse<>(true, "Error!", null));
        }

    }

    @PostMapping("/add")
    public ResponseEntity<?> addEdge(@RequestBody EdgeTreatment edgeTreatment){
        try {
            edgeTreatmentService.save(edgeTreatment);

            return ResponseEntity.ok(new DefaultResponse<>(false, "Added successfully", edgeTreatment));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(new DefaultResponse<>(true, "Error!", null));
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateEdge(@PathVariable("id") Long id,
                                                    @RequestBody EdgeTreatment edgeTreatment) {
        try {
            edgeTreatmentService.update(id, edgeTreatment);

            return ResponseEntity.ok(new DefaultResponse<>(false, "Updated successfully", edgeTreatmentService.getById(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DefaultResponse<>(true, "Error!", null));
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteEdge(@PathVariable("id") Long id){
        try {
            edgeTreatmentService.delete(id);

            return ResponseEntity.ok(new DefaultResponse<>(false, "Edge treatment deleted successfully", null));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(new DefaultResponse<>(true, "Error!", null));
        }
    }
}
