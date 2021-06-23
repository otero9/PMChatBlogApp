import axios from "axios";

const API_URL = "http://localhost:8080/profileDataService/categoryAPI/";

class CategoryService {

  getAllCategories() {
    return axios.get("http://localhost:8080/profileDataService/categoryAPI/categories");
  }

  getCategoriesByName(categoryName) {
    return axios
      .get(API_URL + "getCategoryByCategoryName?categoryName="+categoryName);
  }

}

export default new CategoryService();
