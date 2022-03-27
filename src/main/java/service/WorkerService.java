package service;

import model.Worker;

import java.util.List;

public interface WorkerService {

    void create(Worker worker);

    List<Worker> readAll();

    Worker read(int id);

    boolean update(Worker worker, int id);

    boolean delete(int id);
}
