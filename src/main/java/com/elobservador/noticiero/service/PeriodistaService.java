package com.elobservador.noticiero.service;

import com.elobservador.noticiero.entidades.Periodista;
import com.elobservador.noticiero.entidades.Usuario;

import java.util.List;

public interface PeriodistaService {

    Periodista createPeriodista(Periodista periodista);

    Periodista getPeriodista( String id);
    void updatePeriodista(Periodista periodista);

    void deletePeriodista( String id);

    List<Periodista> listPeriodistas();


}
