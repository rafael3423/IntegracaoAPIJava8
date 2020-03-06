/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink;

import br.com.infotera.common.ErrorException;
import br.com.infotera.common.enumerator.WSIntegracaoStatusEnum;
import br.com.infotera.common.enumerator.WSMensagemErroEnum;
import br.com.infotera.common.politica.WSPolitica;
import br.com.infotera.common.politica.WSPoliticaCancelamento;
import br.com.infotera.common.politica.WSPoliticaVoucher;
import br.com.infotera.common.util.Utils;
import br.com.infotera.it.ezlink.model.Penalt;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rafael
 */
public class UtilsWS {

    public List<WSPolitica> montaPoliticaCancelamento(List<Penalt> penaltList, Boolean refundable, String remarks) {

        List<WSPolitica> politicaList = new ArrayList();

        if (penaltList != null) {

            for (Penalt p : penaltList) {

                Date dtMaximaCancelamento = Utils.addDias(Utils.toDate(p.getFrom(), "yyyy-MM-dd'T'HH:mm:ss"), -3);

                boolean stImediata = false; //Inicia Multa em normalmente falso

                if (new Date().compareTo(dtMaximaCancelamento) == 1) { //Compara se o dia de hoje passou a data máxima de ccanelmento
                    stImediata = true;  // entra em multa
                }

                if (refundable == false) { // checa se o conector tem prazo de cancelamento
                    stImediata = true; // entra em multa
                }

                politicaList.add(new WSPoliticaCancelamento("Cancelamento",
                        "",
                        p.getPrice().getCurrency(),
                        p.getPrice().getValue(),
                        null,
                        null,
                        stImediata,
                        dtMaximaCancelamento,
                        null,
                        !refundable));
            }

            if (remarks != null) {
                politicaList.add(new WSPoliticaVoucher("Remarks", remarks));
            }

        }

        return politicaList;
    }
}
