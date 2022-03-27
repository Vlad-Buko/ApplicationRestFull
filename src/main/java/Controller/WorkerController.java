package Controller;

import model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.WorkerService;

import java.util.List;

@RestController
public class WorkerController {

    private final WorkerService workerService;

    @Autowired
    private WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PostMapping(value ="/worker")
    public ResponseEntity<?> createWorker (@RequestBody Worker worker) {
        workerService.create(worker);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/worker")
    public ResponseEntity<List<Worker>> read() {
        final List<Worker> workers = workerService.readAll();

        return workers != null &&  !workers.isEmpty()
                ? new ResponseEntity<>(workers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/worker/{id}")
    public ResponseEntity<Worker> readFromId(@PathVariable(name = "id") int id) {
        final Worker workerId = workerService.read(id);

        return workerId != null
                ? new ResponseEntity<>(workerId, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/workers/{id}")
    public ResponseEntity<?> updateWorker(@PathVariable(name = "id") int id, @RequestBody Worker worker) {
        final boolean updated = workerService.update(worker, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/workers/{id}")
    public ResponseEntity<?> deleteWorker(@PathVariable(name = "id") int id) {
        final boolean deleted = workerService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
