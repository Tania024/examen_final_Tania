package ups.edu.ec.examen_final.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ups.edu.ec.examen_final.business.GestionRecargaElectronica;
import ups.edu.ec.examen_final.model.RecargaElectronica;

@Path("recargas")
@Named
@ApplicationScoped
public class RecargaElectronicaServices {

    @Inject
    private GestionRecargaElectronica gestionRecarga;

    @POST
    @Path("/procesar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response procesarRecarga(RecargaElectronica recarga) {
        try {
            
            if (recarga != null && recarga.getNumeroTelefono() != null && !recarga.getNumeroTelefono().isEmpty()
                && recarga.getOperador() != null && !recarga.getOperador().isEmpty()
                && recarga.getMonto() > 0) {

               
                gestionRecarga.guardarRecargaElectronica(recarga);

                return Response.ok("Recarga procesada exitosamente").build();
            } else {
                
                return Response.status(Response.Status.BAD_REQUEST).entity("Datos de recarga no válidos").build();
            }
        } catch (Exception e) {
            // Simular transacción fallida
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error al procesar la recarga: " + e.getMessage()).build();
        }
    }
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(RecargaElectronica recarga) {
        try {
            gestionRecarga.actualizarRecargaElectronica(recarga);
            return Response.ok(recarga).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }
    }

    @DELETE
    @Path("elim/{id}")
    public Response borrar(@PathParam("id") int id) {
        try {
            gestionRecarga.eliminarRecargaElectronica(id);
            return Response.ok("OK").build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response leer(@PathParam("id") int id) {
        try {
            RecargaElectronica recarga = gestionRecarga.obtenerRecargaElectronica(id);
            if (recarga != null) {
                return Response.ok(recarga).build();
            } else {
                ErrorMessage error = new ErrorMessage(4, "Recarga electrónica no existe");
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(error)
                        .build();
            }
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public Response getRecargasElectronicas() {
        try {
            List<RecargaElectronica> recargas = gestionRecarga.obtenerTodasRecargasElectronicas();
            if (!recargas.isEmpty()) {
                return Response.ok(recargas).build();
            } else {
                ErrorMessage error = new ErrorMessage(6, "No se registran recargas electrónicas");
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(error)
                        .build();
            }
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }
    }
}
