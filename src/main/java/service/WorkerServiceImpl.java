package service;

import model.Worker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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
        return null;
    }

    @Override
    public Worker read(int id) {
        return null;
    }

    @Override
    public boolean update(Worker worker, int id) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
