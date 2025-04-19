package com.cfx.setup.soap.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import it.mkt.hub.soap.schemas.v1.apr.APR;
import it.mkt.hub.soap.schemas.v1.apr.flussiapr.FlussoAPR0050Type;
import it.mkt.hub.soap.schemas.v1.apr.flussiapr.FlussoAPR0150Type;
import it.mkt.hub.soap.schemas.v1.apr.messaggiosii.AmmissibilitaRichiestaMessaggioSIIType;
import it.mkt.hub.soap.schemas.v1.apr.messaggiosii.AzioneRichiestaType;
import it.mkt.hub.soap.schemas.v1.apr.messaggiosii.EsitoAnnullamentoPraticaDatiSIIType;
import it.mkt.hub.soap.schemas.v1.apr.messaggiosii.EsitoAnnullamentoPraticaMessaggioSIIType;
import it.mkt.hub.soap.schemas.v1.apr.messaggiosii.RichiestaAnnullamentoPraticaDatiSIIType;
import it.mkt.hub.soap.schemas.v1.apr.messaggiosii.RichiestaAnnullamentoPraticaMessaggioSIIType;
import it.mkt.hub.soap.schemas.v1.apr.messaggiosii.RichiestaSIIType;
import it.mkt.hub.soap.schemas.v1.apr.strutturegenerali.AnnullamentoDatiPraticaBaseType;
import it.mkt.hub.soap.schemas.v1.apr.strutturegenerali.AnnullamentoDatiPraticaType;
import it.mkt.hub.soap.schemas.v1.apr.strutturegenerali.DatiAnnullamentoPraticaType;
import it.mkt.hub.soap.schemas.v1.apr.strutturegenerali.DatiTecniciMinimaliType;
import it.mkt.hub.soap.schemas.v1.apr.strutturegenerali.EsitoBaseType;
import it.mkt.hub.soap.schemas.v1.apr.strutturegenerali.IdentificativiRichiestaUtenteCPOpzGestoreCPOpzType;
import it.mkt.hub.soap.schemas.v1.apr.strutturegenerali.IdentificativiRichiestaUtenteCPOpzGestoreCPType;
import it.mkt.hub.soap.schemas.v1.apr.strutturegenerali.SettoreType;

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
        richiestaSIIType.setErogatore("Erogatore");
        richiestaSIIType.setFruitore("Fruitore");

        AzioneRichiestaType azioneRichiestaType = new AzioneRichiestaType();
        azioneRichiestaType.setServizio("APR");
        azioneRichiestaType.setOperazione("APR.0050");
        richiestaSIIType.setAzioneRichiesta(azioneRichiestaType);

        RichiestaAnnullamentoPraticaDatiSIIType richiestaAnnullamentoPraticaDatiSIIType =
                new RichiestaAnnullamentoPraticaDatiSIIType();

        FlussoAPR0050Type flussoAPR0050Type = new FlussoAPR0050Type();
        flussoAPR0050Type.setCODFLUSSO("COD_FLUSSO");
        flussoAPR0050Type.setCODSERVIZIO("COD_SERVIZIO");

        IdentificativiRichiestaUtenteCPOpzGestoreCPOpzType identificativiRichiestaUtenteCPOpzGestoreCPOpzType =
                new IdentificativiRichiestaUtenteCPOpzGestoreCPOpzType();
        identificativiRichiestaUtenteCPOpzGestoreCPOpzType.setCPGESTORE("CP_GESTORE");
        identificativiRichiestaUtenteCPOpzGestoreCPOpzType.setCPUTENTE("CP_UTENTE");
        identificativiRichiestaUtenteCPOpzGestoreCPOpzType.setPIVAGESTORE("PIVA_GESTORE");
        identificativiRichiestaUtenteCPOpzGestoreCPOpzType.setPIVAUTENTE("PIVA_UTENTE");
        flussoAPR0050Type
                .setIdentificativiRichiesta(identificativiRichiestaUtenteCPOpzGestoreCPOpzType);

        AnnullamentoDatiPraticaType annullamentoDatiPraticaType = new AnnullamentoDatiPraticaType();
        annullamentoDatiPraticaType.setCPGESTOREANN("CP_GESTORE_ANN");
        annullamentoDatiPraticaType.setCPUTENTEANN("CP_UTENTE_ANN");

        SettoreType settoreType = SettoreType.G;
        annullamentoDatiPraticaType.setSETTORE(settoreType);
        flussoAPR0050Type.setDatiPratica(annullamentoDatiPraticaType);

        DatiTecniciMinimaliType datiTecniciMinimaliType = new DatiTecniciMinimaliType();
        datiTecniciMinimaliType.setCODPDR("COD_PDR");
        flussoAPR0050Type.setDatiTecnici(datiTecniciMinimaliType);

        DatiAnnullamentoPraticaType datiAnnullamentoPraticaType = new DatiAnnullamentoPraticaType();
        datiAnnullamentoPraticaType.setCODCAUSALE("COD_CAUSALE");
        datiAnnullamentoPraticaType.setMOTIVAZIONE("MOTIVAZIONE");
        datiAnnullamentoPraticaType.setNOTE("NOTE");
        flussoAPR0050Type.setDatiAnnullamento(datiAnnullamentoPraticaType);

        richiestaAnnullamentoPraticaDatiSIIType.setFlusso(flussoAPR0050Type);

        messaggioSII.setRichiestaSII(richiestaSIIType);
        messaggioSII.setDatiSII(richiestaAnnullamentoPraticaDatiSIIType);

        AmmissibilitaRichiestaMessaggioSIIType soapResponse = this.apr.apr0050(messaggioSII);
        this.logger.info("Chiamata APR.0100 eseguita con successo: '{}'", soapResponse);
    }

    public void apr0150() {
        this.logger.info("Chiamata al servizio APR.0150");
        EsitoAnnullamentoPraticaMessaggioSIIType messaggioSII =
                new EsitoAnnullamentoPraticaMessaggioSIIType();

        RichiestaSIIType richiestaSIIType = new RichiestaSIIType();
        richiestaSIIType.setErogatore("Erogatore");
        richiestaSIIType.setFruitore("Fruitore");

        AzioneRichiestaType azioneRichiestaType = new AzioneRichiestaType();
        azioneRichiestaType.setOperazione("APR.0150");
        azioneRichiestaType.setServizio("APR");
        richiestaSIIType.setAzioneRichiesta(azioneRichiestaType);

        EsitoAnnullamentoPraticaDatiSIIType esitoAnnullamentoPraticaDatiSIIType =
                new EsitoAnnullamentoPraticaDatiSIIType();

        FlussoAPR0150Type flussoAPR0150Type = new FlussoAPR0150Type();
        flussoAPR0150Type.setCODFLUSSO("COD_FLUSSO");
        flussoAPR0150Type.setCODSERVIZIO("COD_SERVIZIO");
        flussoAPR0150Type.setNOTE("NOTE");

        IdentificativiRichiestaUtenteCPOpzGestoreCPType identificativiRichiestaUtenteCPOpzGestoreCPType =
                new IdentificativiRichiestaUtenteCPOpzGestoreCPType();
        identificativiRichiestaUtenteCPOpzGestoreCPType.setCPGESTORE("CP_GESTORE");
        identificativiRichiestaUtenteCPOpzGestoreCPType.setCPUTENTE("CP_UTENTE");
        identificativiRichiestaUtenteCPOpzGestoreCPType.setPIVAGESTORE("PIVA_GESTORE");
        identificativiRichiestaUtenteCPOpzGestoreCPType.setPIVAUTENTE("PIVA_UTENTE");
        flussoAPR0150Type
                .setIdentificativiRichiesta(identificativiRichiestaUtenteCPOpzGestoreCPType);

        AnnullamentoDatiPraticaBaseType annullamentoDatiPraticaBaseType =
                new AnnullamentoDatiPraticaBaseType();
        annullamentoDatiPraticaBaseType.setCPGESTOREANN("CP_GESTORE_ANN");
        flussoAPR0150Type.setDatiPratica(annullamentoDatiPraticaBaseType);

        EsitoBaseType esitoBaseType = new EsitoBaseType();
        esitoBaseType.setESITO(1);
        esitoBaseType.setCODESITO("COD_ESITO");
        esitoBaseType.setDETTAGLIOESITO("DETTAGLIO_ESITO");
        flussoAPR0150Type.setEsito(esitoBaseType);

        esitoAnnullamentoPraticaDatiSIIType.setFlusso(flussoAPR0150Type);

        messaggioSII.setRichiestaSII(richiestaSIIType);
        messaggioSII.setDatiSII(esitoAnnullamentoPraticaDatiSIIType);

        this.apr.apr0150(messaggioSII);
        this.logger.info("Chiamata APR.0150 eseguita con successo");
    }
}
