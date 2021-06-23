import axios from "axios";

const API_URL = "http://localhost:8080/profileDataService/rateAPI/";

class RateService {

  rateUser(userRatedId, userId, value) {
    return axios.post(API_URL + "rateUser?userId=" + userId + "&userRatedId=" + userRatedId, {
      value
    });
  }

  getUserRateUser(userRatedId, userId) {
    return axios.get(API_URL + "getUserRateUser?userId=" + userId + "&userRatedId=" + userRatedId);
  }

  rateBlog(blogId, userId,  value) {
    return axios.post(API_URL + "rateBlog?userId=" + userId + "&blogId=" + blogId, {
      value
    });
  }

  getUserBlogRate(blogId, userId) {
    return axios.get(API_URL + "getUserBlogRate?userId=" + userId + "&blogId=" + blogId);
  }

  rateTemplate(templateId, userId,  value) {
    return axios.post(API_URL + "rateTemplate?userId=" + userId + "&templateId=" + templateId, {
      value
    });
  }

  getUserTemplateRate(templateId, userId) {
    return axios.get(API_URL + "getUserTemplateRate?userId=" + userId + "&templateId=" + templateId);
  }

  rateVideo(videoId, userId,  value) {
    return axios.post(API_URL + "rateVideo?userId=" + userId + "&videoId=" + videoId, {
      value
    });
  }

  getUserVideoRate(videoId, userId) {
    return axios.get(API_URL + "getUserVideoRate?userId=" + userId + "&videoId=" + videoId);
  }

  rateComment(commentId, userId,  value) {
    return axios.post(API_URL + "rateComment?userId=" + userId + "&commentId=" + commentId, {
      value
    });
  }

  getUserCommentRate(commentId, userId) {
    return axios.get(API_URL + "getUserCommentRate?userId=" + userId + "&commentId=" + commentId);
  }

}

export default new RateService();
