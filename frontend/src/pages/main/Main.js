import React, { useEffect } from "react";
import BodyContainer from "../../components/body-container/BodyContainer";
import { Route, Routes, useNavigate } from "react-router-dom";
import Login from "../../components/login/Login";
import Register from "../../components/register/Register";
import Header from "../../components/header/Header";
import PageNotFound from "../../components/page-not-found/PageNotFound";
import Home from "../../components/home/Home";
import VerifyEmail from "../../components/verify-email/VerifyEmail";
import UserProfile from "../../components/user-profile/UserProfile";
import PasswordReset from "../../components/password-reset/PasswordReset";
import ChangePassword from "../../components/change-password/ChangePassword";
import Job from "../../components/Jobs/Job";
import AddJob from "../../components/Jobs/AddJob";
import EditJob from "../../components/Jobs/EditJob";
import UserList from "../../components/user-list/UserList";
import AddJobExperience from "../../components/job-experience/AddJobExperience";
import MyJobAdvertisements from "../../components/Jobs/MyJobAdvertisements";
import Students from "../../components/student/Students";
import StudentDetails from "../../components/student/StudentDetails";
import JobDetails from "../../components/Jobs/JobDetails";
import { hasToken } from "../../service/tokenService";

function Main() {
  return (
    <div>
      <Header />

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="register" element={<Register />} />
        <Route path="login" element={<Login />} />
        <Route path="reset-password" element={<PasswordReset />} />
        <Route path="change-password" element={<ChangePassword />} />
        <Route path="verify-email" element={<VerifyEmail />} />
        <>
          <Route path="update-profile" element={<UserProfile />} />
          <Route path="jobs" element={<Job />}></Route>
          <Route path="add-job" element={<AddJob />}></Route>
          <Route path="my-jobs" element={<MyJobAdvertisements />}></Route>
          <Route path="edit-job/:id" element={<EditJob />}></Route>
          <Route path="job-detail/:id" element={<JobDetails />}></Route>
          <Route path="admin/users" element={<UserList />}></Route>
          <Route
            path="add-job-experience"
            element={<AddJobExperience />}
          ></Route>
          <Route path="students" element={<Students />}></Route>
          <Route path="student-detail/:id" element={<StudentDetails />}></Route>
        </>
        <Route path="*" element={<PageNotFound />}></Route>
      </Routes>

      <div className="p-5">
        <BodyContainer />
      </div>
      {/* <Footer /> */}
    </div>
  );
}

export default Main;
