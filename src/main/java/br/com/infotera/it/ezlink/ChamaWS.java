/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink;

import br.com.infotera.common.ErrorException;
import br.com.infotera.common.WSIntegrador;
import br.com.infotera.common.enumerator.WSAmbienteEnum;
import br.com.infotera.common.enumerator.WSIntegracaoStatusEnum;
import br.com.infotera.common.enumerator.WSIntegradorLogTipoEnum;
import br.com.infotera.common.enumerator.WSMensagemErroEnum;
import br.com.infotera.common.util.Utils;
import br.com.infotera.it.ezlink.model.Erro;
import br.com.infotera.it.ezlink.model.Errors;
import br.com.infotera.it.ezlink.rqrs.BookRQ;
import br.com.infotera.it.ezlink.rqrs.BookRS;
import br.com.infotera.it.ezlink.rqrs.CancelRQ;
import br.com.infotera.it.ezlink.rqrs.CancelRS;
import br.com.infotera.it.ezlink.rqrs.DetailsRQ;
import br.com.infotera.it.ezlink.rqrs.DetailsRS;
import br.com.infotera.it.ezlink.rqrs.HotelsRQ;
import br.com.infotera.it.ezlink.rqrs.HotelsRS;
import br.com.infotera.it.ezlink.rqrs.ListRQ;
import br.com.infotera.it.ezlink.rqrs.ListRS;
import br.com.infotera.it.ezlink.rqrs.QuoteRQ;
import br.com.infotera.it.ezlink.rqrs.QuoteRS;
import br.com.infotera.it.ezlink.rqrs.SearchByDestRQ;
import br.com.infotera.it.ezlink.rqrs.SearchByDestRS;
import br.com.infotera.it.ezlink.rqrs.SearchByHotelRQ;
import br.com.infotera.it.ezlink.rqrs.SearchByHotelRS;
import com.google.gson.Gson;
import java.net.URLEncoder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 
 *
 * @author rafael
 */
public class ChamaWS {

