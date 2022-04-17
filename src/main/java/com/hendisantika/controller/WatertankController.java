package com.hendisantika.controller;

import com.hendisantika.model.Watertank;
import com.hendisantika.service.WatertankManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-concurrency-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 17/04/22
 * Time: 09.40
 */
@RestController
public class WatertankController {
    @Autowired
    private WatertankManager watertankManager;

    /**
     * Retrieve an {@link ArrayList} of all current {@link Watertank} 's
     *
     * @param req
     * @param session
     * @return {@link Response}
     */
    @GetMapping(path = "getAllWatertanks")
    public Response getAllWatertanks(@Context HttpServletRequest req, @Context HttpSession session) {
        List<Watertank> al = new ArrayList<Watertank>(watertankManager.getAllWatertanks().values());
        return Response.status(200).entity(al).build();
    }


    /**
     * Retrieve the maximum capacity amount of a specific water tank container
     *
     * @param watertankId
     * @param req
     * @param session
     * @return {@link Response}
     */
    @GetMapping(path = "QueryMaxCapacity")
    public Response QueryMaxCapacity(@RequestParam("id") Integer watertankId, @Context HttpServletRequest req, @Context HttpSession session) {
        String queryMaxCapacity = String.valueOf(watertankManager.getMaxCapacity(watertankId));
        return Response.status(200).entity(queryMaxCapacity).build();
    }


    /**
     * Retrieve the current capacity amount of a specific water tank container
     *
     * @param watertankId
     * @param req
     * @param session
     * @return {@link Response}
     */
    @GetMapping(path = "QueryCurrentCapacity")
    public Response QueryCurrentCapacity(@RequestParam("id") Integer watertankId, @Context HttpServletRequest req, @Context HttpSession session) {
        String queryCurrentCapacity = String.valueOf(watertankManager.getCurrentCapacity(watertankId));
        return Response.status(200).entity(queryCurrentCapacity).build();
    }
}
