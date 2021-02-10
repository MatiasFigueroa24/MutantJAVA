package com.ExamenP.Mutant.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.ExamenP.Mutant.Excepcion.IncorrectNitrogenBaseException;
import com.ExamenP.Mutant.Excepcion.InvalidDataReceivedException;
import com.ExamenP.Mutant.Excepcion.ServiceException;
import com.ExamenP.Mutant.Model.DnaSequence;
import com.ExamenP.Mutant.Model.EnumErrorCode;
import com.ExamenP.Mutant.Model.ResponseDTO;
import com.ExamenP.Mutant.Model.Stats;
import com.ExamenP.Mutant.Service.MutantService;
import com.ExamenP.Mutant.Service.StatsService;

/**
 * Controller for mutant api
 * @CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)

 * @author Matias Figueroa
 */
@RestController
@CrossOrigin(origins= "http://localhost:4200",maxAge = 3600)
public class MutantController {
    @Autowired
    private MutantService mutantsService;
    @Autowired
    StatsService statsService;
    private Stats stats;

    /**
     * Método que comprueba el estado del equilibrador de carga
     */
    @GetMapping(value = "status")
    String checkStatus() {
        return "ok";
    }

   
    /**
     *Método que analiza una secuencia de ADN y determina si es humana o mutante
     * @param dna String[]
     * @return is mutant 200(OK) / is Human 403(FORBIDDEN)
     */
    @RequestMapping(value = "v1/mutant", method = RequestMethod.POST,produces = "application/json; charset-UTF-8")
    public ResponseEntity<ResponseDTO> mutant(@RequestBody DnaSequence dna) {
        ResponseDTO responseDTO=new ResponseDTO();
        ResponseEntity<ResponseDTO> responseEntity;
        boolean isMutant;
        try {
            if (dna.getDna() != null) {
                isMutant = mutantsService.isMutant(dna.getDna());

                if (isMutant) {
                    responseDTO.setMessage("Is Mutant");
                    responseEntity = new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
                } else {
                    responseDTO.setMessage("Is Human");
                    responseEntity = new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.FORBIDDEN);
                }
            } else {
                responseDTO.setErrorCode(EnumErrorCode.InvalidDataReceived.getErrorCode());
                responseDTO.setMessage("The data structure sent is not correct, please verify the documentation.");
                responseEntity = new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (ServiceException e) {
            responseDTO.setErrorCode(EnumErrorCode.GeneralException.getErrorCode());
            responseDTO.setMessage(e.getMessage());
            responseEntity = new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (InvalidDataReceivedException e) {
            responseDTO.setErrorCode(EnumErrorCode.InvalidDataReceived.getErrorCode());
            responseDTO.setMessage(e.getMessage());
            responseEntity = new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IncorrectNitrogenBaseException e) {
            responseDTO.setErrorCode(EnumErrorCode.IncorrectNitrogenBase.getErrorCode());
            responseDTO.setMessage(e.getMessage());
            responseEntity = new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            responseDTO.setErrorCode(EnumErrorCode.GeneralException.getErrorCode());
            responseDTO.setMessage(e.getMessage());
            responseEntity = new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return responseEntity;
    }

    /**
      Método que devuelve las estadísticas de las secuencias analizadas   
   * @return a Json data whit the statistics 200(OK)
     */
    @RequestMapping(value = "v1/stats", method = RequestMethod.GET, produces = "application/json; charset-UTF-8")
    public ResponseEntity<Stats> getStatics() {

        try {
            stats = statsService.GetStatsDna();
            return new ResponseEntity<>(stats, HttpStatus.OK);
        } catch (ServiceException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found", ex);
        }
    }

}


