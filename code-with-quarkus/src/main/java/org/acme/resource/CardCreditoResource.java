package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.CardCreditos;
import org.acme.service.CardCreditoService;

import java.sql.SQLException;
import java.util.List;

@Path("/main")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CardCreditoResource {

    @Inject
    CardCreditoService cardCreditoService;
    @GET
    @Path("/cardcreditos")
    public Response buscarCartoes() {
        try {
            List<CardCreditos> lista = cardCreditoService.listarCardCredito();
            return Response.ok(lista).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar cartões: " + e.getMessage()).build();
        }
    }

    @POST
    @Path("/cardcredito")
    public Response criarCardCredito(CardCreditos cardCredito) {
        try {
            cardCreditoService.cadastrarCardCredito(cardCredito);
            return Response.status(Response.Status.CREATED)
                    .entity("Cartão cadastrado com sucesso!").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar cartão: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/cardcreditos/{id}")
    public Response cancelarCardCredito(@PathParam("id") int id) {
        try {
            boolean sucesso = cardCreditoService.cancelarCardCredito(id);
            if (sucesso) {
                return Response.ok("Cartão cancelado com sucesso!").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Cartão não encontrado.").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir cartão: " + e.getMessage()).build();
        }
    }
    @PUT
    @Path("/cardcredito/{id}")
    public Response atualizarCardCredito(@PathParam("id") int id, CardCreditos cardCredito) {
        try {
            boolean sucesso = cardCreditoService.atualizarCardCredito(
                    id,
                    cardCredito.getNumCard(),
                    cardCredito.getDataValCard(),
                    cardCredito.getCvvCard(),
                    cardCredito.getNomeTitular(),
                    cardCredito.getCpfTitular()
            );

            if (sucesso) {
                return Response.ok("Cartão atualizado com sucesso!").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Erro: dados inválidos ou cartão não encontrado.").build();
            }

        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar cartão: " + e.getMessage()).build();
        }
    }

}

