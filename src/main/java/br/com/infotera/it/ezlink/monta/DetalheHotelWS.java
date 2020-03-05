/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink.monta;

import br.com.infotera.common.ErrorException;
import br.com.infotera.common.WSEndereco;
import br.com.infotera.common.enumerator.WSIntegracaoStatusEnum;
import br.com.infotera.common.enumerator.WSMensagemErroEnum;
import br.com.infotera.common.hotel.WSFacilidade;
import br.com.infotera.common.hotel.WSFacilidadeItem;
import br.com.infotera.common.hotel.WSHotel;
import br.com.infotera.common.hotel.rqrs.WSDetalheHotelRQ;
import br.com.infotera.common.hotel.rqrs.WSDetalheHotelRS;
import br.com.infotera.it.ezlink.ChamaWS;
import br.com.infotera.it.ezlink.model.Amenities;
import br.com.infotera.it.ezlink.model.Description;
import br.com.infotera.it.ezlink.rqrs.HotelsRQ;
import br.com.infotera.it.ezlink.rqrs.HotelsRS;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class DetalheHotelWS {

    ChamaWS chamaWS = new ChamaWS();

    public WSDetalheHotelRS detalheHotel(WSDetalheHotelRQ detalheHotelRQ) throws ErrorException {

        HotelsRQ hotelsRQ = new HotelsRQ(detalheHotelRQ.getHotel().getIdExterno());

        HotelsRS hotelsRS = chamaWS.chamadaPadrao(detalheHotelRQ.getIntegrador(), hotelsRQ, HotelsRS.class);
        
        WSEndereco endereco = new WSEndereco(hotelsRS.getHotelListResponse().getHotelListResults().getHotels().get(0).getLocalization().getAddress(),
                "",
                "",
                "",
                hotelsRS.getHotelListResponse().getHotelListResults().getHotels().get(0).getCountryISO2(),
                "",
                hotelsRS.getHotelListResponse().getHotelListResults().getHotels().get(0).getLocalization().getCoordinates().get(0).toString(),
                hotelsRS.getHotelListResponse().getHotelListResults().getHotels().get(0).getLocalization().getCoordinates().get(1).toString());

        String dsHotel = new String();
        try {

            for (Description ds : hotelsRS.getHotelListResponse().getHotelListResults().getHotels().get(0).getDescription()) {

                if (ds.getLanguage().equals("pt_br")) {
                    dsHotel = ds.getText();
                } else {
                    dsHotel = null;
                }

            }
        } catch (Exception ex) {
            throw new ErrorException(detalheHotelRQ.getIntegrador(), DetalheHotelWS.class, "detalheHotel", WSMensagemErroEnum.HPH, "Ocorreu uma falha ao pesquisar o hotel", WSIntegracaoStatusEnum.NEGADO, ex);
        }

        List<WSFacilidade> facilidadeList = new ArrayList();

        try {

            for (Amenities a : hotelsRS.getHotelListResponse().getHotelListResults().getHotels().get(0).getAmenities()) {

                List<WSFacilidadeItem> facilidadeItemList = new ArrayList();

                facilidadeItemList.add(new WSFacilidadeItem(null,
                        a.getText(),
                        a.getText(),
                        null,
                        null,
                        null,
                        null));

                facilidadeList.add(new WSFacilidade(null,
                        null,
                        facilidadeItemList,
                        null));
            }
        } catch (Exception ex) {
            throw new ErrorException(detalheHotelRQ.getIntegrador(), DetalheHotelWS.class, "detalheHotel", WSMensagemErroEnum.HPH, "Ocorreu uma falha ao pesquisar o hotel", WSIntegracaoStatusEnum.NEGADO, ex);
        }

        WSHotel hotel = new WSHotel(hotelsRS.getHotelListResponse().getHotelListResults().getHotels().get(0).getName(),
                dsHotel,
                endereco,
                hotelsRS.getHotelListResponse().getHotelListResults().getHotels().get(0).getId(),
                null,
                null,
                null,
                null,
                null,
                hotelsRS.getHotelListResponse().getHotelListResults().getHotels().get(0).getCategory(),
                null,
                facilidadeList);

        return new WSDetalheHotelRS(hotel, detalheHotelRQ.getIntegrador(), WSIntegracaoStatusEnum.OK);

    }
}
