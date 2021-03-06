package com.aliyektan.project.restapi;

import com.aliyektan.project.core.dao.ProcessDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by yektan on 17.12.2017.
 *
 * ÖNEMLİ NOT: GÖNDERMİŞ OLDUĞUNUZ MAILDE HANGİ BİLGİLERİ HANGİ İŞLEMLERİ HANGİ FORMATTA İSTEDİĞİNİZ
 * YAZMAMAKTA OLDUĞU İÇİN ÖRNEK TEŞKİL ETMESİ İÇİN ÜÇ CLASSIMI REST SERVİSLERE AÇTIM.
 *
 * 'JAX-RS' VE JSON FORMATINA DÖNÜŞTÜRMEK İÇİN 'JACKSON' PAKETLERİNİ KULLANDIM.
 *
 * POST, UPDATE, DELETE işlemlerini zaman kaybetmemek açısından eklemedim.
 */

@Path("/api/process")
public class ProcessService {

    private ProcessDao processDao = new ProcessDao();
    private ObjectMapper mapper = new ObjectMapper();

    @GET
    @Path("/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProcessById(@PathParam("param") Integer id) throws SQLException, ClassNotFoundException, JsonProcessingException {

        String json=mapper.writeValueAsString(processDao.getById(id));

        return Response.status(200).entity(json).build();

    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProcesses() throws SQLException, ClassNotFoundException, JsonProcessingException {

        List myList = processDao.findAll();
        String json = mapper.writeValueAsString(myList);

        return Response.status(200).entity(json).build();
    }
}
