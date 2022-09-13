package com.example.demo.controllers;

import com.example.demo.models.Bootcamper;
import com.example.demo.services.BootcamperService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Component
@Path("/")
public class BootcampersControllers {

    private final BootcamperService bootcamperService;

    public BootcampersControllers(BootcamperService bootcamperService) {
        this.bootcamperService = bootcamperService;

        this.bootcamperService.add(new Bootcamper("uno", Math.random()));
        this.bootcamperService.add(new Bootcamper("dos", Math.random()));
        this.bootcamperService.add(new Bootcamper("tres", Math.random()));
        this.bootcamperService.add(new Bootcamper("cuatro", Math.random()));
        this.bootcamperService.add(new Bootcamper("cinco", Math.random()));
    }

    @GET
    @Path("/bootcampers")
    @Produces("aplication/json")
    public List<Bootcamper> listarTodos(){
        return bootcamperService.getAll();
    }

    @GET
    @Path("/bootcampers/{nombre}")
    @Produces("aplication/json")
    public Bootcamper listarUno(@PathParam("nombre") String nombre){
        return bootcamperService.get(nombre);
    }

    @POST
    @Path("/bootcampers")
    @Produces("aplication/json")
    @Consumes("aplication/json")
    public Response meterBootcamper(Bootcamper bootcamper) {
        bootcamperService.add(bootcamper);

        return Response.created(
                URI.create("/bootcampers/" + bootcamper.getNombre())
        ).build();
    }





}
