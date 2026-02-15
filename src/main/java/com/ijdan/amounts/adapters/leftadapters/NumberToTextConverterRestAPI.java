package com.ijdan.amounts.adapters.leftadapters;

import com.ijdan.amounts.corelogic.ports.NumberToTextConverterPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/T1.0/amounts")
public class NumberToTextConverterRestAPI {
        @Autowired
        private NumberToTextConverterPort numberToTextConverterPort;

        @RequestMapping(value = "/{langue}/value/{value}", method = RequestMethod.GET, produces = {
                        MediaType.APPLICATION_JSON_VALUE })
        @ResponseStatus(HttpStatus.OK)

        public @ResponseBody StructureReponseAPI transformerNombreEnTexte(@PathVariable(value = "value") String value,
                        @PathVariable(value = "langue") String langue) {
                return new StructureReponseAPI(
                                numberToTextConverterPort.transformerLeNombreEnTexte(value, langue),
                                langue, value);
        }

}
