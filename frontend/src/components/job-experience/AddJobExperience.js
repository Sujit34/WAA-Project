import axios from "axios";
import React, { useRef } from "react";
import { useNavigate } from "react-router";
import { getUser } from "../../service/tokenService";
import BodyContainer from "../body-container/BodyContainer";

function AddJobExperience() {
  const formRef = useRef();
  const navigate = useNavigate();

  function addJobExperience(data) {
    axios.post("/jobExperiences", data).then(() => {
      navigate("/update-profile");
    });
  }

  function onSubmitClick(e) {
    e.preventDefault();
    const form = formRef.current;
    const data = {
      email: getUser().email,
      title: form.title.value,
      startDate: new Date(form.startDate.value),
      endDate: new Date(form.endDate.value),
      company: form.company.value,
      description: form.description.value,
    };
    addJobExperience(data);
  }

  return (
    <BodyContainer>
      <h1 className="mb-4">Add Job Experience</h1>
      <form className="form-a contactForm" ref={formRef}>
        <div className="row">
          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="title" className="form-label">
                Title:
              </label>
              <input
                id="title"
                name="title"
                type="text"
                className="form-control form-control-lg form-control-a"
                required
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="company" className="form-label">
                Company:
              </label>
              <input
                id="company"
                name="company"
                type="text"
                className="form-control form-control-lg form-control-a"
                required
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="startDate" className="form-label">
                Start Date:
              </label>
              <input
                type="date"
                name="startDate"
                id="startDate"
                className="form-control form-control-lg form-control-a"
                required
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="endDate" className="form-label">
                End Date:
              </label>
              <input
                type="date"
                name="endDate"
                id="endDate"
                className="form-control form-control-lg form-control-a"
                required
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="description" className="form-label">
                Description:
              </label>
              <textarea
                name={"description"}
                id={"description"}
                className="form-control form-control-lg form-control-a"
                required
              ></textarea>
            </div>
          </div>

          <div className="col-md-12 text-right mb-4">
            <button
              type="submit"
              className="btn btn-primary"
              onClick={onSubmitClick}
            >
              Submit
            </button>
          </div>
        </div>
      </form>
    </BodyContainer>
  );
}

export default AddJobExperience;
