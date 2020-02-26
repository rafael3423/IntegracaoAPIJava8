/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink.ms;

import br.com.infotera.common.WSIntegrador;
import br.com.infotera.common.WSReservaNome;
import br.com.infotera.common.aereo.WSCiaAerea;
import br.com.infotera.common.aereo.WSFiltro;
import br.com.infotera.common.aereo.WSVooTrajeto;
import br.com.infotera.common.aereo.rqrs.WSDisponibilidadeAereoIntegradorRQ;
import br.com.infotera.common.aereo.rqrs.WSDisponibilidadeAereoIntegradorRS;
import br.com.infotera.common.enumerator.WSAmbienteEnum;
import br.com.infotera.common.enumerator.WSIntegradorEnum;
import br.com.infotera.common.enumerator.WSPaxTipoEnum;
import br.com.infotera.common.enumerator.WSPeriodoHoraEnum;
import br.com.infotera.common.enumerator.WSSexoEnum;
import br.com.infotera.common.util.Utils;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author arquimedes
 */
public class teste {

    public static void main(String[] args) {
        Gson gson = new Gson();

        List<String> dsCredencialList = new ArrayList();

//orinter
        dsCredencialList.add("57521284WEB");
        dsCredencialList.add("10152030");
        dsCredencialList.add("57521284");

        Utils.geraArquivoXml = false;

        WSIntegrador integrador = new WSIntegrador(1,
                "Azul",
                WSIntegradorEnum.AZUL,
                "BRL",
                30,
                WSAmbienteEnum.PRODUCAO,
                dsCredencialList);

        Date dtIda = Utils.addDias(new Date(), 100);
        Date dtVolta = Utils.addDias(new Date(), 110);

        List<WSVooTrajeto> trajetoList = new ArrayList();
        trajetoList.add(new WSVooTrajeto("SAO", "RIO", dtIda, false, WSPeriodoHoraEnum.TODOS));
        trajetoList.add(new WSVooTrajeto("RIO", "SAO", dtVolta, false, WSPeriodoHoraEnum.TODOS));

        List<WSReservaNome> reservaNomeList = new ArrayList();
        reservaNomeList.add(new WSReservaNome("JOAO", "SILVA", WSPaxTipoEnum.ADT, Utils.toDate("01/05/1990"), WSSexoEnum.MASCULINO, null, null));
        reservaNomeList.add(new WSReservaNome("JOSE", "SILVA", WSPaxTipoEnum.ADT, Utils.toDate("01/05/1990"), WSSexoEnum.MASCULINO, null, null));

        List<WSCiaAerea> ciaAereaList = new ArrayList();
        ciaAereaList.add(new WSCiaAerea("AD", "AZUL", "LAZER"));

        WSFiltro filtro = new WSFiltro(null, 10, true, true, null, null);
        WSDisponibilidadeAereoIntegradorRQ rq = new WSDisponibilidadeAereoIntegradorRQ(integrador,
                ciaAereaList,
                reservaNomeList,
                trajetoList,
                gson.toJson(filtro));
        System.out.println("inicio1 " + new Date().toInstant());
        System.out.println(new WS().disponibilidade(gson.toJson(rq)));
        System.out.println("inicio2" + new Date().toInstant());
        System.out.println(new WS().disponibilidade(gson.toJson(rq)));
        System.out.println("fim1" + new Date().toInstant());
    }

}
