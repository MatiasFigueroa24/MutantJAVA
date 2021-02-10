package com.ExamenP.Mutant.Service;

import com.ExamenP.Mutant.Excepcion.ServiceException;
import com.ExamenP.Mutant.Model.Stats;


public interface StatsService {

    Stats GetStatsDna() throws ServiceException;

}
