import axios from "axios";
import { API_BASE_URL } from "../constants/constants";
import {
  getAccessToken,
  getRefreshToken,
  setAccessToken,
  setRefreshToken,
  removeTokens,
} from "../service/tokenService";

axios.defaults.baseURL = API_BASE_URL;

let storedRequest = [];
let tokenRefreshFlag = false;

axios.interceptors.request.use(
  (config) => {
    if (getAccessToken())
      config.headers["Authorization"] = "Bearer " + getAccessToken();
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

axios.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    if (
      error.response &&
      error.response.status === 401 &&
      error.response.data.msg === "Token has expired!"
    ) {
      handleTokenExpired(error);
    } else {
      //check validation error
      console.log(error.response.data);
      const errorData = error.response.data.error;
      const validationErrors = errorData.validationErrors;
      console.log(validationErrors);
      if (validationErrors) {
        alert(validationErrors);
      }

      return Promise.reject(error);
    }
  }
);

async function storedRequestPromise(request) {
  return new Promise((resolve, reject) => {
    storedRequest.push({ resolve, reject });
  })
    .then((newToken) => {
      request.headers.authorization = "Bearer " + newToken;

      return axios(request);
    })
    .catch((err) => {
      return Promise.reject(err);
    });
}

async function handleTokenExpired(error) {
  const request = error.config;

  if (!getRefreshToken()) {
    removeTokens();
    return Promise.reject(error);
  }

  if (tokenRefreshFlag) {
    return storedRequestPromise(request);
  }

  tokenRefreshFlag = true;

  return axios
    .post(API_BASE_URL + "/refresh", {
      accessToken: getAccessToken(),
      refreshToken: getRefreshToken(),
    })
    .then((res) => {
      tokenRefreshFlag = false;
      setAccessToken(res.data.accessToken);
      setRefreshToken(res.data.refreshToken);
      request.headers.authorization = "Bearer" + res.data.accessToken;
      releaseRequests(res.data.accessToken);
      return axios(request);
    })
    .catch((err) => {
      if (err.response.status === 401) {
        removeTokens();
        return Promise.reject(err);
      }
      releaseRequests(null, err);
    });
}

function releaseRequests(newAccessToken, err = null) {
  storedRequest.forEach((promise) => {
    if (err) {
      return promise.reject(err);
    }
    promise.resolve(newAccessToken);
  });
  storedRequest = [];
}
