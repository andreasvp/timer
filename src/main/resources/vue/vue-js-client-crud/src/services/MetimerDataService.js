import http from "../http-common";

class MetimerDataService {
  getAll() {
    return http.get("/metimers");
  }

  get(id) {
    return http.get(`/metimers/${id}`);
  }

  create(data) {
    return http.post("/metimers", data);
  }

  update(id, data) {
    return http.put(`/metimers/${id}`, data);
  }

  delete(id) {
    return http.delete(`/metimers/${id}`);
  }

  deleteAll() {
    return http.delete(`/metimers`);
  }

  findByTitle(title) {
    return http.get(`/metimers?title=${title}`);
  }
}

export default new MetimerDataService();
