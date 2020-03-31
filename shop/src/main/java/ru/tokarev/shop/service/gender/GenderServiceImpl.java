package ru.tokarev.shop.service.gender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tokarev.shop.repository.GenderRepository;
import ru.tokarev.shop.repository.entity.Gender;

import java.io.Serializable;
import java.util.List;

@Service("genderService")
public class GenderServiceImpl implements GenderService, Serializable {

    private static final long serialVersionUID = 4879353679130292018L;

    private GenderRepository genderRepository;

    @Autowired
    public void setGenderRepository(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @Override
    public List<Gender> findAll() {
        return genderRepository.findAll();
    }

    @Override
    public Gender get(int id) {
        return genderRepository.findById(id).get();
    }

    @Override
    public void addGenders(Gender article) {

    }

    @Override
    public void deleteById(int id) {
        genderRepository.deleteById(id);
    }

    @Override
    public void delete(Gender gender) {
        genderRepository.delete(gender);
    }

    @Override
    public Gender findOneByNameGender(String nameGender) {
        return genderRepository.findOneByNameGender(nameGender);
    }

    @Override
    public boolean isPresent(String nameGender) {
        return genderRepository.findOneByNameGender(nameGender) != null;
    }
}
