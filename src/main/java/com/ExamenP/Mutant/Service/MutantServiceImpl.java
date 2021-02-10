package com.ExamenP.Mutant.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ExamenP.Mutant.DbData.DnaDao;
import com.ExamenP.Mutant.Excepcion.IncorrectNitrogenBaseException;
import com.ExamenP.Mutant.Excepcion.InvalidDataReceivedException;
import com.ExamenP.Mutant.Excepcion.ServiceException;

import java.util.regex.Pattern;

@Service
public class MutantServiceImpl implements MutantService {
    @Autowired
    private DnaDao DnaDaoService;
    private static final Pattern NITROGENOUS_BASE_PATTERN = Pattern.compile("[atcg]+", Pattern.CASE_INSENSITIVE);


    /**
     * Método que analiza la secuencia AND y verifica si existe en la base de datos, si no existe la inserta
     *
     * @param dna String[]
     * @return boolean True(Mutant) / false (Human)
     */
    @Override
    public boolean isMutant(String[] dna) throws ServiceException, InvalidDataReceivedException, IncorrectNitrogenBaseException {
        boolean isMutant;
        DnaAnalyzeServiceImpl DnaAnalize = new DnaAnalyzeServiceImpl();
        try {
            isMutant = DnaAnalize.isMutant(dna);
            String DnaSequence = DnaToString(dna);
            boolean existDb = this.DnaDaoService.ExistDnaSequence(DnaSequence);
            if (!existDb) {
                this.DnaDaoService.InsertDnaSequence(DnaToString(dna), isMutant);
            }

            return isMutant;

        } catch (ServiceException ex) {
            throw new ServiceException(ex.getMessage());
        } catch (InvalidDataReceivedException ex) {
            throw new InvalidDataReceivedException(ex.getMessage());
        } catch (IncorrectNitrogenBaseException ex) {
            throw new IncorrectNitrogenBaseException(ex.getMessage());
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }

    }

    /**
     *Método que prepara la secuencia de ADN en el formato para insertar en la base de datos
     * @param dna String[]
     * @return String
     */

    private String DnaToString(String[] dna) {

        StringBuffer sb = new StringBuffer();
        sb.append("[dna:");
        for (int position = 0; position < dna.length; position++) {
            if (position == dna.length - 1) {
                sb.append(dna[position].toUpperCase());
            } else {
                sb.append(dna[position].toUpperCase()).append(" ");
            }
        }
        sb.append("]");

        return sb.toString();
    }
}
