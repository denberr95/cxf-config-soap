package com.cfx.issue.soap.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import it.mkt.hub.soap.schemas.v1.apr.APR;
import it.mkt.hub.soap.schemas.v1.apr.flussiapr.FlussoAPR0100Type;
import it.mkt.hub.soap.schemas.v1.apr.messaggiosii.AmmissibilitaRichiestaDatiSIIType;
import it.mkt.hub.soap.schemas.v1.apr.messaggiosii.AmmissibilitaRichiestaMessaggioSIIType;
import it.mkt.hub.soap.schemas.v1.apr.messaggiosii.AzioneRichiestaType;
import it.mkt.hub.soap.schemas.v1.apr.messaggiosii.EsitoAnnullamentoPraticaMessaggioSIIType;
import it.mkt.hub.soap.schemas.v1.apr.messaggiosii.RichiestaAnnullamentoPraticaMessaggioSIIType;
import it.mkt.hub.soap.schemas.v1.apr.messaggiosii.RichiestaSIIType;
import it.mkt.hub.soap.schemas.v1.apr.strutturegenerali.AmmissibilitaBaseType;
import it.mkt.hub.soap.schemas.v1.apr.strutturegenerali.AnnullamentoDatiPraticaBaseType;
import it.mkt.hub.soap.schemas.v1.apr.strutturegenerali.IdentificativiRichiestaUtenteCPOpzGestoreCPOpzType;

@Component
public class WebServiceAPR implements APR {

    Logger logger = LoggerFactory.getLogger(WebServiceAPR.class);

    @Override
    public void apr0150(EsitoAnnullamentoPraticaMessaggioSIIType messaggioSII) {
        this.logger.info("Chiamata ricevuta su servizio APR.0150");
    }

    @Override
    public AmmissibilitaRichiestaMessaggioSIIType apr0050(
            RichiestaAnnullamentoPraticaMessaggioSIIType messaggioSII) {
        this.logger.info("Chiamata ricevuta su servizio APR.0050");
        AmmissibilitaRichiestaMessaggioSIIType ammissibilitaRichiestaMessaggioSIIType =
                new AmmissibilitaRichiestaMessaggioSIIType();

        RichiestaSIIType richiestaSIIType = new RichiestaSIIType();
        richiestaSIIType.setErogatore("Erogatore");
        richiestaSIIType.setFruitore("Fruitore");

        AzioneRichiestaType azioneRichiestaType = new AzioneRichiestaType();
        azioneRichiestaType.setServizio("APR");
        azioneRichiestaType.setOperazione("APR.0050");
        richiestaSIIType.setAzioneRichiesta(azioneRichiestaType);

        AmmissibilitaRichiestaDatiSIIType ammissibilitaRichiestaDatiSIIType =
                new AmmissibilitaRichiestaDatiSIIType();

        FlussoAPR0100Type flussoAPR0100Type = new FlussoAPR0100Type();
        flussoAPR0100Type.setCODFLUSSO("COD_FLUSSI");
        flussoAPR0100Type.setCODSERVIZIO("COD_SERVIZIO");

        IdentificativiRichiestaUtenteCPOpzGestoreCPOpzType identificativiRichiestaGestoreCPUtenteType =
                new IdentificativiRichiestaUtenteCPOpzGestoreCPOpzType();
        identificativiRichiestaGestoreCPUtenteType.setCPGESTORE("CP_GESTORE");
        identificativiRichiestaGestoreCPUtenteType.setCPUTENTE("CP_UTENTE");
        identificativiRichiestaGestoreCPUtenteType.setPIVAGESTORE("PIVA_GESTORE");
        identificativiRichiestaGestoreCPUtenteType.setPIVAUTENTE("PIVA_UTENTE");
        flussoAPR0100Type.setIdentificativiRichiesta(identificativiRichiestaGestoreCPUtenteType);

        AmmissibilitaBaseType ammissibilitaBaseType = new AmmissibilitaBaseType();
        ammissibilitaBaseType.setVERIFICAAMM(1);
        ammissibilitaBaseType.setCODCAUSALE("COD_CAUSALE");
        ammissibilitaBaseType.setMOTIVAZIONE("MOTIVAZIONE");
        flussoAPR0100Type.setAmmissibilita(ammissibilitaBaseType);

        AnnullamentoDatiPraticaBaseType annullamentoDatiPraticaBaseType =
                new AnnullamentoDatiPraticaBaseType();
        annullamentoDatiPraticaBaseType.setCPGESTOREANN("CP_GESTORE_ANN");
        flussoAPR0100Type.setDatiPratica(annullamentoDatiPraticaBaseType);

        ammissibilitaRichiestaDatiSIIType.setFlusso(flussoAPR0100Type);
        ammissibilitaRichiestaMessaggioSIIType.setRichiestaSII(richiestaSIIType);
        ammissibilitaRichiestaMessaggioSIIType.setDatiSII(ammissibilitaRichiestaDatiSIIType);

        this.logger.info("Fornita risposta su servizio APR.0100");

        return ammissibilitaRichiestaMessaggioSIIType;
    }
}
