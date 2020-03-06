/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink.monta;

import br.com.infotera.common.ErrorException;
import br.com.infotera.common.WSEndereco;
import br.com.infotera.common.WSReservaNome;
import br.com.infotera.common.WSTarifa;
import br.com.infotera.common.enumerator.WSIntegracaoStatusEnum;
import br.com.infotera.common.enumerator.WSMensagemErroEnum;
import br.com.infotera.common.hotel.WSConfigUh;
import br.com.infotera.common.hotel.WSHotel;
import br.com.infotera.common.hotel.WSHotelCategoria;
import br.com.infotera.common.hotel.WSHotelPesquisa;
import br.com.infotera.common.hotel.WSQuarto;
import br.com.infotera.common.hotel.WSQuartoUh;
import br.com.infotera.common.hotel.WSRegime;
import br.com.infotera.common.hotel.WSUh;
import br.com.infotera.common.hotel.rqrs.WSDisponibilidadeHotelRQ;
import br.com.infotera.common.hotel.rqrs.WSDisponibilidadeHotelRS;
import br.com.infotera.common.politica.WSPolitica;
import br.com.infotera.common.util.Utils;
import br.com.infotera.it.ezlink.ChamaWS;
import br.com.infotera.it.ezlink.UtilsWS;
import br.com.infotera.it.ezlink.model.Room;
import br.com.infotera.it.ezlink.model.SearchResults;
import br.com.infotera.it.ezlink.rqrs.SearchByHotelRQ;
import br.com.infotera.it.ezlink.rqrs.SearchByHotelRS;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rafael
 */
public class DisponibilidadeWS {

    ChamaWS chamaWS = new ChamaWS();

