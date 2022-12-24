import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router";
import JobExperience from "./JobExperience";

function JobExperienceList(props) {
  const navigate = useNavigate();

  const onAddClicked = () => {
    navigate("/add-job-experience");
  };

  return (
    <div className="card">
      <div className="card-header">Job Experiences</div>
      {props.jobExperiences && props.jobExperiences.length > 0 ? (
        <ul className="list-group list-group-flush">
          {props.jobExperiences.map((exp, index) => (
            <li key={index} className="list-group-item">
              {<JobExperience experience={exp} />}
            </li>
          ))}
        </ul>
      ) : null}
      {props.showAdd && (
        <div className="card-body">
          <button onClick={onAddClicked} className="btn btn-primary">
            Add Job Experience
          </button>
        </div>
      )}
    </div>
  );
}

export default JobExperienceList;
