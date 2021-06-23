import axios from "axios";

const API_URL = "http://localhost:8080/profileDataService/videoAPI/";

class VideoService {

  getAllVideos() {
    return axios
      .get(API_URL + "videos");
  }

  getBestVideos() {
    return axios
      .get(API_URL + "bestVideos");
  }

  getVideoById(videoId) {
    return axios
      .get(API_URL + "getVideoById?id="+videoId);
  }

  getVideosByUserId(userId) {
    return axios
      .get(API_URL + "videos?userId="+userId);
  }

  createVideo(categoryId, userId, videoUrl, videoTitle, videoDescription) {
    return axios.post(API_URL + "video?categoryId=" + categoryId + "&userId=" + userId, {
      videoUrl,
      videoTitle,
      videoDescription
    });
  }

  updateVideo(videoId, categoryId, videoUrl, videoTitle, videoDescription) {
    return axios.put(API_URL + "updateVideo?id=" + videoId, {
      categoryId,
      videoUrl,
      videoTitle,
      videoDescription
    });
  }

  deleteVideo(videoId) {
    return axios.delete(API_URL + "video?id="+videoId);
  }

}

export default new VideoService();