    /**
     *
     * @param disponibilidadeRQ
     * @return WSDisponibilidadeRS
     * @throws ErrorException
     * <br>
     * este metodo faz a disponibilidade
     */
    public WSDisponibilidadeHotelRS disponibilidade(WSDisponibilidadeHotelRQ disponibilidadeRQ) throws ErrorException {

        String metodo = "disponibilidade";
        List<String> hotelIdsList = new ArrayList();
        List<Room> roomList = new ArrayList();
        try {

            for (WSHotel hid : disponibilidadeRQ.getHotelList()) { // Abre a lista de hotel enviado pelo dispRQ e vai colocando no hotelIdsList
                hotelIdsList.add(hid.getIdExterno());
            }
        } catch (Exception ex) {
            throw new ErrorException(disponibilidadeRQ.getIntegrador(), DisponibilidadeWS.class, "disponibilidade", WSMensagemErroEnum.HDI, "Ocorreu uma falha ao consultar os hóteis disponiveis", WSIntegracaoStatusEnum.NEGADO, ex);
        }

        Map<Integer, WSConfigUh> configUhMap = new LinkedHashMap(); // Configura o Map novo com a chave de int, do tipo WSConfigUh

        int sqConfigUh = 0;

        /**
         * Abre a lista de Config UhList enviado pelo dispRQ e vai adicionando
         * na roomList
         */
        try {

            for (WSConfigUh cuh : disponibilidadeRQ.getConfigUhList()) { //abre a lista de quarto e vai rodando no cuh do tipo WSConfigUh

                sqConfigUh++;
                configUhMap.put(sqConfigUh, cuh);

                List<Integer> idadeCriancaList = new ArrayList();

                int qtADT = 0;
                Integer qtCHD = 0;

                for (WSReservaNome rn : cuh.getReservaNomeList()) { //abre a lista de nome e vai rodando no rn do tipo WSReservaNome
                    if (rn.getPaxTipo().isAdt() || rn.getPaxTipo().isSrn()) { // checha se o hospede é adulto ou senhor
                        qtADT++; // adiciona como adulto
                    } else {// checha se o hospede é criança
                        if (rn.getQtIdade() < 12) {
                            qtCHD++; //Adiciona criança
                            idadeCriancaList.add(rn.getQtIdade());
                        } //define a idade da criança
                        else {
                            throw new ErrorException(disponibilidadeRQ.getIntegrador(), DisponibilidadeWS.class, metodo, WSMensagemErroEnum.GENMETHOD, "Idade máxima para crianças: 11 anos", WSIntegracaoStatusEnum.NEGADO, null);
                        }
                    }

                }

                if (qtCHD == 0) {//checa se a quatidade de criança é 0
                    qtCHD = null;//define como nulo 
                    idadeCriancaList = null;// define como nulo
                }

                roomList.add(new Room(qtADT, qtCHD, idadeCriancaList));
            }
        } catch (Exception ex) {
            throw new ErrorException(disponibilidadeRQ.getIntegrador(), DisponibilidadeWS.class, "disponibilidade", WSMensagemErroEnum.HDI, "Ocorreu uma falha ao consultar os hóteis disponiveis", WSIntegracaoStatusEnum.NEGADO, ex);
        }

        String dtcheckIn = Utils.formatData(disponibilidadeRQ.getDtEntrada(), "yyyy-MM-dd"); // Converte para string a data check in fornecida pelo infotravel
        String dtcheckOut = Utils.formatData(disponibilidadeRQ.getDtSaida(), "yyyy-MM-dd"); // Converte para string a data check out fornecida pelo infotravel

        SearchByHotelRQ searchByHotelRQ = new SearchByHotelRQ( //monta o searchByHotelRQ com a informações que vem do dispRQ
                dtcheckIn,
                dtcheckOut,
                hotelIdsList,
                "BR",
                40000,
                true,
                roomList);

        SearchByHotelRS searchByHotelRS = chamaWS.chamadaPadrao(disponibilidadeRQ.getIntegrador(), searchByHotelRQ, SearchByHotelRS.class);

        List<WSHotelPesquisa> hotelPesquisaList = new ArrayList();
        int sqPesquisa = 0;
        /**
         * Abre a Lista de SearchResults para preencher WSHotelPesquisa
         */

        if (searchByHotelRS != null && searchByHotelRS.getSearchResponse() != null && searchByHotelRS.getSearchResponse().getSearchResults() != null) {
            try {
                for (SearchResults sr : searchByHotelRS.getSearchResponse().getSearchResults()) {//Abre a lista de SearchResults e roda no sr  

                    sqPesquisa++;

                    List<WSQuarto> quartoList = new ArrayList();
                    int sqQuarto = 0;

                    /**
                     * Abre a lista de rooms para preencher quartoList
                     */
                    try {

                        for (List<Room> rList : sr.getRooms()) {

                            sqQuarto++;

                            List<WSQuartoUh> quartoUhList = new ArrayList();

                            /**
                             * Abre a lista na lista rooms para preencher
                             * QuartoUhList
                             */
                            try {

                                for (Room r : rList) {

                                    UtilsWS utils = new UtilsWS();

                                    List<WSPolitica> politicaCancelamentoList = new ArrayList();

                                    if (r.getCancellationPolicies() != null) {
                                        politicaCancelamentoList = utils.montaPoliticaCancelamento(r.getCancellationPolicies().getPenalties(),
                                                r.getRefundable(),
                                                r.getRemarks());
                                    }

                                    /**
                                     * Seta tarifa com a moeda, preço e a lista
                                     * de polica de cancelamento
                                     */
                                    WSTarifa tarifa = new WSTarifa(r.getPrice().getCurrency(),
                                            r.getPrice().getValue(),
                                            null,
                                            null,
                                            null,
                                            politicaCancelamentoList);

                                    /**
                                     * Seta QuartoUhList com WSQuartoUh
                                     */
                                    quartoUhList.add(new WSQuartoUh( // Seta WSQuatoUh com WSUh, WSRegime e WSTarifa
                                            new WSUh(null,
                                                    r.getRoomId(),
                                                    r.getRoomName(),
                                                    r.getRoomName(),
                                                    r.getRoomId() + "#" + r.getRefundable()),
                                            new WSRegime(r.getBoard().getBoardCode().toString(),
                                                    r.getBoard().getBoardCode().toString(),
                                                    r.getBoard().getBoardName()),
                                            tarifa,
                                            null));

                                }
                            } catch (Exception ex) {
                                throw new ErrorException(disponibilidadeRQ.getIntegrador(), DisponibilidadeWS.class, "disponibilidade", WSMensagemErroEnum.HDI, "Ocorreu uma falha ao consultar os hóteis disponiveis", WSIntegracaoStatusEnum.NEGADO, ex);
                            }

                            quartoList.add(new WSQuarto(sqQuarto, configUhMap.get(sqQuarto), quartoUhList)); //Poe dentro QuartoList o numero do WSQuarto, a ordem do WSQuarto, o QuartoUhList com WSQuartoUhs
                        }
                    } catch (Exception ex) {
                        throw new ErrorException(disponibilidadeRQ.getIntegrador(), DisponibilidadeWS.class, "disponibilidade", WSMensagemErroEnum.HDI, "Ocorreu uma falha ao consultar os hóteis disponiveis", WSIntegracaoStatusEnum.NEGADO, ex);
                    }

                    /**
                     * Adiciona à lista hotelPesquisaList todos componentes do
                     * WSHotelPesquisa
                     */
                    hotelPesquisaList.add(new WSHotelPesquisa(null,
                            Utils.toDate(searchByHotelRS.getSearchResponse().getSearchQuery().getCheckIn(), "yyyy-MM-dd"),
                            Utils.toDate(searchByHotelRS.getSearchResponse().getSearchQuery().getCheckOut(), "yyyy-MM-dd"),
                            new WSHotel(sr.getHotelId(),
                                    sr.getHotelName(),
                                    null,
                                    new WSHotelCategoria(sr.getCategory(), null),
                                    null,
                                    null,
                                    new WSEndereco(sr.getLocalization().getAddress(), null, sr.getLocalization().getDestination(), null, sr.getLocalization().getCountryISO2(), "", sr.getLocalization().getCoordinates().get(0).toString(), sr.getLocalization().getCoordinates().get(1).toString()),
                                    null),
                            quartoList,
                            sqPesquisa,
                            searchByHotelRS.getSearchResponse().getSearchToken()));

                }
            } catch (Exception ex) {
                throw new ErrorException(disponibilidadeRQ.getIntegrador(), DisponibilidadeWS.class, "disponibilidade", WSMensagemErroEnum.HDI, "Ocorreu uma falha ao consultar os hóteis disponiveis", WSIntegracaoStatusEnum.NEGADO, ex);
            }

            /**
             * Retorna para o infortravel a Lista de pesquisas de hotel, o
             * integrado que foi enviado, e o status de integração como oK
             */
        }
        return new WSDisponibilidadeHotelRS(hotelPesquisaList, disponibilidadeRQ.getIntegrador(), WSIntegracaoStatusEnum.OK);
    }
}
