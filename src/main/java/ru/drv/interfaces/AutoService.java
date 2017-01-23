package ru.drv.interfaces;

import ru.drv.models.Auto;

import java.util.List;

public interface AutoService {
    List<Auto> getAutoByUser(int userId);
    List<Auto> getAll();
    Auto get(int id);
    Auto save(Auto auto);
    boolean delete(int id);
    boolean deleteByUser(int userId);
    boolean update(int id, Auto auto);
}
