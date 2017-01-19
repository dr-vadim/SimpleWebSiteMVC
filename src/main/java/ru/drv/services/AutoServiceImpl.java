package ru.drv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.drv.interfaces.AutoDao;
import ru.drv.interfaces.AutoService;
import ru.drv.models.Auto;

import java.util.List;

@Service
public class AutoServiceImpl implements AutoService {


    private AutoDao autoDao;

    @Autowired
    public AutoServiceImpl(AutoDao autoDao){
        this.autoDao = autoDao;
    }

    @Override
    public List<Auto> getAutoByUser(int userId) {
        return autoDao.findByUser(userId);
    }
}
