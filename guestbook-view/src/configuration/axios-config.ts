import Axios from "axios";

if (process.env.NODE_ENV === "development") {
  Axios.defaults.baseURL = "http://localhost:8080";
}
