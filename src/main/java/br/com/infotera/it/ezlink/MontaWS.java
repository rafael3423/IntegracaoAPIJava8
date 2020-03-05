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
import br.com.infotera.it.ezlink.monta.CancelaReservaWS;
import br.com.infotera.it.ezlink.monta.ConsultaReservaWS;
import br.com.infotera.it.ezlink.monta.DetalheHotelWS;
import br.com.infotera.it.ezlink.monta.DisponibilidadeWS;
import br.com.infotera.it.ezlink.monta.PesquisaHotelWS;
import br.com.infotera.it.ezlink.monta.PreCancelaReservaWS;
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

    public WSReservaRS consulta(WSReservaRQ consultaReservaRQ) throws ErrorException {
        ConsultaReservaWS consultaReservaWS = new ConsultaReservaWS();
        return consultaReservaWS.consulta(consultaReservaRQ, false);
    }

    public WSReservaRS cancelar(WSReservaRQ cancelaReservaRQ) throws ErrorException {
        CancelaReservaWS cancelaReservaWS = new CancelaReservaWS();
        return cancelaReservaWS.cancela(cancelaReservaRQ);
    }

    public WSPesquisaHotelRS pesquisaHotel(WSPesquisaHotelRQ pesquisaHotelRQ) throws ErrorException {
        PesquisaHotelWS pesquisaHotelWS = new PesquisaHotelWS();
        return pesquisaHotelWS.pesquisaHotel(pesquisaHotelRQ);
    }

    public WSDetalheHotelRS detalheHotel(WSDetalheHotelRQ detalheHotelRQ) throws ErrorException {
        DetalheHotelWS detalheHotelWS = new DetalheHotelWS();
        return detalheHotelWS.detalheHotel(detalheHotelRQ);
    }

    public WSReservaRS preCancelar(WSReservaRQ preCancelaReservaRQ) throws ErrorException {
        PreCancelaReservaWS preCancelaReservaWS = new PreCancelaReservaWS();
        return preCancelaReservaWS.preCancelaReserva(preCancelaReservaRQ);
    }

    public WSTarifarHotelRS tarifar(WSTarifarHotelRQ tarifarHotelRQ) throws ErrorException {
        TarifarWS tarifarWS = new TarifarWS();
        return tarifarWS.tarifarHotel(tarifarHotelRQ);
    }

    public WSIntegrador login(WSIntegrador wsi) throws ErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public WSDestinoRS listaDestino(WSDestinoRQ wsdrq) throws ErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public WSReservaRS confirmar(WSReservaRQ wsrrq) throws ErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public WSPreAlterarRS preAlterarReserva(WSPreAlterarRQ wsparq) throws ErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public WSAlteraReservaRS alterarReserva(WSAlteraReservaRQ wsarrq) throws ErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void alterarPrazo(WSAlteraReservaRQ wsarrq) throws ErrorException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
