package com.cfx.issue.soap.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import it.mkt.hub.soap.schemas.v1.apr.APR;
import it.mkt.hub.soap.schemas.v1.apr.messaggiosii.EsitoAnnullamentoPraticaDatiSIIType;
import it.mkt.hub.soap.schemas.v1.apr.messaggiosii.EsitoAnnullamentoPraticaMessaggioSIIType;
import it.mkt.hub.soap.schemas.v1.apr.messaggiosii.RichiestaAnnullamentoPraticaDatiSIIType;
import it.mkt.hub.soap.schemas.v1.apr.messaggiosii.RichiestaAnnullamentoPraticaMessaggioSIIType;
import it.mkt.hub.soap.schemas.v1.apr.messaggiosii.RichiestaSIIType;

@Component
public class ClientAPR {

    Logger logger = LoggerFactory.getLogger(ClientAPR.class);

    private final APR apr;

    public ClientAPR(APR apr) {
        this.apr = apr;
    }

    public void apr0050() {
        this.logger.info("Chiamata al servizio APR.0050");
        RichiestaAnnullamentoPraticaMessaggioSIIType messaggioSII =
                new RichiestaAnnullamentoPraticaMessaggioSIIType();

        RichiestaSIIType richiestaSIIType = new RichiestaSIIType();

        RichiestaAnnullamentoPraticaDatiSIIType richiestaAnnullamentoPraticaDatiSIIType =
                new RichiestaAnnullamentoPraticaDatiSIIType();

        messaggioSII.setRichiestaSII(richiestaSIIType);
        messaggioSII.setDatiSII(richiestaAnnullamentoPraticaDatiSIIType);

        this.apr.apr0050(messaggioSII);
        this.logger.info("Chiamata APR.0100 eseguita con successo");
    }

    public void apr0150() {
        this.logger.info("Chiamata al servizio APR.0150");
        EsitoAnnullamentoPraticaMessaggioSIIType messaggioSII =
                new EsitoAnnullamentoPraticaMessaggioSIIType();

        RichiestaSIIType richiestaSIIType = new RichiestaSIIType();

        EsitoAnnullamentoPraticaDatiSIIType esitoAnnullamentoPraticaDatiSIIType =
                new EsitoAnnullamentoPraticaDatiSIIType();

        messaggioSII.setRichiestaSII(richiestaSIIType);
        messaggioSII.setDatiSII(esitoAnnullamentoPraticaDatiSIIType);

        this.apr.apr0150(messaggioSII);
        this.logger.info("Chiamata APR.0150 eseguita con successo");
    }
}
