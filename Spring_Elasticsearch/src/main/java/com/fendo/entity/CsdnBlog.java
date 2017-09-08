package com.fendo.entity;


public class CsdnBlog {
	
    private Integer id;

    private Integer keyes;

    private String titles;

    private String content;

    private String dates;

    private String tags;

    private String category;

    private Integer views;

    private Integer comments;

    private Integer copyright;

    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKeyes() {
        return keyes;
    }

    public void setKeyes(Integer keyes) {
        this.keyes = keyes;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles == null ? null : titles.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates == null ? null : dates.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getCopyright() {
        return copyright;
    }

    public void setCopyright(Integer copyright) {
        this.copyright = copyright;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

	public CsdnBlog(Integer id, Integer keyes, String titles, String content, String dates, String tags,
			String category, Integer views, Integer comments, Integer copyright, String url) {
		super();
		this.id = id;
		this.keyes = keyes;
		this.titles = titles;
		this.content = content;
		this.dates = dates;
		this.tags = tags;
		this.category = category;
		this.views = views;
		this.comments = comments;
		this.copyright = copyright;
		this.url = url;
	}

	public CsdnBlog() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CsdnBlog [id=" + id + ", keyes=" + keyes + ", titles=" + titles + ", content=" + content + ", dates="
				+ dates + ", tags=" + tags + ", category=" + category + ", views=" + views + ", comments=" + comments
				+ ", copyright=" + copyright + ", url=" + url + "]";
	}
    
    
}