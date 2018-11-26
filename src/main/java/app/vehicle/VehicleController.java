package app.vehicle;

import io.javalin.Handler;
import org.json.simple.JSONObject;

import static app.vehicle.VehicleConstants.*;

public class VehicleController {

    public Handler getVehicle = ctx -> {
        JSONObject str = VehicleServiceHandler.getVehicle(ctx.queryParam(PARAM_CSID),
                 ctx.queryParam(PARAM_MODELID),
                ctx.queryParam(PARAM_MARKET),
                ctx.queryParam(PARAM_LOCALE), ctx.queryParam(PARAM_USERNAME));
        ctx.json(str);
        //ctx.result(String.valueOf(str));
    };

    public Handler searchVehicle = ctx -> {
        JSONObject str = VehicleServiceHandler.search(ctx.queryParam(PARAM_CSID),
                ctx.queryParam(PARAM_SEARCH_TERM),
                ctx.queryParam(PARAM_SEARCH_TYPE),
                ctx.queryParam(PARAM_MARKET),
                ctx.queryParam(PARAM_LOCALE),
                ctx.queryParam(PARAM_USERNAME));
        ctx.json(str);
        //ctx.result(String.valueOf(str));
    };
    public Handler listVehicles = ctx -> {
        JSONObject str = VehicleServiceHandler.list(ctx.queryParam(PARAM_CSID),
                ctx.queryParam(PARAM_FAMILY_CODE),
                ctx.queryParam(PARAM_MARKET),
                ctx.queryParam(PARAM_LOCALE),
                ctx.queryParam(PARAM_USERNAME));
        ctx.json(str);
        //ctx.result(String.valueOf(str));
    };

    public Handler listFamilies = ctx -> {
        JSONObject str = VehicleServiceHandler.listFamilies(ctx.queryParam(PARAM_CSID),
                ctx.queryParam(PARAM_MARKET),
                ctx.queryParam(PARAM_LOCALE),
                ctx.queryParam(PARAM_USERNAME));
        ctx.json(str);
        //ctx.result(String.valueOf(str));
    };

    public Handler listSeries = ctx -> {
        JSONObject str = VehicleServiceHandler.listSeries(ctx.queryParam(PARAM_CSID),
                ctx.queryParam(PARAM_FAMILY_CODE),
                ctx.queryParam(PARAM_MARKET),
                ctx.queryParam(PARAM_LOCALE),
                ctx.queryParam(PARAM_USERNAME));
        ctx.json(str);
        //ctx.result(String.valueOf(str));
    };






}
