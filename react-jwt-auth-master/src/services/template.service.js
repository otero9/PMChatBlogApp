import axios from "axios";

const API_URL = "http://localhost:8080/profileDataService/templateAPI/";

class TemplateService {

  getAllTemplates() {
    return axios
      .get(API_URL + "templates");
  }

  getBestTemplates() {
    return axios
      .get(API_URL + "bestTemplates");
  }

  getTemplateById(templateId) {
    return axios
      .get(API_URL + "getTemplateById?id="+templateId);
  }

  getTemplatesByUserId(userId) {
    return axios
      .get(API_URL + "templates?userId="+userId);
  }

  uploadFile(file) {
    let formData = new FormData();
    formData.append("file", file);
    return axios.post(API_URL + "file", formData);
  }

  downloadFile(fileId) {
    return axios
      .get(API_URL + "file?fileId="+fileId, { responseType: 'blob' });
  }

  uploadTemplate(categoryId, userId, templateTitle, templateDescription, fileId) {
    return axios.post(API_URL + "template?categoryId=" + categoryId + "&userId=" + userId, {
      templateTitle,
      templateDescription,
      fileId
    });
  }

  updateTemplate(templateId, categoryId, templateTitle, templateDescription, fileId) {
    return axios.put(API_URL + "updateTemplate?id=" + templateId, {
      categoryId,
      templateTitle,
      templateDescription,
      fileId
    });
  }

  deleteTemplate(templateId) {
    return axios.delete(API_URL + "template?id="+templateId);
  }

}

export default new TemplateService();
