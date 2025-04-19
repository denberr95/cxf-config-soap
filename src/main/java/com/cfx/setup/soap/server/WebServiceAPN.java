package com.cfx.setup.soap.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import it.mkt.hub.soap.schemas.v1.apn.APN;
import it.mkt.hub.soap.schemas.v1.apn.flussiapn.FlussoAPNRESPType;
import it.mkt.hub.soap.schemas.v1.apn.messaggiosii.AzioneRichiestaType;
import it.mkt.hub.soap.schemas.v1.apn.messaggiosii.ComunicazioneAnnullamentoPraticaMessaggioSIIType;
import it.mkt.hub.soap.schemas.v1.apn.messaggiosii.DefaultResponseDatiSIIType;
import it.mkt.hub.soap.schemas.v1.apn.messaggiosii.DefaultResponseMessaggioSIIType;
import it.mkt.hub.soap.schemas.v1.apn.messaggiosii.RichiestaSIIType;
import it.mkt.hub.soap.schemas.v1.apn.strutturegenerali.DefaultEsitoType;
import it.mkt.hub.soap.schemas.v1.apn.strutturegenerali.IdentificativiRichiestaGestoreCPUtenteType;

@Component
public class WebServiceAPN implements APN {

    Logger logger = LoggerFactory.getLogger(WebServiceAPN.class);

    @Override
    public DefaultResponseMessaggioSIIType apn0200(
            ComunicazioneAnnullamentoPraticaMessaggioSIIType messaggioSII) {
        this.logger.info("Chiamata ricevuta su servizio APN.0200");
        DefaultResponseMessaggioSIIType defaultResponseMessaggioSIIType =
                new DefaultResponseMessaggioSIIType();

        RichiestaSIIType richiestaSII = new RichiestaSIIType();
        richiestaSII.setErogatore("EROGATORE");
        richiestaSII.setFruitore("FRUITORE");

        AzioneRichiestaType azioneRichiesta = new AzioneRichiestaType();
        azioneRichiesta.setServizio("APN");
        azioneRichiesta.setOperazione("APN.0200");
        richiestaSII.setAzioneRichiesta(azioneRichiesta);

        DefaultResponseDatiSIIType defaultResponseDatiSIIType = new DefaultResponseDatiSIIType();

        FlussoAPNRESPType flussoAPNRESPType = new FlussoAPNRESPType();
        flussoAPNRESPType.setCODSERVIZIO("APN");
        flussoAPNRESPType.setCODFLUSSO("0200");

        IdentificativiRichiestaGestoreCPUtenteType identificativiRichiestaGestoreCPUtenteType =
                new IdentificativiRichiestaGestoreCPUtenteType();
        identificativiRichiestaGestoreCPUtenteType.setCPGESTORE("CP_GESTORE");
        identificativiRichiestaGestoreCPUtenteType.setPIVAGESTORE("PIVA_GESTORE");
        identificativiRichiestaGestoreCPUtenteType.setPIVAUTENTE("PIVA_UTENTE");
        flussoAPNRESPType.setIdentificativiRichiesta(identificativiRichiestaGestoreCPUtenteType);

        DefaultEsitoType defaultEsitoType = new DefaultEsitoType();
        defaultEsitoType.setEsito(1);
        defaultEsitoType.setCodCausale("COD_CAUSALE");
        defaultEsitoType.setMotivazione("MOTIVAZIONE");
        flussoAPNRESPType.setEsito(defaultEsitoType);

        defaultResponseDatiSIIType.setFlusso(flussoAPNRESPType);
        defaultResponseMessaggioSIIType.setDatiSII(defaultResponseDatiSIIType);
        defaultResponseMessaggioSIIType.setRichiestaSII(richiestaSII);

        this.logger.info("Fornita risposta su servizio APN.0200");

        return defaultResponseMessaggioSIIType;
    }
}
