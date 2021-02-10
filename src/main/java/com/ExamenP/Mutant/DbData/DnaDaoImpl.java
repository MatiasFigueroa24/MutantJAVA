package com.ExamenP.Mutant.DbData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ExamenP.Mutant.Excepcion.ServiceException;

@Service
public class DnaDaoImpl implements DnaDao {
    public DnaDaoImpl() {
        super();
    }

    // Nosotros estamos cableando la plantilla jdbc
    // usando las propiedades que hemos configurado Spring automáticamente
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    /**
     * método que inserta la secuencia de ADN en la base de datos
     * @Params DnaSequence String
     * @Params isMutant boolean
     * @return boolean
     */
    public boolean InsertDnaSequence(String DnaSequence, boolean isMutant) throws ServiceException {
        try {
            Integer result = jdbcTemplate.update("INSERT INTO Dna (DnaSequence, IsMutant) VALUES (?, ?)", DnaSequence, isMutant);
            return result != null && result > 0;
        }
        catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
    /**
     * Método que busca la existencia de una secuencia de ADN en la base de datos
     * @Params DnaSequence String
     * @return boolean
     */
    @Override
    public boolean ExistDnaSequence(String DnaSequence) throws ServiceException {
        try {
            Integer result = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Dna WHERE DnaSequence = ?", new Object[]{DnaSequence}, Integer.class);
            return result != null && result > 0;
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
    /**
     * Método que devuelve la cantidad de mutantes
     * @return int
     */
    @Override
    public int getDnaHumanCount() throws ServiceException {
        try {
            Integer result = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Dna WHERE IsMutant=false;", Integer.class);

            return result;
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
    /**
     * Método que devuelve la cantidad de mutantes
     * @return int
     */
    @Override
    public int getDnaMutantCount() throws ServiceException {
        try {
            Integer result = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Dna WHERE IsMutant=true;", Integer.class);
            return result;
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

}
