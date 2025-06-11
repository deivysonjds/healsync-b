package com.pi.healsync.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pi.healsync.models.Fila;
import com.pi.healsync.repositories.FilaRepository;

@Service
public class FilaService {
    @Autowired
    private FilaRepository filaRepository;

    @Transactional(readOnly = true)
    public boolean isFilaExists(LocalDate date) {
        Fila fila = filaRepository.findByDate(date);
        if (fila != null) {
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public Fila getFilaByDate(LocalDate date) {
        return filaRepository.findByDate(date);
    }

    @Transactional
    public Fila insert(Fila fila){
        return filaRepository.save(fila);
    }
}
