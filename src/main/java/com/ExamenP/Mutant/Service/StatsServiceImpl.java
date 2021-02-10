package com.ExamenP.Mutant.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ExamenP.Mutant.DbData.DnaDao;
import com.ExamenP.Mutant.Excepcion.ServiceException;
import com.ExamenP.Mutant.Model.Stats;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    private DnaDao dnaDaoService;
    private BigDecimal count_Human;
    private BigDecimal count_Mutant;
    private BigDecimal ratio;

    /**
     * Método que analiza la secuencia AND y verifica si existe en la base de datos, si no existe la inserta
     * @return Stats
     */
    @Override
    public Stats GetStatsDna() throws ServiceException {
        Stats stats = new Stats();
        try {
            count_Human =  new BigDecimal(dnaDaoService.getDnaHumanCount());
            count_Mutant =  new BigDecimal(dnaDaoService.getDnaMutantCount());
            ratio = (count_Human.compareTo(BigDecimal.ZERO) != 0) ? count_Mutant.divide(count_Human, 2, RoundingMode.UNNECESSARY) : new BigDecimal("0.00");
            //ratio = Math.round((count_Human > 0 ? count_Mutant / count_Human : 0) * 100.0) / 100.0;

            stats.setCount_human_dna(dnaDaoService.getDnaHumanCount());
            stats.setCount_mutant_dna(dnaDaoService.getDnaMutantCount());
            stats.setRatio(ratio);

            return stats;
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}

