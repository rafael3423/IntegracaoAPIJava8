/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink.monta;

import br.com.infotera.common.ErrorException;
import br.com.infotera.common.WSReserva;
import br.com.infotera.common.enumerator.WSIntegracaoStatusEnum;
import br.com.infotera.common.enumerator.WSMensagemErroEnum;
import br.com.infotera.common.enumerator.WSReservaStatusEnum;
import br.com.infotera.common.reserva.rqrs.WSReservaRQ;
import br.com.infotera.common.reserva.rqrs.WSReservaRS;
import br.com.infotera.it.ezlink.ChamaWS;
import br.com.infotera.it.ezlink.UtilsWS;
import br.com.infotera.it.ezlink.rqrs.CancelRQ;
import br.com.infotera.it.ezlink.rqrs.CancelRS;

/**
 *
 * @author rafael
 */
public class CancelaReservaWS {

    ConsultaReservaWS consultaReservaWS = new ConsultaReservaWS();
    UtilsWS utilsWS = new UtilsWS();
    ChamaWS chamaWS = new ChamaWS();

    public WSReservaRS cancela(WSReservaRQ cancelaReservaRQ) throws ErrorException {
        try {

            WSReservaRS consultaCancelamento = consultaReservaWS.consulta(cancelaReservaRQ, true);

            if (consultaCancelamento.getReserva().getReservaHotel().getReservaStatus().equals(WSReservaStatusEnum.CONFIRMADO)) {
                CancelRQ cancelRQ = new CancelRQ(Integer.parseInt(cancelaReservaRQ.getReserva().getReservaHotel().getNrLocalizador()),
                        null,
                        true);

                CancelRS cancelRS = chamaWS.chamadaPadrao(cancelaReservaRQ.getIntegrador(), cancelRQ, CancelRS.class);

                WSReserva reserva = utilsWS.montaReserva(cancelaReservaRQ.getIntegrador(), cancelRS.getCancelledBooking(), true);

                return new WSReservaRS(reserva, cancelaReservaRQ.getIntegrador(), WSIntegracaoStatusEnum.OK);
            } else {
                return consultaCancelamento;
            }
        } catch (Exception ex) {
            throw new ErrorException(cancelaReservaRQ.getIntegrador(), CancelaReservaWS.class, "cancela", WSMensagemErroEnum.HCO, "Ocorreu uma falha ao efetuar o cancelamento da reserva", WSIntegracaoStatusEnum.NEGADO, ex);
        }
    }
}
