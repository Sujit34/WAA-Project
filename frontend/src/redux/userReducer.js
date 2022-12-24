import { createSlice } from "@reduxjs/toolkit";
import { getUser } from "../service/tokenService";

const userSlice = createSlice({
  name: "user",
  initialState: {
    role: null,
    email: null,
    name: null,
  },
  reducers: {
    setUser: (state) => {
      state.role = getUser()?.role;
      state.email = getUser()?.email;
      state.name = getUser()?.name;
    },
  },
});

export default userSlice.reducer;
export const { setUser } = userSlice.actions;