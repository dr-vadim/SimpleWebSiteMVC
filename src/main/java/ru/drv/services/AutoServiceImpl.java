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

    @Override
    public List<Auto> getAll() {
        return autoDao.findAll();
    }

    @Override
    public Auto get(int id) {
        return autoDao.find(id);
    }

    @Override
    public Auto save(Auto auto) {
        return autoDao.save(auto);
    }

    @Override
    public boolean delete(int id) {
        return autoDao.remove(id);
    }

    @Override
    public boolean deleteByUser(int userId) {
        return autoDao.removeByUser(userId);
    }

    @Override
    public boolean update(int id, Auto auto) {
        return autoDao.update(id, auto);
    }
}
