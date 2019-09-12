package no.ssb.platform.rest;

import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
@Path("/")
public class MetricDemoResource{
    private static AtomicInteger temperature = new AtomicInteger(5);
    
    @GET
    @Produces("text/plain")
    @Counted(name = "wss_metric_up_counter", absolute = true, description = "How many times the temp has been increased")
    @Path("/up")
    public static String hotter() throws InterruptedException {
        return "Increasing temperature by one. Gauge now at " + temperature.incrementAndGet();
    }
    @GET
    @Produces("text/plain")
    @Counted(name = "wss_metric_down_counter", description = "How many times the temp has been decreased")
    @Path("/down")
    public static String cooler() {
        return "Increasing temperature by one. Gauge now at " + temperature.decrementAndGet();
    }
    @Counted(name = "wss_metric_counter", description = "How many times this method has been called")
    @GET
    @Produces("text/plain")
    @Path("/count")
    public static String count() {
        return "TODO";
    }

    @Gauge(name = "wss_temperature", unit = MetricUnits.NONE, description = "Temperature as of now")
    public AtomicInteger highestPrimeNumberSoFar() {
        return temperature;
    }
}