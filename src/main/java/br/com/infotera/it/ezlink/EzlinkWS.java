/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink;

import br.com.infotera.common.ErrorException;
import br.com.infotera.common.WSIntegrador;
import br.com.infotera.common.WSPreAlterarRQ;
import br.com.infotera.common.WSPreAlterarRS;
import br.com.infotera.common.WSPreReservarRQ;
import br.com.infotera.common.WSPreReservarRS;
import br.com.infotera.common.WSReservaRelatorioRQ;
import br.com.infotera.common.WSReservaRelatorioRS;
import br.com.infotera.common.destino.rqrs.WSDestinoRQ;
import br.com.infotera.common.destino.rqrs.WSDestinoRS;
import br.com.infotera.common.hotel.HotelImpl;
import br.com.infotera.common.hotel.WSAlteraReservaRQ;
import br.com.infotera.common.hotel.WSAlteraReservaRS;
import br.com.infotera.common.hotel.rqrs.WSDetalheHotelRQ;
import br.com.infotera.common.hotel.rqrs.WSDetalheHotelRS;
import br.com.infotera.common.hotel.rqrs.WSDisponibilidadeHotelRQ;
import br.com.infotera.common.hotel.rqrs.WSDisponibilidadeHotelRS;
import br.com.infotera.common.hotel.rqrs.WSPesquisaHotelRQ;
import br.com.infotera.common.hotel.rqrs.WSPesquisaHotelRS;
import br.com.infotera.common.hotel.rqrs.WSTarifarHotelRQ;
import br.com.infotera.common.hotel.rqrs.WSTarifarHotelRS;
import br.com.infotera.common.reserva.rqrs.WSReservaRQ;
import br.com.infotera.common.reserva.rqrs.WSReservaRS;
import br.com.infotera.common.reserva.rqrs.WSReservarRQ;
import br.com.infotera.common.reserva.rqrs.WSReservarRS;

/**
 *
 * @author rafael
 */
public class EzlinkWS implements HotelImpl {

    MontaWS montaWS = new MontaWS();

    @Override
    public WSPreReservarRS preReservar(WSPreReservarRQ preReservarRQ) throws ErrorException {
        try {
            return montaWS.preReservar(preReservarRQ);
        } catch (ErrorException ex) {
            return new WSPreReservarRS(null, ex.getIntegrador());
        }
    }

    @Override
    public WSDisponibilidadeHotelRS disponibilidade(WSDisponibilidadeHotelRQ disponibilidadeRQ) throws ErrorException {
        try {
            return montaWS.disponibilidade(disponibilidadeRQ);
        } catch (ErrorException ex) {
            return new WSDisponibilidadeHotelRS(null, ex.getIntegrador());
        }
    }

    @Override
    public WSReservarRS reservar(WSReservarRQ reservarRQ) throws ErrorException {
        try {
            return montaWS.reservar(reservarRQ);
        } catch (ErrorException ex) {
            return new WSReservarRS(null, ex.getIntegrador());
        }
    }

    @Override
    public WSReservaRelatorioRS relatorio(WSReservaRelatorioRQ reservaRelatorioRQ) throws ErrorException {
        try {
            return montaWS.relatorio(reservaRelatorioRQ);
        } catch (ErrorException ex) {
            return new WSReservaRelatorioRS(null, ex.getIntegrador());
        }
    }

    @Override
    public WSReservaRS consulta(WSReservaRQ reservaRQ) throws ErrorException {
        try {
            return montaWS.consultar(reservaRQ);
        } catch (ErrorException ex) {
            return new WSReservaRS(null, ex.getIntegrador());
        }
    }

    @Override
    public WSReservaRS cancelar(WSReservaRQ cancelaReservaRQ) throws ErrorException {
        try {
            return montaWS.cancelar(cancelaReservaRQ);
        } catch (ErrorException ex) {
            return new WSReservaRS(null, ex.getIntegrador());
        }
    }

    @Override
    public WSDetalheHotelRS detalheHotel(WSDetalheHotelRQ detalheHotelRQ) throws ErrorException {
        try {
            return montaWS.detalharHotel(detalheHotelRQ);
        } catch (ErrorException ex) {
            return new WSDetalheHotelRS(null, ex.getIntegrador());
        }
    }

    @Override
    public WSPesquisaHotelRS pesquisaHotel(WSPesquisaHotelRQ pesquisaHotelRQ) throws ErrorException {
        try {
            return montaWS.pesquisarHotel(pesquisaHotelRQ);
        } catch (ErrorException ex) {
            return new WSPesquisaHotelRS(null, ex.getIntegrador());
        }
    }

    @Override
    public WSReservaRS preCancelar(WSReservaRQ preCancelaReservaRQ) throws ErrorException {
        try {
            return montaWS.preCancelar(preCancelaReservaRQ);
        } catch (ErrorException ex) {
            return new WSReservaRS(null, ex.getIntegrador());
        }
    }

    @Override
    public WSTarifarHotelRS tarifar(WSTarifarHotelRQ tarifarHotelRQ) throws ErrorException {
        try {
            return montaWS.tarifar(tarifarHotelRQ);
        } catch (ErrorException ex) {
            return new WSTarifarHotelRS(null, ex.getIntegrador());
        }
    }

    @Override
    public WSIntegrador login(WSIntegrador wsi) throws ErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WSDestinoRS listaDestino(WSDestinoRQ wsdrq) throws ErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WSReservaRS confirmar(WSReservaRQ wsrrq) throws ErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WSPreAlterarRS preAlterarReserva(WSPreAlterarRQ wsparq) throws ErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WSAlteraReservaRS alterarReserva(WSAlteraReservaRQ wsarrq) throws ErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterarPrazo(WSAlteraReservaRQ wsarrq) throws ErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
