package com.cfx.issue.soap.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import it.mkt.hub.soap.schemas.v1.apn.APN;
import it.mkt.hub.soap.schemas.v1.apn.flussiapn.FlussoAPN0200Type;
import it.mkt.hub.soap.schemas.v1.apn.messaggiosii.AzioneRichiestaType;
import it.mkt.hub.soap.schemas.v1.apn.messaggiosii.ComunicazioneAnnullamentoPraticaDatiSIIType;
import it.mkt.hub.soap.schemas.v1.apn.messaggiosii.ComunicazioneAnnullamentoPraticaMessaggioSIIType;
import it.mkt.hub.soap.schemas.v1.apn.messaggiosii.DefaultResponseMessaggioSIIType;
import it.mkt.hub.soap.schemas.v1.apn.messaggiosii.RichiestaSIIType;
import it.mkt.hub.soap.schemas.v1.apn.strutturegenerali.AnnullamentoDatiPraticaType;
import it.mkt.hub.soap.schemas.v1.apn.strutturegenerali.DatiAnnullamentoPraticaType;
import it.mkt.hub.soap.schemas.v1.apn.strutturegenerali.DatiTecniciMinimaliType;
import it.mkt.hub.soap.schemas.v1.apn.strutturegenerali.IdentificativiRichiestaGestoreCPUtenteType;
import it.mkt.hub.soap.schemas.v1.apn.strutturegenerali.SettoreType;

@Component
public class ClientAPN {

    Logger logger = LoggerFactory.getLogger(ClientAPN.class);

    private final APN apn;

    public ClientAPN(APN apn) {
        this.apn = apn;
    }

    public void apn0200() {
        this.logger.info("Chiamata al servizio APN.0200");
        ComunicazioneAnnullamentoPraticaMessaggioSIIType messaggioSII =
                new ComunicazioneAnnullamentoPraticaMessaggioSIIType();

        RichiestaSIIType richiestaSII = new RichiestaSIIType();
        richiestaSII.setErogatore("Erogatore");
        richiestaSII.setFruitore("Fruitore");

        AzioneRichiestaType azioneRichiesta = new AzioneRichiestaType();
        azioneRichiesta.setServizio("APN");
        azioneRichiesta.setOperazione("APN.0200");

        richiestaSII.setAzioneRichiesta(azioneRichiesta);

        ComunicazioneAnnullamentoPraticaDatiSIIType comunicazioneAnnullamentoPraticaDatiSIIType =
                new ComunicazioneAnnullamentoPraticaDatiSIIType();
        FlussoAPN0200Type flussoAPN0200Type = new FlussoAPN0200Type();
        flussoAPN0200Type.setCODSERVIZIO("APN");
        flussoAPN0200Type.setCODFLUSSO("0200");

        DatiAnnullamentoPraticaType datiAnnullamentoPraticaType = new DatiAnnullamentoPraticaType();
        datiAnnullamentoPraticaType.setCODCAUSALE("COD_CAUSALE");
        datiAnnullamentoPraticaType.setMOTIVAZIONE("MOTIVAZIONE");
        datiAnnullamentoPraticaType.setNOTE("NOTE");
        flussoAPN0200Type.setDatiAnnullamento(datiAnnullamentoPraticaType);

        IdentificativiRichiestaGestoreCPUtenteType identificativiRichiestaGestoreCPUtenteType =
                new IdentificativiRichiestaGestoreCPUtenteType();
        identificativiRichiestaGestoreCPUtenteType.setCPGESTORE("CP_GESTORE");
        identificativiRichiestaGestoreCPUtenteType.setPIVAGESTORE("PIVA_GESTORE");
        identificativiRichiestaGestoreCPUtenteType.setPIVAUTENTE("PIVA_UTENTE");
        flussoAPN0200Type.setIdentificativiRichiesta(identificativiRichiestaGestoreCPUtenteType);

        AnnullamentoDatiPraticaType annullamentoDatiPraticaType = new AnnullamentoDatiPraticaType();
        annullamentoDatiPraticaType.setCPUTENTEANN("CP_UTENTE_ANN");
        annullamentoDatiPraticaType.setCPGESTOREANN("CP_GESTORE_ANN");

        SettoreType settoreType = SettoreType.G;
        annullamentoDatiPraticaType.setSETTORE(settoreType);
        flussoAPN0200Type.setDatiPratica(annullamentoDatiPraticaType);

        DatiTecniciMinimaliType datiTecniciMinimaliType = new DatiTecniciMinimaliType();
        datiTecniciMinimaliType.setCODPDR("COD_PDR");
        flussoAPN0200Type.setDatiTecnici(datiTecniciMinimaliType);

        comunicazioneAnnullamentoPraticaDatiSIIType.setFlusso(flussoAPN0200Type);

        messaggioSII.setRichiestaSII(richiestaSII);
        messaggioSII.setDatiSII(comunicazioneAnnullamentoPraticaDatiSIIType);

        DefaultResponseMessaggioSIIType soapResponse = this.apn.apn0200(messaggioSII);
        this.logger.info("Chiamata APN.0200 eseguita con successo'");
    }
}
