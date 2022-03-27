package service;

import model.Worker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class WorkerServiceImpl implements WorkerService{

    private static final Map<Integer, Worker> WORKER_REPOSITORY_MAP = new HashMap<>();

    // Переменная для генерации ID worker
    private static final AtomicInteger WORKER_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Worker worker) {
        final int workerId = WORKER_ID_HOLDER.incrementAndGet();
        worker.setId(workerId);
        WORKER_REPOSITORY_MAP.put(workerId, worker);
    }

    @Override
    public List<Worker> readAll() {
        return new ArrayList<>(WORKER_REPOSITORY_MAP.values());
    }

    @Override
    public Worker read(int id) {
        return WORKER_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Worker worker, int id) {
        if (WORKER_REPOSITORY_MAP.containsKey(id)) {
            worker.setId(id);
            WORKER_REPOSITORY_MAP.put(id, worker);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        return WORKER_REPOSITORY_MAP.remove(id) != null;
    }
}
