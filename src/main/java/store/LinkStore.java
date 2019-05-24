package store;

import java.util.Collection;

import model.Link;

public interface LinkStore {
	
	/**
     * Get the target db object.
     * 
     * @return Database.
     * @throws Exception 
     */
    public Object getDB();

  
    /**
     * Gets all Links from the store.
     * 
     * @return All Links.
     * @throws Exception 
     */
    public Collection<Link> getAll();

    /**
     * Gets an individual ToDo from the store.
     * @param id The ID of the ToDo to get.
     * @return The ToDo.
     */
    public Link get(String id);

    /**
     * Persists a Link to the store.
     * @param td The ToDo to persist.
     * @return The persisted ToDo.  The ToDo will not have a unique ID..
     */
    public Link persist(Link vi);

    /**
     * Updates a ToDo in the store.
     * @param id The ID of the Link to update.
     * @param td The Link with updated information.
     * @return The updated Link.
     */
    public Link update(String id, Link vi);

    /**
     * Deletes a ToDo from the store.
     * @param id The ID of the Link to delete.
     */
    public void delete(String id);
  
    /**
     * Counts the number of Links
     * @return The total number of Links.
     * @throws Exception 
     */
    public int count() throws Exception;
}