    public <T> T chamadaPadrao(WSIntegrador integrador, Object envio, Class<T> retorno) throws ErrorException { //puxa do montaws o tipo WSIntegrador e chama de integrador, um objeto vazio do tipo que puxar com nome envio, a classe que ira retornar do tipo classe que ira puxar

        Long tempoInicio = System.currentTimeMillis();// mede o tempo que ira fazer a chamada DAS CONEXÕES
        Object objResponse = null;// cria um objeto vazio objResponse
        Response r;
        String metodo = null;
        String endpoint;
        String request = "";
        String response = "";
        String bookingId;

        Gson gson = new Gson();//cria objeto gson exportado do pom.xml da google que converte json
        try {

            if (integrador.getAmbiente().equals(WSAmbienteEnum.HOMOLOGACAO)) {  //testa se o que esta no integrador ambiente é produção ou homologação para guardar no endpoint o link desejado
                endpoint = "https://test.ezconnect.link/v1/";
            } else {
                endpoint = "";
            }
            Client client = ClientBuilder.newClient(); //conexão do server

            if (envio instanceof SearchByDestRQ) {//compara se o objeto envio tem a cara de AutorizarRq para entrar na respectiva ação de postar a autorização
                metodo = "searchByDestinationId";

                try {
                    r = (Response) client.target(endpoint + "hotel/search")// 
                            .request()
                            .header("Content-Type", "application/json")//poe no header o tipo de estrutura que vai ser postado no site
                            .header("Authorization", integrador.getDsCredencialList().get(0))//posta no header a autorização necessaria do tipo basic com o codigo gerado no encodede
                            .buildPost(Entity.entity(gson.toJson(envio), MediaType.APPLICATION_JSON))//posta no site o objeto envio convertido como JSON do tipo Application JSON
                            .invoke();

                    String ret = r.readEntity(String.class);//atribui a string ret a resposta do post
                    response = ret;
                    request = gson.toJson(envio);

                    objResponse = gson.fromJson(ret, SearchByDestRS.class);//atribui ao objresponse a conversão de json para objeto obtido no ret e diz que ele é do tipo AutorizarRs classe
                    verificaErro(integrador, objResponse, r.getStatus());

                } catch (Exception ex) {

                    throw new ErrorException(integrador, ChamaWS.class, metodo, WSMensagemErroEnum.GENMETHOD, "Erro na chamada do metodo: " + metodo, WSIntegracaoStatusEnum.NEGADO, ex);
                }

            }

            if (envio instanceof SearchByHotelRQ) {//compara se o objeto envio tem a cara de AutorizarRq para entrar na respectiva ação de postar a autorização
                metodo = "searchByHotelIds";

                try {
                    r = (Response) client.target(endpoint + "hotel/search")// 
                            .request()
                            .header("Content-Type", "application/json")//poe no header o tipo de estrutura que vai ser postado no site
                            .header("Authorization", integrador.getDsCredencialList().get(0))//posta no header a autorização necessaria do tipo basic com o codigo gerado no encodede
                            .buildPost(Entity.entity(gson.toJson(envio), MediaType.APPLICATION_JSON))//posta no site o objeto envio convertido como JSON do tipo Application JSON
                            .invoke();

                    String ret = r.readEntity(String.class);//atribui a string ret a resposta do post
                    request = gson.toJson(envio);
                    response = ret;

                    objResponse = gson.fromJson(ret, SearchByHotelRS.class);//atribui ao objresponse a conversão de json para objeto obtido no ret e diz que ele é do tipo AutorizarRs classe
                    verificaErro(integrador, objResponse, r.getStatus());

                } catch (Exception ex) {

                    throw new ErrorException(integrador, ChamaWS.class, metodo, WSMensagemErroEnum.GENMETHOD, "Erro na chamada do metodo: " + metodo, WSIntegracaoStatusEnum.NEGADO, ex);
                }

            }

            if (envio instanceof QuoteRQ) {//compara se o objeto envio tem a cara de AutorizarRq para entrar na respectiva ação de postar a autorização
                metodo = "quote";

                try {
                    r = (Response) client.target(endpoint + "hotel/quote")// 
                            .request()
                            .header("Content-Type", "application/json")//poe no header o tipo de estrutura que vai ser postado no site
                            .header("Authorization", integrador.getDsCredencialList().get(0))//posta no header a autorização necessaria do tipo basic com o codigo gerado no encodede
                            .buildPost(Entity.entity(gson.toJson(envio), MediaType.APPLICATION_JSON))//posta no site o objeto envio convertido como JSON do tipo Application JSON
                            .invoke();

                    String ret = r.readEntity(String.class);//atribui a string ret a resposta do post
                    request = gson.toJson(envio);
                    response = ret;
                    objResponse = gson.fromJson(ret, QuoteRS.class);//atribui ao objresponse a conversão de json para objeto obtido no ret e diz que ele é do tipo AutorizarRs classe
                    verificaErro(integrador, objResponse, r.getStatus());

                } catch (Exception ex) {

                    throw new ErrorException(integrador, ChamaWS.class, metodo, WSMensagemErroEnum.GENMETHOD, "Erro na chamada do metodo: " + metodo, WSIntegracaoStatusEnum.NEGADO, ex);
                }

            }

            if (envio instanceof BookRQ) {//compara se o objeto envio tem a cara de AutorizarRq para entrar na respectiva ação de postar a autorização
                metodo = "book";

                try {
                    r = (Response) client.target(endpoint + "hotel/book")// 
                            .request()
                            .header("Content-Type", "application/json")//poe no header o tipo de estrutura que vai ser postado no site
                            .header("Authorization", integrador.getDsCredencialList().get(0))//posta no header a autorização necessaria do tipo basic com o codigo gerado no encodede
                            .buildPost(Entity.entity(gson.toJson(envio), MediaType.APPLICATION_JSON))//posta no site o objeto envio convertido como JSON do tipo Application JSON
                            .invoke();

                    String ret = r.readEntity(String.class);//atribui a string ret a resposta do post
                    request = gson.toJson(envio);
                    response = ret;

                    objResponse = gson.fromJson(ret, BookRS.class);//atribui ao objresponse a conversão de json para objeto obtido no ret e diz que ele é do tipo AutorizarRs classe
                    verificaErro(integrador, objResponse, r.getStatus());

                } catch (Exception ex) {

                    throw new ErrorException(integrador, ChamaWS.class, metodo, WSMensagemErroEnum.GENMETHOD, "Erro na chamada do metodo: " + metodo, WSIntegracaoStatusEnum.INCONSISTENTE, ex);
                }

            }

            if (envio instanceof ListRQ) {//compara se o objeto envio tem a cara de AutorizarRq para entrar na respectiva ação de postar a autorização
                metodo = "list";

                try {
                    ListRQ listRQ = (ListRQ) envio;

                    r = (Response) client.target(endpoint + "hotel/list?createdFrom=" + listRQ.getCreatedFrom() + "&createdTo=" + listRQ.getCreatedTo())// 
                            .request()
                            .header("Content-Type", "application/json")//poe no header o tipo de estrutura que vai ser postado no site
                            .header("Authorization", integrador.getDsCredencialList().get(0))//posta no header a autorização necessaria do tipo basic com o codigo gerado no encodede
                            .buildGet()//posta no site o objeto envio convertido como JSON do tipo Application JSON
                            .invoke();

                    String ret = r.readEntity(String.class);//atribui a string ret a resposta do post
                    request = gson.toJson(envio);
                    response = ret;

                    objResponse = gson.fromJson(ret, ListRS.class);//atribui ao objresponse a conversão de json para objeto obtido no ret e diz que ele é do tipo AutorizarRs classe
                    verificaErro(integrador, objResponse, r.getStatus());

                } catch (Exception ex) {

                    throw new ErrorException(integrador, ChamaWS.class, metodo, WSMensagemErroEnum.GENMETHOD, "Erro na chamada do metodo: " + metodo, WSIntegracaoStatusEnum.NEGADO, ex);
                }

            }

            if (envio instanceof DetailsRQ) {//compara se o objeto envio tem a cara de AutorizarRq para entrar na respectiva ação de postar a autorização
                metodo = "details";

                try {
                    DetailsRQ detailsRQ = (DetailsRQ) envio;
                    bookingId = Integer.toString(detailsRQ.getBookingId());
                    detailsRQ.setBookingId(null);

                    r = (Response) client.target(endpoint + "hotel/details?bookingId=" + bookingId)
                            .request()
                            .header("Content-Type", "application/json")//poe no header o tipo de estrutura que vai ser postado no site
                            .header("Authorization", integrador.getDsCredencialList().get(0))//posta no header a autorização necessaria do tipo basic com o codigo gerado no encodede
                            .buildGet()//posta no site o objeto envio convertido como JSON do tipo Application JSON
                            .invoke();

                    String ret = r.readEntity(String.class);//atribui a string ret a resposta do post
                    request = gson.toJson(envio);
                    response = ret;

                    objResponse = gson.fromJson(ret, DetailsRS.class);//atribui ao objresponse a conversão de json para objeto obtido no ret e diz que ele é do tipo AutorizarRs classe
                    verificaErro(integrador, objResponse, r.getStatus());

                } catch (Exception ex) {

                    throw new ErrorException(integrador, ChamaWS.class, metodo, WSMensagemErroEnum.GENMETHOD, "Erro na chamada do metodo: " + metodo, WSIntegracaoStatusEnum.INCONSISTENTE, ex);
                }

            }

            if (envio instanceof CancelRQ) {//compara se o objeto envio tem a cara de AutorizarRq para entrar na respectiva ação de postar a autorização
                metodo = "cancel";

                try {

                    r = (Response) client.target(endpoint + "hotel/cancel")
                            .request()
                            .header("Content-Type", "application/json")//poe no header o tipo de estrutura que vai ser postado no site
                            .header("Authorization", integrador.getDsCredencialList().get(0))//posta no header a autorização necessaria do tipo basic com o codigo gerado no encodede
                            .buildPost(Entity.entity(gson.toJson(envio), MediaType.APPLICATION_JSON))//posta no site o objeto envio convertido como JSON do tipo Application JSON
                            .invoke();

                    String ret = r.readEntity(String.class);//atribui a string ret a resposta do post
                    request = gson.toJson(envio);
                    response = ret;

                    objResponse = gson.fromJson(ret, CancelRS.class);//atribui ao objresponse a conversão de json para objeto obtido no ret e diz que ele é do tipo AutorizarRs classe
                    verificaErro(integrador, objResponse, r.getStatus());

                } catch (Exception ex) {
                    throw new ErrorException(integrador, ChamaWS.class, metodo, WSMensagemErroEnum.GENMETHOD, "Erro na chamada do metodo: " + metodo, WSIntegracaoStatusEnum.NEGADO, ex);
                }

            }

            if (envio instanceof HotelsRQ) {//compara se o objeto envio tem a cara de AutorizarRq para entrar na respectiva ação de postar a autorização
                metodo = "hotels";
                try {
                    HotelsRQ hotelsRQ = (HotelsRQ) envio;

                    r = (Response) client.target(endpoint + "static/hotels?hotelIds=" + URLEncoder.encode("[\"" + hotelsRQ.getHotelid() + "\"]", "UTF-8"))
                            .request()
                            .header("Content-Type", "application/json")//poe no header o tipo de estrutura que vai ser postado no site
                            .header("Authorization", integrador.getDsCredencialList().get(0))//posta no header a autorização necessaria do tipo basic com o codigo gerado no encodede
                            .buildGet()//posta no site o objeto envio convertido como JSON do tipo Application JSON
                            .invoke();

                    String ret = r.readEntity(String.class);//atribui a string ret a resposta do post
                    request = gson.toJson(envio);
                    response = ret;

                    objResponse = gson.fromJson(ret, HotelsRS.class);//atribui ao objresponse a conversão de json para objeto obtido no ret e diz que ele é do tipo AutorizarRs classe
                    verificaErro(integrador, objResponse, r.getStatus());

                } catch (Exception ex) {
                    throw new ErrorException(integrador, ChamaWS.class, metodo, WSMensagemErroEnum.GENMETHOD, "Erro na chamada do metodo: " + metodo, WSIntegracaoStatusEnum.NEGADO, ex);
                }

            }
        } finally {

//            System.out.println("REQUEST--" + RQ + "-->" + request.toString()); // Printa o request que recebe do info e envia para o conector
//            System.out.println("RESPONSE --" + RS + "-->" + response.toString());// Printa o response que vem do conecotr e envia para o info
            integrador.setIntegradorLogList(Utils.adicionaIntegradorLog(integrador,
                    WSIntegradorLogTipoEnum.JSON,
                    metodo,
                    request,
                    response.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", ""),
                    Utils.tempoExecucaoSeg(tempoInicio)));
        }

        return retorno.cast(objResponse);
    }

    public void verificaErro(WSIntegrador integrador, Object objeto, int status) throws ErrorException {
        {

            Erro erro = (Erro) objeto;
            String msgerro = null;

            if (status != 200) {
                for (Errors errors : erro.getErrors()) {

                    for (String message : errors.getMessages()) {

                        if (msgerro == null) {
                            msgerro = message;
                        } else {
                            msgerro = msgerro + message;
                        }

                    }
                }
                throw new ErrorException(integrador, ChamaWS.class, "verificaErro", WSMensagemErroEnum.GENMETHOD, "Erro no conector: " + status + " - " + msgerro, WSIntegracaoStatusEnum.INCONSISTENTE, null);

            }
        }

    }
}
