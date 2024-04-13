package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dao.ScrewdriversDAO;
import org.example.entity.Screwdriver;
import org.example.enums.Characteristic;
import org.example.enums.Country;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class ScrewdriverServiceImpl implements ScrewdriverService {
    private final ScrewdriversDAO screwdriversDAO;
    
    @Override
    @Transactional
    public void add(Screwdriver screwdriver) {
        screwdriversDAO.add(screwdriver);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Optional<Screwdriver> optionalScrewdriver = screwdriversDAO.findById(id); // 1

        optionalScrewdriver.ifPresent(screwdriver -> {
           screwdriversDAO.delete(screwdriver); // 2
            System.out.println("Screwdriver " + screwdriver + " removed!!!!");
        });
    }

    @Override
    public List<Screwdriver> findAll() {
        return screwdriversDAO.findAll();
    }

    @Override
    public Long findQuantityByCountry(Country country) {
        return screwdriversDAO.findByCountry(country).stream().count();
    }

    @Override
    public Optional<Screwdriver> findById(UUID id) {
        return screwdriversDAO.findById(id);
    }

    @Override
    @Transactional
    public void update(UUID id) {
        screwdriversDAO.findById(id)
                .ifPresent(screwdriver -> {
                    screwdriver.setCharacteristic(Characteristic.BRUSH);
                    screwdriver.setModel("BOSCHHHH");

                    screwdriversDAO.update(screwdriver);
                });
    }
}
