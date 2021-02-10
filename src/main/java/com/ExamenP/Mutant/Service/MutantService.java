package com.ExamenP.Mutant.Service;


import com.ExamenP.Mutant.Excepcion.IncorrectNitrogenBaseException;
import com.ExamenP.Mutant.Excepcion.InvalidDataReceivedException;
import com.ExamenP.Mutant.Excepcion.ServiceException;
import com.ExamenP.Mutant.Model.ResponseDTO;


public interface MutantService {
    boolean isMutant(String[] dna) throws ServiceException, InvalidDataReceivedException, IncorrectNitrogenBaseException;// throws ServiceException, InputValidationException;
}

