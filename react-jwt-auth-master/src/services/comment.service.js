import axios from "axios";

const API_URL = "http://localhost:8080/profileDataService/commentAPI/";

class CommentService {

  createComment(userId, blogId, commentValue) {
    return axios.post(API_URL + "comment?userId="+userId+"&blogId="+blogId, {
      commentValue
    });
  }

}

export default new CommentService();
