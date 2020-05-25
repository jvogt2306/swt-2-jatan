package de.jatan.analysisapplication.Domain.Model;

public class GithubOrganization {

    private String login;
    private String id;
    private String node_id;
    private String url;
    private String repos_url;
    private String  events_url;
    private String hooks_url;
    private String issues_url;
private String members_url;
  private String public_members_url;
  private String avatar_url;
  private String description;
  private String name;
  private String company ;
  private String blog; 
  private String location ;
  private String email ;
  private String  is_verified ;
  private String has_organization_projects ;
  private String has_repository_projects ;
  private String public_repos ;
  private String public_gists;
  private String followers;
  private String following;
  private String html_url;
  private String created_at;
  private String updated_at;
  private String type;

    /**
     * @return String return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return String return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return String return the node_id
     */
    public String getNode_id() {
        return node_id;
    }

    /**
     * @param node_id the node_id to set
     */
    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    /**
     * @return String return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return String return the repos_url
     */
    public String getRepos_url() {
        return repos_url;
    }

    /**
     * @param repos_url the repos_url to set
     */
    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    /**
     * @return String return the events_url
     */
    public String getEvents_url() {
        return events_url;
    }

    /**
     * @param events_url the events_url to set
     */
    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    /**
     * @return String return the hooks_url
     */
    public String getHooks_url() {
        return hooks_url;
    }

    /**
     * @param hooks_url the hooks_url to set
     */
    public void setHooks_url(String hooks_url) {
        this.hooks_url = hooks_url;
    }

    /**
     * @return String return the issues_url
     */
    public String getIssues_url() {
        return issues_url;
    }

    /**
     * @param issues_url the issues_url to set
     */
    public void setIssues_url(String issues_url) {
        this.issues_url = issues_url;
    }

    /**
     * @return String return the members_url
     */
    public String getMembers_url() {
        return members_url;
    }

    /**
     * @param members_url the members_url to set
     */
    public void setMembers_url(String members_url) {
        this.members_url = members_url;
    }

    /**
     * @return String return the public_members_url
     */
    public String getPublic_members_url() {
        return public_members_url;
    }

    /**
     * @param public_members_url the public_members_url to set
     */
    public void setPublic_members_url(String public_members_url) {
        this.public_members_url = public_members_url;
    }

    /**
     * @return String return the avatar_url
     */
    public String getAvatar_url() {
        return avatar_url;
    }

    /**
     * @param avatar_url the avatar_url to set
     */
    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return String return the blog
     */
    public String getBlog() {
        return blog;
    }

    /**
     * @param blog the blog to set
     */
    public void setBlog(String blog) {
        this.blog = blog;
    }

    /**
     * @return String return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String return the is_verified
     */
    public String getIs_verified() {
        return is_verified;
    }

    /**
     * @param is_verified the is_verified to set
     */
    public void setIs_verified(String is_verified) {
        this.is_verified = is_verified;
    }

    /**
     * @return String return the has_organization_projects
     */
    public String getHas_organization_projects() {
        return has_organization_projects;
    }

    /**
     * @param has_organization_projects the has_organization_projects to set
     */
    public void setHas_organization_projects(String has_organization_projects) {
        this.has_organization_projects = has_organization_projects;
    }

    /**
     * @return String return the has_repository_projects
     */
    public String getHas_repository_projects() {
        return has_repository_projects;
    }

    /**
     * @param has_repository_projects the has_repository_projects to set
     */
    public void setHas_repository_projects(String has_repository_projects) {
        this.has_repository_projects = has_repository_projects;
    }

    /**
     * @return String return the public_repos
     */
    public String getPublic_repos() {
        return public_repos;
    }

    /**
     * @param public_repos the public_repos to set
     */
    public void setPublic_repos(String public_repos) {
        this.public_repos = public_repos;
    }

    /**
     * @return String return the public_gists
     */
    public String getPublic_gists() {
        return public_gists;
    }

    /**
     * @param public_gists the public_gists to set
     */
    public void setPublic_gists(String public_gists) {
        this.public_gists = public_gists;
    }

    /**
     * @return String return the followers
     */
    public String getFollowers() {
        return followers;
    }

    /**
     * @param followers the followers to set
     */
    public void setFollowers(String followers) {
        this.followers = followers;
    }

    /**
     * @return String return the following
     */
    public String getFollowing() {
        return following;
    }

    /**
     * @param following the following to set
     */
    public void setFollowing(String following) {
        this.following = following;
    }

    /**
     * @return String return the html_url
     */
    public String getHtml_url() {
        return html_url;
    }

    /**
     * @param html_url the html_url to set
     */
    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    /**
     * @return String return the created_at
     */
    public String getCreated_at() {
        return created_at;
    }

    /**
     * @param created_at the created_at to set
     */
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    /**
     * @return String return the updated_at
     */
    public String getUpdated_at() {
        return updated_at;
    }

    /**
     * @param updated_at the updated_at to set
     */
    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    /**
     * @return String return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ClassPojo [ [login = " + login + ", id = " + id + ", node_id = "
        + node_id + ", url = " + url + ", repos_url = " + repos_url + ", events_url = "
        + events_url + ", hooks_url = " + hooks_url + ", issues_url = " + issues_url
        + ", members_url = " + members_url + ", public_members_url = " + public_members_url + "]";

}
}