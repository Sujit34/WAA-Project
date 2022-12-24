export const getAccessToken = () => {
  return localStorage.getItem("accessToken");
}

export const getRefreshToken = () => {
  return localStorage.getItem("refreshToken");
}

export const setAccessToken = (token) => {
  localStorage.setItem("accessToken", token);
}

export const setRefreshToken = (token) => {
  localStorage.setItem("refreshToken", token);
}

export const setTokenData = (tokenData) => {
  if (tokenData && tokenData.accessToken && tokenData.refreshToken && tokenData.userType) {
    setAccessToken(tokenData.accessToken);
    setRefreshToken(tokenData.refreshToken);
    setUser(tokenData.fullName, tokenData.email, tokenData.userType);
  }
}

export const removeTokens = () => {
  localStorage.removeItem("accessToken");
  localStorage.removeItem("refreshToken");
  localStorage.removeItem("user");
}

export const setUser = (name, email, role) => {
    const user = {
        name: name,
        email: email,
        role: role
    }
  localStorage.setItem("user", JSON.stringify(user));
}

export const getUser = () => {
  return JSON.parse(localStorage.getItem("user"));
}

export const hasToken = () => {
  const accessToken = getAccessToken();
  const refreshToken = getRefreshToken();

  if (!accessToken || accessToken === "") return false;
  if (!refreshToken || refreshToken === "") return false;

  return true;
}
