import { useEffect, useRef, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router";
import BodyContainer from "../body-container/BodyContainer";
import { getUser } from "../../service/tokenService";
import JobExperienceList from "../job-experience/JobExperienceList";

function StudentProfile() {
  const formRef = useRef();
  const navigate = useNavigate();
  const [jobExperiences, setJobExperiences] = useState([]);

  function getProfileData() {
    axios
      .get("students/filterByEmail?email=" + getUser().email)
      .then((response) => {
        const {
          studentId,
          program,
          major,
          entryYear,
          completionYear,
          jobExperience,
        } = response.data;
        formRef.current.studentId.value = studentId;
        formRef.current.program.value = program;
        formRef.current.major.value = major;
        formRef.current.entryYear.value = entryYear;
        formRef.current.completionYear.value = completionYear;
        formRef.current.tag.value = response.data.tag
          .map((item) => item.tag)
          .join(", ");
        setJobExperiences(jobExperience);
      });
  }

  function updateProfileData(data) {
    axios.put("students", data).then(() => {
      navigate("/");
    });
  }

  function onSaveClicked(e) {
    e.preventDefault();
    const data = {
      email: getUser().email,
      studentId: formRef.current.studentId?.value,
      program: formRef.current.program?.value,
      major: formRef.current.major?.value,
      entryYear: formRef.current.entryYear?.value,
      completionYear: formRef.current.completionYear?.value,
      file: formRef.current.file?.value,
      tag: formRef.current.tag?.value.split(",").map((tag) => {
        return { tag: tag.trim() };
      }),
    };
    updateProfileData(data);
  }

  useEffect(() => {
    getProfileData();
  }, []);

  return (
    <BodyContainer>
      <h1 className="mb-4">Student Profile</h1>
      <form className="form-a contactForm" ref={formRef}>
        <div className="row">
          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="studentId" className="form-label">
                Student Id:
              </label>
              <input
                id="studentId"
                name="studentId"
                type="studentId"
                className="form-control form-control-lg form-control-a"
                required
              />
            </div>
          </div>
          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="program" className="form-label">
                Program:
              </label>
              <input
                name="program"
                id="program"
                type="text"
                className="form-control form-control-lg form-control-a"
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="major" className="form-label">
                Major:
              </label>
              <input
                name="major"
                id="major"
                type="text"
                className="form-control form-control-lg form-control-a"
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="entryYear" className="form-label">
                Entry Year:
              </label>
              <input
                name="entryYear"
                id="entryYear"
                type="text"
                className="form-control form-control-lg form-control-a"
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="completionYear" className="form-label">
                Completion Year:
              </label>
              <input
                name="completionYear"
                id="completionYear"
                type="text"
                className="form-control form-control-lg form-control-a"
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="tag" className="form-label">
                Subscribed Tags:
              </label>
              <input
                name="tag"
                id="tag"
                type="text"
                className="form-control form-control-lg form-control-a"
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="resume" className="form-label">
                Resume:
              </label>
              <input
                name="resume"
                id="resume"
                type="file"
                className="form-control form-control-lg form-control-a"
              />
            </div>
          </div>

          <div className="col-md-12 text-right mb-4">
            <button
              type="submit"
              className="btn btn-primary"
              onClick={onSaveClicked}
            >
              Save Profile
            </button>
          </div>
        </div>
      </form>

      <div className="col-md-12 mb-3">
        <JobExperienceList jobExperiences={jobExperiences} showAdd={true} />
      </div>
    </BodyContainer>
  );
}

export default StudentProfile;
