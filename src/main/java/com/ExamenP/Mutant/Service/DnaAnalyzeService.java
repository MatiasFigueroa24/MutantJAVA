package com.ExamenP.Mutant.Service;


import com.ExamenP.Mutant.Excepcion.IncorrectNitrogenBaseException;
import com.ExamenP.Mutant.Excepcion.InvalidDataReceivedException;
import com.ExamenP.Mutant.Excepcion.ServiceException;

public interface DnaAnalyzeService {
    boolean isMutant(String[] dna) throws ServiceException, InvalidDataReceivedException, IncorrectNitrogenBaseException;
}
