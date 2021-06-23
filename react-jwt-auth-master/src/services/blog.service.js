import axios from "axios";

const API_URL = "http://localhost:8080/profileDataService/blogAPI/";

class BlogService {

  getAllBlogs() {
    return axios
      .get(API_URL + "blogs");
  }

  getBestBlogs() {
    return axios
      .get(API_URL + "bestBlogs");
  }

  getBlogById(blogId) {
    return axios
      .get(API_URL + "getBlogById?id="+blogId);
  }

  getBlogsByUserId(userId) {
    return axios
      .get(API_URL + "blogs?userId="+userId);
  }

  createBlog(categoryId, userId, title, header, body, footer) {
    return axios.post(API_URL + "blog?categoryId=" + categoryId + "&userId=" + userId, {
      title,
      header,
      body,
      footer
    });
  }

  updateBlog(blogId, categoryId, title, header, body, footer) {
    return axios.put(API_URL + "updateBlog?id=" + blogId, {
      categoryId,
      title,
      header,
      body,
      footer
    });
  }

  deleteBlog(blogId) {
    return axios.delete(API_URL + "blog?id="+blogId);
  }

}

export default new BlogService();
