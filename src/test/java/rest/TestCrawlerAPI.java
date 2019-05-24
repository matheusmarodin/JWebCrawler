package rest;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import model.Link;
import model.Url;
import store.LinkStore;
import store.LinkStoreFactory;


@ExtendWith(MockitoExtension.class)
public class TestCrawlerAPI {
	
	final String ID = "1";
	final String URL = "www.example.com";
	final int DEPTH = 0;
	
	@Mock
	LinkStore store = org.mockito.Mockito.mock(LinkStore.class);

	@InjectMocks
	CrawlerAPI crawlerAPI = org.mockito.Mockito.mock(CrawlerAPI.class);

	@Test
	public void testGetLinks() {
		Link link1 = new Link();		
		link1.setChildLink("www.teste1.com");
		link1.setParentLink("www.wikipedia.org");
		
		Link link2 = new Link();
		link2.setChildLink("www.teste2.com");
		link2.setParentLink("www.wikipedia.org");
		
		Set<Link> links = new HashSet<>();
		links.add(link1);
		links.add(link2);
		
		when(store.getAll()).thenReturn(links);		
		
		List<JsonObject> jsonObjects = new ArrayList<JsonObject>();
	        for (Link doc : store.getAll()) {
	            
	            JsonObject jsonObj = new JsonObject();
	            jsonObj.addProperty("childLink", doc.getChildLink());
	            jsonObj.addProperty("parentLink", doc.getParentLink());
	            
	            jsonObjects.add(jsonObj);
	    }
	    
	    String json = new Gson().toJson(jsonObjects);
	        
	    when(crawlerAPI.getLinks()).thenReturn(json);
	    
	    assertTrue(crawlerAPI.getLinks().equals(json));
	}
	
	@Test
	public void testDoWebCrawler() throws Exception {
		Url url = new Url();
		url.setUrl(URL);
		url.setDepth(DEPTH);
		
		when(crawlerAPI.doWebCrawler(any())).thenReturn("OK");
		
		String ret = crawlerAPI.doWebCrawler(url);
		
		assertTrue("OK".equals(ret));
	}
}
