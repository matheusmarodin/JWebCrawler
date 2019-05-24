package model;

public class Link {
	 private String _id;
	    private String _rev;
	    private String childLink = null;
	    private String parentLink = null;	   

		public Link() {
	        this.childLink = "";
	        this.parentLink = "";
	    }

	    /**
	     * Gets the ID.
	     * 
	     * @return The ID.
	     */
	    public String get_id() {
	        return _id;
	    }

	    /**
	     * Sets the ID
	     * 
	     * @param _id
	     *            The ID to set.
	     */
	    public void set_id(String _id) {
	        this._id = _id;
	    }

	    /**
	     * Gets the revision of the document.
	     * 
	     * @return The revision of the document.
	     */
	    public String get_rev() {
	        return _rev;
	    }

	    /**
	     * Sets the revision.
	     * 
	     * @param _rev
	     *            The revision to set.
	     */
	    public void set_rev(String _rev) {
	        this._rev = _rev;
	    }

	    /**
	     * Gets the child link of the document.
	     * 
	     * @return The child link of the document.
	     */
	    public String getChildLink() {
	        return childLink;
	    }

	    /**
	     * Sets the childLink
	     * 
	     * @param childLink
	     *            The childLink to set.
	     */
	    public void setChildLink(String childLink ) {
	        this.childLink = childLink;
	    }
	    	    
	     /**
	     * Gets the parent link of the document.
	     * 
	     * @return The parent link of the document.
	     */
	    public String getParentLink() {
	        return parentLink;
	    }

	    /**
	     * Sets the parentLink
	     * 
	     * @param parentLink
	     *            The parentLink to set.
	     */
	    public void setParentLink(String parentLink ) {
	        this.parentLink = parentLink;
	    }
	    
	    @Override
		public String toString() {
			return "Link [_id=" + _id + ", _rev=" + _rev + ", childLink=" + childLink + ", parentLink=" + parentLink
					+ "]";
		}

}
