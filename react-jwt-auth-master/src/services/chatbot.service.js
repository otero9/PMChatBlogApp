import axios from "axios";

const API_URL = "http://127.0.0.1:8000/chatbot";

class ChatBotService {

  getResponse(msg) {
    return axios.post(API_URL, {
      msg
    },{'headers': {
      'Content-Type': 'application/json'
      }}
    );
  }

}

export default new ChatBotService();
