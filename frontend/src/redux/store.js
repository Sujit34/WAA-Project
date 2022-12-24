import { configureStore } from "@reduxjs/toolkit";
// import loginReducer from "./loginReducer";
import userReducer from "./userReducer";

const store = configureStore({
  reducer: {
    // auth: loginReducer,
    user: userReducer,
  },
});

export default store;
