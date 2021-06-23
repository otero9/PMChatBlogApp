import axios from "axios";

const API_URL = "http://localhost:8080/profileDataService/userAPI/";

class UserService {

  getBestUsers() {
    return axios
      .get(API_URL + "bestUsers");
  }

  login(username, password) {
    return axios
      .post(API_URL + "login", {
        username,
        password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("user");
  }

  signup(userName, email, password, firstName, lastName, birthdate) {
    return axios.post(API_URL + "signup", {
      userName,
      email,
      password,
      firstName,
      lastName,
      birthdate
    });
  }

  updateProfile(userId, userName, email, firstName, lastName, birthdate) {
    return axios.put(API_URL + "user?id="+userId, {
      userName,
      email,
      firstName,
      lastName,
      birthdate
    });
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem("user"));;
  }

  deleteAccount(userId) {
    return axios.delete(API_URL + "user?id="+userId);
  }

  getUserById(userId) {
    return axios.get(API_URL + "getUserById?id="+userId);
  }

  changePassword(userId, oldPassword, newPassword) {
    return axios.post(API_URL + "changePassword?id="+userId, {
      oldPassword,
      newPassword
    });
  }

}

export default new UserService();
