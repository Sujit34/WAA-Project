import axios from "axios";
import React, { createContext, useEffect, useState } from "react";
import { useParams } from "react-router";
import { FACULTY } from "../../constants/constants";
import { getUser } from "../../service/tokenService";
import BodyContainer from "../body-container/BodyContainer";
import StudentCommentList from "../comment/StudentCommentList";
import JobExperienceList from "../job-experience/JobExperienceList";

export const StudentContext = createContext();

function StudentDetails() {
  const { id } = useParams();
  const [student, setStudent] = useState({});

  useEffect(() => {
    getDetails();
  }, []);

  const getDetails = () => {
    axios.get("students/" + id).then((response) => {
      console.log(response);
      setStudent(response.data);
    });
  };

  return (
    <BodyContainer>
      <h1 className="mb-4">Student Details</h1>
      <div className="col-md-12 mb-3">
        <div className="form-group">
          <label htmlFor="studentId" className="form-label">
            Student Id:
          </label>
          <div className="form-control form-control-lg bg-white">
            {student.studentId}
          </div>
        </div>
      </div>

      <div className="col-md-12 mb-3">
        <div className="form-group">
          <label htmlFor="firstName" className="form-label">
            First Name:
          </label>
          <div className="form-control form-control-lg bg-white">
            {student.firstName}
          </div>
        </div>
      </div>

      <div className="col-md-12 mb-3">
        <div className="form-group">
          <label htmlFor="lastName" className="form-label">
            Last Name:
          </label>
          <div className="form-control form-control-lg bg-white">
            {student.lastName}
          </div>
        </div>
      </div>

      <div className="col-md-12 mb-3">
        <div className="form-group">
          <label htmlFor="email" className="form-label">
            Email:
          </label>
          <div className="form-control form-control-lg bg-white">
            {student.email}
          </div>
        </div>
      </div>

      <div className="col-md-12 mb-3">
        <div className="form-group">
          <label htmlFor="address" className="form-label">
            Address:
          </label>
          <div className="form-control form-control-lg bg-white">
            {student.address?.street +
              ", " +
              student.address?.city +
              ", " +
              student.address?.state +
              ", " +
              student.address?.zipCode}
          </div>
        </div>
      </div>

      <div className="col-md-12 mb-3">
        <div className="form-group">
          <label htmlFor="program" className="form-label">
            Program:
          </label>
          <div className="form-control form-control-lg bg-white">
            {student.program}
          </div>
        </div>
      </div>

      <div className="col-md-12 mb-3">
        <div className="form-group">
          <label htmlFor="major" className="form-label">
            Major:
          </label>
          <div className="form-control form-control-lg bg-white">
            {student.major}
          </div>
        </div>
      </div>

      <div className="col-md-12 mb-3">
        <div className="form-group">
          <label htmlFor="entryYear" className="form-label">
            Entry Year:
          </label>
          <div className="form-control form-control-lg bg-white">
            {student.entryYear}
          </div>
        </div>
      </div>

      <div className="col-md-12 mb-3">
        <div className="form-group">
          <label htmlFor="completionYear" className="form-label">
            Completion Year:
          </label>
          <div className="form-control form-control-lg bg-white">
            {student.completionYear}
          </div>
        </div>
      </div>

      <div className="col-md-12 mb-3">
        <div className="form-group">
          <label htmlFor="tag" className="form-label">
            Subscribed Tags:
          </label>
          <div className="form-control form-control-lg bg-white">
            {student.tag?.map((t) => (
              <span key={t.id} className="badge badge-pill badge-secondary">
                {t.tag}
              </span>
            ))}
          </div>
        </div>
      </div>

      <div className="col-md-12 mb-3">
        <div className="form-group">
          <label htmlFor="resume" className="form-label">
            Resume:
          </label>
          <div className="form-control form-control-lg bg-white"></div>
        </div>
      </div>

      <div className="col-md-12 mb-3">
        <JobExperienceList
          jobExperiences={student.jobExperience}
          showAdd={false}
        />
      </div>

      {getUser().role && getUser().role === FACULTY && (
        <StudentContext.Provider value={student}>
          <div className="col-md-12 mb-3">
            <StudentCommentList showAdd={true} studentId={id} />
          </div>
        </StudentContext.Provider>
      )}
    </BodyContainer>
  );
}

export default StudentDetails;
