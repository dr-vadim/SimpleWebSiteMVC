package ru.drv.interfaces;

import ru.drv.models.Auto;

import java.util.List;

public interface AutoDao extends MainDao<Auto>{
    List<Auto> findByUser(int userId);
    boolean removeByUser(int id);
}
