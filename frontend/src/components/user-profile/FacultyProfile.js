import { useEffect, useRef } from "react";
import axios from "axios";
import { useNavigate } from "react-router";
import BodyContainer from "../body-container/BodyContainer";
import { getUser } from "../../service/tokenService";

function FacultyProfile() {
  const formRef = useRef();
  const navigate = useNavigate();

  function getProfileData() {
    axios
      .get("faculty/filterByEmail?email=" + getUser().email)
      .then((response) => {
        const { facultyId, department } =
          response.data;
        formRef.current.facultyId.value = facultyId;
        formRef.current.department.value = department;
      });
  }

  function updateProfileData(data) {
    axios.put("faculty", data).then(() => {
      navigate("/");
    });
  }

  function onSaveClicked(e) {
    e.preventDefault();
    const form = formRef.current;
    const data = {
      email: getUser().email,
      facultyId: form.facultyId.value,
      department: form.department.value,
    };
    updateProfileData(data);
  }

  useEffect(() => {
    getProfileData();
  }, []);

  return (
    <BodyContainer>
      <h1 className="mb-4">Faculty Profile</h1>
      <form className="form-a contactForm" ref={formRef}>
        <div className="row">
          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="facultyId" className="form-label">
                Faculty Id:
              </label>
              <input
                id="facultyId"
                name="facultyId"
                type="facultyId"
                className="form-control form-control-lg form-control-a"
                required
              />
            </div>
          </div>
          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="department" className="form-label">
                Department:
              </label>
              <input
                name="department"
                id="department"
                type="text"
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
    </BodyContainer>
  );
}

export default FacultyProfile;
