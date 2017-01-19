package ru.drv.interfaces;

import ru.drv.models.Auto;

import java.util.List;

public interface AutoService {
    List<Auto> getAutoByUser(int userId);
}
