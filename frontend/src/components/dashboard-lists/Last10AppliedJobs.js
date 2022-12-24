import axios from "axios";
import React, { useEffect, useState } from "react";
import { STUDENT } from "../../constants/constants";
import { getUser } from "../../service/tokenService";
import BodyContainer from "../body-container/BodyContainer";
import JobsList from "../Jobs/JobsList";

function Last10AppliedJobs() {
  const [jobs, setJobs] = useState([]);
  const role = getUser()?.role;

  const setLast10AppliedJobs = (jobs) => {
    let distinctJobs = jobs
      .sort((x, y) => y.createdDate - x.createdDate)
      .flatMap((j) => j.job)
      .filter((v, i, a) => a.indexOf(v) === i);

    distinctJobs.length = Math.min(distinctJobs.length, 10);
    setJobs(distinctJobs);
  };

  useEffect(() => {
    axios
      .get("/jobApplications")
      .then((response) => {
        setLast10AppliedJobs(response.data);
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
              <h1 className="title-single">Last 10 Applied Jobs</h1>
            </div>
          </div>
        </div>
      </section>

      {role && role === STUDENT ? (
        <JobsList
          jobs={jobs}
          action={{ name: "Apply", onClicked: (id) => onApplyClicked(id) }}
          enableFilter={false}
        />
      ) : (
        <JobsList jobs={jobs} />
      )}
    </BodyContainer>
  );
}

export default Last10AppliedJobs;
