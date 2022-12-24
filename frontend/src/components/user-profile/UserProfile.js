import React, { useEffect } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router";
import { FACULTY, STUDENT } from "../../constants/constants";
import { hasToken } from "../../service/tokenService";
import Home from "../home/Home";
import FacultyProfile from "./FacultyProfile";
import StudentProfile from "./StudentProfile";

function UserProfile() {
  const user = useSelector((state) => state.user);
  const navigate = useNavigate();

  useEffect(() => {
    if (!hasToken()) {
      navigate("/");
    }
  }, []);

  return (
    <>
      {user.role === STUDENT && <StudentProfile />}
      {user.role === FACULTY && <FacultyProfile />}
    </>
  );
}

export default UserProfile;
