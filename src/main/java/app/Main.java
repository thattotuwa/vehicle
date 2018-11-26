package app;

import io.javalin.Javalin;
import app.vehicle.VehicleController;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().enableCaseSensitiveUrls().start(7000);
        VehicleController vehicleController = new VehicleController();

        app.routes(() -> {
            path("vehicle", () -> {
                path("/get", () -> {
                    get(vehicleController.getVehicle);
                });
                path("/search", () -> {
                    //get(ctx -> ctx.result("Hello World ids"));
                    get(vehicleController.searchVehicle);
                    //http://localhost.ifmsystems.com:7000/vehicle/search?term=SB1BD76L00E134148&type=vin&market=fr&subscriptionAssignment=DYN00000000006447D&locale=en-gb&username=thattotuwarbu@infomedia.com.au
                });
                path("/list", () -> {
                    get(vehicleController.listVehicles);
                });
                path("/listFamilies", () -> {
                    get(vehicleController.listFamilies);
                });
                path("/listSeries", () -> {
                    get(vehicleController.listSeries);
                });

            });
        });
    }
}
