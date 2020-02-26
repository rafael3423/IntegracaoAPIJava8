/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink.ms;

import br.com.infotera.common.ErrorException;
import br.com.infotera.common.WSPreAlterarRQ;
import br.com.infotera.common.WSPreAlterarRS;
import br.com.infotera.common.WSPreReservarRQ;
import br.com.infotera.common.WSPreReservarRS;
import br.com.infotera.common.WSReservaRelatorioRQ;
import br.com.infotera.common.WSReservaRelatorioRS;
import br.com.infotera.common.enumerator.WSIntegracaoStatusEnum;
import br.com.infotera.common.enumerator.WSMensagemErroEnum;
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
import br.com.infotera.common.util.Utils;
import br.com.infotera.it.ezlink.EzlinkWS;
import com.google.gson.Gson;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author arquimedes
 */
@Path("/")
public class WS {

    Gson gson = Utils.getGson();
    EzlinkWS ezlinkWS = new EzlinkWS();
    private static Logger logger;

    static {
        try {
            logger = Logger.getLogger(WS.class.getName());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    @Path("ola")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response helloWorld() {
        return Response.ok("Hello World EZLINK").build();
    }

    @Path("disponibilidade")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response disponibilidade(String jsonRQ) {
        WSDisponibilidadeHotelRS wsRS = null;
        WSDisponibilidadeHotelRQ wsRQ = gson.fromJson(jsonRQ, WSDisponibilidadeHotelRQ.class);
        try {
            wsRS = ezlinkWS.disponibilidade(wsRQ);
        } catch (ErrorException ex) {
            wsRS = new WSDisponibilidadeHotelRS(null, ex.getIntegrador());
        } catch (Exception ex) {
            wsRS = new WSDisponibilidadeHotelRS(null, new ErrorException(wsRQ.getIntegrador(), WS.class, "disponibilidade", WSMensagemErroEnum.GENNULO, "", WSIntegracaoStatusEnum.NEGADO, ex).getIntegrador());
        }
        Response result = Response.ok(gson.toJson(wsRS)).build();
        return result;
    }

    @Path("tarifar")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response tarifar(String jsonRQ) {
        WSTarifarHotelRS wsRS = null;
        WSTarifarHotelRQ wsRQ = gson.fromJson(jsonRQ, WSTarifarHotelRQ.class);
        try {
            wsRS = ezlinkWS.tarifar(wsRQ);
        } catch (ErrorException ex) {
            wsRS = new WSTarifarHotelRS(null, ex.getIntegrador());
        } catch (Exception ex) {
            wsRS = new WSTarifarHotelRS(null, new ErrorException(wsRQ.getIntegrador(), WS.class, "tarifar", WSMensagemErroEnum.GENNULO, "", WSIntegracaoStatusEnum.INCONSISTENTE, ex).getIntegrador());
        }
        Utils.gerarLog(wsRS.getIntegrador(), "tarifar", false);
        Response result = Response.ok(gson.toJson(wsRS)).build();
        return result;
    }

    @Path("preReservar")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response preReservar(String jsonRQ) {
        WSPreReservarRS wsRS = null;
        WSPreReservarRQ wsRQ = gson.fromJson(jsonRQ, WSPreReservarRQ.class);
        try {
            wsRS = ezlinkWS.preReservar(wsRQ);
        } catch (ErrorException ex) {
            wsRS = new WSPreReservarRS(null, ex.getIntegrador());
        } catch (Exception ex) {
            wsRS = new WSPreReservarRS(null, new ErrorException(wsRQ.getIntegrador(), WS.class, "preReservar", WSMensagemErroEnum.GENNULO, "", WSIntegracaoStatusEnum.NEGADO, ex).getIntegrador());
        }
        Utils.gerarLog(wsRS.getIntegrador(), "preReservar", true);
        Response result = Response.ok(gson.toJson(wsRS)).build();
        return result;
    }

    @Path("reservar")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response reservar(String jsonRQ) {
        WSReservarRS wsRS = null;
        WSReservarRQ wsRQ = gson.fromJson(jsonRQ, WSReservarRQ.class);
        try {
            wsRS = ezlinkWS.reservar(wsRQ);
        } catch (ErrorException ex) {
            wsRS = new WSReservarRS(null, ex.getIntegrador());
        } catch (Exception ex) {
            wsRS = new WSReservarRS(null, new ErrorException(wsRQ.getIntegrador(), WS.class, "reservar", WSMensagemErroEnum.GENNULO, "", WSIntegracaoStatusEnum.INCONSISTENTE, ex).getIntegrador());
        }
        Utils.gerarLog(wsRS.getIntegrador(), "reservar", true);
        Response result = Response.ok(gson.toJson(wsRS)).build();
        return result;
    }

    @Path("confirmar")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response confirmar(String jsonRQ) {
        WSReservaRS wsRS = null;
        WSReservaRQ wsRQ = gson.fromJson(jsonRQ, WSReservaRQ.class);
        try {
            wsRS = ezlinkWS.confirmar(wsRQ);
        } catch (ErrorException ex) {
            wsRS = new WSReservaRS(null, ex.getIntegrador());
        } catch (Exception ex) {
            wsRS = new WSReservaRS(null, new ErrorException(wsRQ.getIntegrador(), WS.class, "confirmar", WSMensagemErroEnum.GENNULO, "", WSIntegracaoStatusEnum.INCONSISTENTE, ex).getIntegrador());
        }
        Utils.gerarLog(wsRS.getIntegrador(), "confirmar", true);
        Response result = Response.ok(gson.toJson(wsRS)).build();
        return result;
    }

    @Path("consultar")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response consulta(String jsonRQ) {
        WSReservaRS wsRS = null;
        WSReservaRQ wsRQ = gson.fromJson(jsonRQ, WSReservaRQ.class);
        try {
            wsRS = ezlinkWS.consulta(wsRQ);
        } catch (ErrorException ex) {
            wsRS = new WSReservaRS(null, ex.getIntegrador());
        } catch (Exception ex) {
            wsRS = new WSReservaRS(null, new ErrorException(wsRQ.getIntegrador(), WS.class, "consultar", WSMensagemErroEnum.GENNULO, "", WSIntegracaoStatusEnum.INCONSISTENTE, ex).getIntegrador());
        }
        Utils.gerarLog(wsRS.getIntegrador(), "consultar", true);
        Response result = Response.ok(gson.toJson(wsRS)).build();
        return result;
    }

    @Path("preCancelar")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response preCancelar(String jsonRQ) {
        WSReservaRS wsRS = null;
        WSReservaRQ wsRQ = gson.fromJson(jsonRQ, WSReservaRQ.class);
        try {
            wsRS = ezlinkWS.preCancelar(wsRQ);
        } catch (ErrorException ex) {
            wsRS = new WSReservaRS(null, ex.getIntegrador());
        } catch (Exception ex) {
            wsRS = new WSReservaRS(null, new ErrorException(wsRQ.getIntegrador(), WS.class, "preCancelar", WSMensagemErroEnum.GENNULO, "", WSIntegracaoStatusEnum.NEGADO, ex).getIntegrador());
        }
        Utils.gerarLog(wsRS.getIntegrador(), "preCancelar", true);
        Response result = Response.ok(gson.toJson(wsRS)).build();
        return result;
    }

    @Path("cancelar")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response cancelar(String jsonRQ) {
        WSReservaRS wsRS = null;
        WSReservaRQ wsRQ = gson.fromJson(jsonRQ, WSReservaRQ.class);
        try {
            wsRS = ezlinkWS.cancelar(wsRQ);
        } catch (ErrorException ex) {
            wsRS = new WSReservaRS(null, ex.getIntegrador());
        } catch (Exception ex) {
            wsRS = new WSReservaRS(null, new ErrorException(wsRQ.getIntegrador(), WS.class, "cancelar", WSMensagemErroEnum.GENNULO, "", WSIntegracaoStatusEnum.NEGADO, ex).getIntegrador());
        }
        Utils.gerarLog(wsRS.getIntegrador(), "cancelar", true);
        Response result = Response.ok(gson.toJson(wsRS)).build();
        return result;
    }

    @Path("pesquisaHotel")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response pesquisaHotel(String jsonRQ) {
        WSPesquisaHotelRS wsRS = null;
        WSPesquisaHotelRQ wsRQ = gson.fromJson(jsonRQ, WSPesquisaHotelRQ.class);
        try {
            wsRS = ezlinkWS.pesquisaHotel(wsRQ);
        } catch (ErrorException ex) {
            wsRS = new WSPesquisaHotelRS(null, ex.getIntegrador());
        } catch (Exception ex) {
            wsRS = new WSPesquisaHotelRS(null, new ErrorException(wsRQ.getIntegrador(), WS.class, "pesquisaHotel", WSMensagemErroEnum.GENNULO, "", WSIntegracaoStatusEnum.NEGADO, ex).getIntegrador());
        }
        Utils.gerarLog(wsRS.getIntegrador(), "pesquisaHotel", false);
        Response result = Response.ok(gson.toJson(wsRS)).build();
        return result;
    }

    @Path("detalheHotel")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response detalheHotel(String jsonRQ) {
        WSDetalheHotelRS wsRS = null;
        WSDetalheHotelRQ wsRQ = gson.fromJson(jsonRQ, WSDetalheHotelRQ.class);
        try {
            wsRS = ezlinkWS.detalheHotel(wsRQ);
        } catch (ErrorException ex) {
            wsRS = new WSDetalheHotelRS(null, ex.getIntegrador());
        } catch (Exception ex) {
            wsRS = new WSDetalheHotelRS(null, new ErrorException(wsRQ.getIntegrador(), WS.class, "detalheHotel", WSMensagemErroEnum.GENNULO, "", WSIntegracaoStatusEnum.NEGADO, ex).getIntegrador());
        }
        Utils.gerarLog(wsRS.getIntegrador(), "detalheHotel", false);
        Response result = Response.ok(gson.toJson(wsRS)).build();
        return result;
    }

    @Path("relatorio")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response relatorio(String jsonRQ) {
        WSReservaRelatorioRS wsRS = null;
        WSReservaRelatorioRQ wsRQ = gson.fromJson(jsonRQ, WSReservaRelatorioRQ.class);
        try {
            wsRS = ezlinkWS.relatorio(wsRQ);
        } catch (ErrorException ex) {
            wsRS = new WSReservaRelatorioRS(null, ex.getIntegrador());
        } catch (Exception ex) {
            wsRS = new WSReservaRelatorioRS(null, new ErrorException(wsRQ.getIntegrador(), WS.class, "relatorio", WSMensagemErroEnum.GENNULO, "", WSIntegracaoStatusEnum.INCONSISTENTE, ex).getIntegrador());
        }
        Utils.gerarLog(wsRS.getIntegrador(), "relatorio", true);
        Response result = Response.ok(gson.toJson(wsRS)).build();
        return result;
    }

    @Path("preAlterarReserva")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response preAlterarReserva(String jsonRQ) {
        WSPreAlterarRS wsRS = null;
        WSPreAlterarRQ wsRQ = gson.fromJson(jsonRQ, WSPreAlterarRQ.class);
        try {
            wsRS = ezlinkWS.preAlterarReserva(wsRQ);
        } catch (ErrorException ex) {
            wsRS = new WSPreAlterarRS(null, ex.getIntegrador());
        } catch (Exception ex) {
            wsRS = new WSPreAlterarRS(null, new ErrorException(wsRQ.getIntegrador(), WS.class, "preAlterarReserva", WSMensagemErroEnum.GENNULO, "", WSIntegracaoStatusEnum.NEGADO, ex).getIntegrador());
        }
        Utils.gerarLog(wsRS.getIntegrador(), "preAlterarReserva", true);
        Response result = Response.ok(gson.toJson(wsRS)).build();
        return result;
    }

    @Path("alterarReserva")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response alterarReserva(String jsonRQ) {
        WSAlteraReservaRS wsRS = null;
        WSAlteraReservaRQ wsRQ = gson.fromJson(jsonRQ, WSAlteraReservaRQ.class);
        try {
            wsRS = ezlinkWS.alterarReserva(wsRQ);
        } catch (ErrorException ex) {
            wsRS = new WSAlteraReservaRS(null, ex.getIntegrador());
        } catch (Exception ex) {
            wsRS = new WSAlteraReservaRS(null, new ErrorException(wsRQ.getIntegrador(), WS.class, "alterarReserva", WSMensagemErroEnum.GENNULO, "", WSIntegracaoStatusEnum.NEGADO, ex).getIntegrador());
        }
        Utils.gerarLog(wsRS.getIntegrador(), "alterarReserva", true);
        Response result = Response.ok(gson.toJson(wsRS)).build();
        return result;
    }
}
