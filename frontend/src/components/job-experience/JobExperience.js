import React from "react";

function JobExperience(props) {
  const { experience } = props;
  return (
    <div className="card">
      <div className="card-body">
        <h5 className="card-title">{experience.title}</h5>
        <h6 className="card-subtitle mb-2 text-muted">{experience.company}</h6>
        <h6>
          {new Date(experience.startDate)?.toLocaleDateString("en-us", {
            year: "numeric",
            month: "short",
          })}{" "}
          -{" "}
          {new Date(experience.endDate)?.toLocaleDateString("en-us", {
            year: "numeric",
            month: "short",
          })}
        </h6>
        <p className="card-text">{experience.description}</p>
      </div>
    </div>
  );
}

export default JobExperience;
