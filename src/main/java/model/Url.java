package model;

public class Url {
	
	    private String url;
	    private Integer depth;	   

		public Url() {
	        this.url = "";
	        this.depth = 0;
	    }

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public Integer getDepth() {
			return depth;
		}

		public void setDepth(Integer depth) {
			this.depth = depth;
		}

		@Override
		public String toString() {
			return "Url [url=" + url + ", depth=" + depth + "]";
		}
	   
		
}
