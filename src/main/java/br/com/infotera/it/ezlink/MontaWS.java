/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink;

import br.com.infotera.common.ErrorException;
import br.com.infotera.common.WSPreReservarRQ;
import br.com.infotera.common.WSPreReservarRS;
import br.com.infotera.common.WSReservaRelatorioRQ;
import br.com.infotera.common.WSReservaRelatorioRS;
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
import br.com.infotera.it.ezlink.monta.CancelarReservaWS;
import br.com.infotera.it.ezlink.monta.ConsultarReservaWS;
import br.com.infotera.it.ezlink.monta.DetalharHotelWS;
import br.com.infotera.it.ezlink.monta.DisponibilidadeWS;
import br.com.infotera.it.ezlink.monta.PesquisarHotelWS;
import br.com.infotera.it.ezlink.monta.PreCancelarReservaWS;
import br.com.infotera.it.ezlink.monta.PreReservarWS;
import br.com.infotera.it.ezlink.monta.ReservaRelatorioWS;
import br.com.infotera.it.ezlink.monta.ReservarWS;
import br.com.infotera.it.ezlink.monta.TarifarWS;

/**
 *
 * @author rafael
 */
public class MontaWS {

    public WSDisponibilidadeHotelRS disponibilidade(WSDisponibilidadeHotelRQ disponibilidadeRQ) throws ErrorException {
        DisponibilidadeWS disponibilidadeWS = new DisponibilidadeWS();
        return disponibilidadeWS.disponibilidade(disponibilidadeRQ);
    }

    public WSPreReservarRS preReservar(WSPreReservarRQ preReservarRQ) throws ErrorException {
        PreReservarWS preReservarWS = new PreReservarWS();
        return preReservarWS.preReservar(preReservarRQ);
    }

    public WSReservarRS reservar(WSReservarRQ reservarRQ) throws ErrorException {
        ReservarWS reservarWS = new ReservarWS();
        return reservarWS.reservar(reservarRQ);
    }

    public WSReservaRelatorioRS relatorio(WSReservaRelatorioRQ reservaRelatorioRQ) throws ErrorException {
        ReservaRelatorioWS reservaRelatorioWS = new ReservaRelatorioWS();
        return reservaRelatorioWS.relatorio(reservaRelatorioRQ);
    }

    public WSReservaRS consultar(WSReservaRQ consultaReservaRQ) throws ErrorException {
        ConsultarReservaWS consultarReservaWS = new ConsultarReservaWS();
        return consultarReservaWS.consultar(consultaReservaRQ, false);
    }

    public WSReservaRS cancelar(WSReservaRQ cancelaReservaRQ) throws ErrorException {
        CancelarReservaWS cancelarReservaWS = new CancelarReservaWS();
        return cancelarReservaWS.cancelar(cancelaReservaRQ);
    }

    public WSPesquisaHotelRS pesquisarHotel(WSPesquisaHotelRQ pesquisaHotelRQ) throws ErrorException {
        PesquisarHotelWS pesquisarHotelWS = new PesquisarHotelWS();
        return pesquisarHotelWS.pesquisarHotel(pesquisaHotelRQ);
    }

    public WSDetalheHotelRS detalharHotel(WSDetalheHotelRQ detalheHotelRQ) throws ErrorException {
        DetalharHotelWS detalharHotelWS = new DetalharHotelWS();
        return detalharHotelWS.detalharHotel(detalheHotelRQ);
    }

    public WSReservaRS preCancelar(WSReservaRQ preCancelarReservaRQ) throws ErrorException {
        PreCancelarReservaWS preCancelarReservaWS = new PreCancelarReservaWS();
        return preCancelarReservaWS.preCancelarReserva(preCancelarReservaRQ);
    }

    public WSTarifarHotelRS tarifar(WSTarifarHotelRQ tarifarHotelRQ) throws ErrorException {
        TarifarWS tarifarWS = new TarifarWS();
        return tarifarWS.tarifarHotel(tarifarHotelRQ);
    }

}
