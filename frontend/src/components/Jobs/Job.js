import axios from "axios";
import React, { useEffect, useState } from "react";
import { STUDENT } from "../../constants/constants";
import { getUser } from "../../service/tokenService";
import BodyContainer from "../body-container/BodyContainer";
import JobsList from "./JobsList";

function Job() {
  const [jobs, setJobs] = useState([]);
  const role = getUser()?.role;

  useEffect(() => {
    axios
      .get("/jobAdvertisements")
      .then((response) => {
        setJobs(response.data);
      })
      .catch((error) => {
        console.log(error.message);
      });
  }, []);

  const onApplyClicked = (id) => {
    //action handler
    console.log(id);
    if (window.confirm("Confirm Application?")) {
      axios
        .post("/jobApplications", {
          studentEmail: getUser().email,
          jobId: id,
        })
        .then(() => {
          window.alert("Job application submitted");
        });
    }
  };

  return (
    <BodyContainer>
      <section className="intro-single p-2">
        <div className="container">
          <div className="row">
            <div className="title-single-box">
              <h1 className="title-single">Jobs</h1>
            </div>
          </div>
        </div>
      </section>

      {role && role === STUDENT ? (
        <JobsList
          jobs={jobs}
          action={{ name: "Apply", onClicked: (id) => onApplyClicked(id) }}
          enableFilter = {true}
        />
      ) : (
        <JobsList jobs={jobs} enableFilter = {true}/>
      )}
    </BodyContainer>
  );
}

export default Job;
