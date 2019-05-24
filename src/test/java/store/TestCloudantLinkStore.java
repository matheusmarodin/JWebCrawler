package store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import model.Link;

@ExtendWith(MockitoExtension.class)
public class TestCloudantLinkStore {
		
	final String ID = "1";
	
	@Mock
	LinkStore store = org.mockito.Mockito.mock(LinkStore.class);

	@Test
	public void testGetAll() {
		Link link1 = new Link();
		link1.set_id(ID);	
		
		Link link2 = new Link();
		
		Set<Link> links = new HashSet<>();
		links.add(link1);
		links.add(link2);
		
		when(store.getAll()).thenReturn(links);
		
		Set<Link> savedLinks = (Set<Link>) store.getAll();
		
		assertNotNull(savedLinks);		
		assertEquals(2,savedLinks.size());
	}
	
	@Test
	public void testGet() {
		Link link1 = new Link();
		link1.set_id(ID);		
		
		when(store.get(any())).thenReturn(link1);
		
		Link foundLink = store.get(ID);		
		
		assertNotNull(foundLink);
		assertEquals(ID, foundLink.get_id());
	}
	

	@Test
	public void testPersist() {
		Link link1 = new Link();
		link1.set_id(ID);	
		
		when(store.persist(any())).thenReturn(link1);
		
		Link savedLink = store.persist(link1);
		
		assertNotNull(savedLink);		
		assertNotNull(savedLink.get_id());
				
	}

	@Test
	public void testUpdate() {
		Link link1 = new Link();
		link1.set_id(ID);	
		
		when(store.update(any(),any())).thenReturn(link1);
		
		Link savedLink = store.update(link1.get_id(),link1);
		
		assertNotNull(savedLink);		
		assertNotNull(savedLink.get_id());
			
	}
	
	@Test
	public void testDelete() {
		Link link1 = new Link();
		link1.set_id(ID);	
		
		store.delete(link1.get_id());
		
		verify(store).delete(any());
	}

	
	
	@Test
	public void testCount() throws Exception {
		Link link1 = new Link();
		link1.set_id(ID);	
		
		Link link2 = new Link();
		
		Set<Link> links = new HashSet<>();
		links.add(link1);
		links.add(link2);
		
		when(store.count()).thenReturn(links.size());
		
		int size = store.count();
						
		assertEquals(2,size);
	}

}
