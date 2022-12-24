import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import BodyContainer from "../body-container/BodyContainer";
import { getUser } from "../../service/tokenService";
import JobsList from "./JobsList";

export default function MyJobAdvertisements() {
  const [jobs, setJobs] = useState([]);
  const navigate = useNavigate();

  const onAddJobAdClicked = () => {
    navigate("/add-job");
  };

  const onEditClicked = (id) => {
    //action handler
    navigate('/edit-job/' + id);
  };

  useEffect(() => {
    axios
      .get("/jobAdvertisements/filterByOwnerEmail?email=" + getUser().email)
      .then((response) => {
        setJobs(response.data);
      })
      .catch((error) => {
        console.log(error.message);
      });
  }, []);

  return (
    <BodyContainer>
      <section className="intro-single p-2">
        <div className="container">
          <div className="row">
            <div className="title-single-box">
              <h1 className="title-single">My Job Advertisements</h1>
            </div>
          </div>
        </div>
      </section>

      <JobsList
        jobs={jobs}
        action={{ name: "Edit", onClicked: (id) => onEditClicked(id) }}        
        enableFilter = {true}
      />

      <div className="m-5">
        <button className="btn btn-primary" onClick={onAddJobAdClicked}>
          Add Job Advertisement
        </button>
      </div>
    </BodyContainer>
  );
}
