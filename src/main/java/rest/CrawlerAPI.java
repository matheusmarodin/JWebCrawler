package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import model.Link;
import model.Url;
import store.LinkStore;
import store.LinkStoreFactory;
import util.MyCrawler;


@ApplicationPath("api")
@Path("/crawler")
public class CrawlerAPI extends Application {

	//Our database store
    LinkStore store = LinkStoreFactory.getInstance();

    /**
     * Gets all Links.
     * REST API example:
     * <code>
     * GET http://localhost:9080/api/crawler
     * </code>
     * 
     * Response:
     * <code>
     * [ "www.teste1.com", "www.teste2.com" ]
     * </code>
     * @return A collection of all the Links
     */
    @GET
    @Path("/")
    @Produces({"application/json"})
    public String getLinks() {

        if (store == null) {
            return "[]";
        }

        List<JsonObject> jsonObjects = new ArrayList<JsonObject>();
        for (Link doc : store.getAll()) {
            
            JsonObject jsonObj = new JsonObject();
            jsonObj.addProperty("childLink", doc.getChildLink());
            jsonObj.addProperty("parentLink", doc.getParentLink());
            
            jsonObjects.add(jsonObj);
        }
        return new Gson().toJson(jsonObjects);
    }
    
    /**
     * Creates a new Link.
     * 
     * REST API example:
     * <code>
     * POST http://localhost:9080/api/Links
     * <code>
     * POST Body:
     * <code>
     * {
     *   "url":"www.teste1.com",
     *   "depth":"1"
     * }
     * </code>
     * Response:
     * <code>
     * "
     * </code>
     * @param Link The new Link to create.
     * @return The Link after it has been stored.  This will include a unique ID for the Link.
     * @throws Exception 
     */
    @POST
    @Produces("application/text")
    @Consumes("application/json")
    public String doWebCrawler(Url url) throws Exception {
    	    	
    	String crawlStorageFolder = Files.createTempDir().getAbsolutePath();
        int numberOfCrawlers = url.getDepth();

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        // Instantiate the controller for this crawl.
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        // For each crawl, you need to add some seed urls. These are the first
        // URLs that are fetched and then the crawler starts following links
        // which are found in these pages
        controller.addSeed(url.getUrl());        
    	
    	// The factory which creates instances of crawlers.
        CrawlController.WebCrawlerFactory<MyCrawler> factory = MyCrawler::new;
        
        // Start the crawl. This is a blocking operation, meaning that your code
        // will reach the line after this only when crawling is finished.
        controller.start(factory, numberOfCrawlers);
        
    	return "OK";
    }
}
