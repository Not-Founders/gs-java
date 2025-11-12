package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Clientes;
import org.acme.service.ClienteService;

import java.sql.SQLException;
import java.util.List;

@Path("/main")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

    public class ClienteResource {

    @Inject
    ClienteService clienteService;
    @POST
    @Path("/cliente")
    public Response criarCliente(Clientes cliente) {
        try {
            boolean sucesso = clienteService.cadastrarCliente(cliente);
            if (sucesso) {
                return Response.status(Response.Status.CREATED)
                        .entity("Cliente cadastrado com sucesso!").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Dados inválidos! Verifique nome, CPF, email ou senha.").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro no banco de dados: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/clientes")
    public Response buscarClientes() {
        try {
            List<Clientes> lista = clienteService.listarClientes();
            return Response.ok(lista).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar clientes: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/cliente/{id}")
    public Response atualizarCliente(@PathParam("id") int id, Clientes cliente) {
        try {
            boolean sucesso = clienteService.atualizarCliente(
                    id,
                    cliente.getNomeCompleto(),
                    cliente.getCpfNumero(),
                    cliente.getEmailCliente(),
                    cliente.getPassword()
            );
            if (sucesso) {
                return Response.ok("Cliente atualizado com sucesso!").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Cliente não encontrado ou dados inválidos.").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar cliente: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/cliente/{id}")
    public Response deletarCliente(@PathParam("id") int id) {
        try {
            boolean sucesso = clienteService.deletarCliente(id);
            if (sucesso) {
                return Response.ok("Cliente deletado com sucesso!").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Cliente não encontrado.").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar cliente: " + e.getMessage()).build();
        }
    }
}
